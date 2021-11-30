package ru.kotofeya.storage.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kotofeya.storage.model.DeletedIncome;
import ru.kotofeya.storage.model.Income;
import ru.kotofeya.storage.model.Item;
import ru.kotofeya.storage.service.DeletedIncomeService;
import ru.kotofeya.storage.service.IncomeService;
import ru.kotofeya.storage.service.ItemService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class IncomeController {
    @Autowired
    private IncomeService incomeService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private DeletedIncomeService deletedIncomeService;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");

    @GetMapping("/add_income")
    public String  addIncome(Model model) {
        List<Item> allItems = itemService.getAllItems();
        List<Income> todayIncomes = incomeService.getTodayIncomes(LocalDateTime.now().format(dateTimeFormatter));
        model.addAttribute("items", allItems);
        model.addAttribute("incomeForm", new Income());
        model.addAttribute("todayIncomes", todayIncomes);
        model.addAttribute("date", LocalDateTime.now().format(dateTimeFormatter));
        return "storage/add_income";
    }

    @PostMapping("/add_income")
    public String addIncome(@ModelAttribute("incomeForm") Income income, Model model) {
        System.out.println("post add income: " + income);
        income.setDate(LocalDateTime.now().format(dateTimeFormatter));
        incomeService.saveIncome(income);
        Item item = itemService.getById(income.getItem().getId());
        if(item != null) {
            int count = item.getCount() == null? 0 : item.getCount();
            item.setCount(count + income.getCount());
            itemService.saveItem(item);
        }
        return "redirect:/add_income";
    }
    @GetMapping("/delete_income/{incomeId}/{deleteUserName}")
    public String deleteIncome(@PathVariable ("incomeId") Long incomeId,
                               @PathVariable ("deleteUserName") String deleteUserName,
                               Model model) {
        Income income = incomeService.getIncomeById(incomeId);
        if(income != null){
            Item incomeItem = itemService.getById(income.getItem().getId());
            if(incomeItem != null){
                incomeItem.setCount(incomeItem.getCount() - income.getCount());
                itemService.saveItem(incomeItem);
            }
            DeletedIncome deletedIncome = new DeletedIncome(income,
                    LocalDateTime.now().format(dateTimeFormatter),
                    deleteUserName);
            deletedIncomeService.saveDeletedIncome(deletedIncome);
            incomeService.deleteIncomeById(income.getId());
        }
        return "redirect:/add_income";
    }

    @GetMapping("/edit_income/{incomeId}")
    public String editIncome(@PathVariable ("incomeId") Long incomeId,
                               Model model) {
        Income income = incomeService.getIncomeById(incomeId);
        if(income != null){
            Item item = itemService.getById(income.getItem().getId());
            income.setItem(item);
            income.setPurchasePriceDouble(income.getPurchasePrice()/100d);
            income.setPurchasePriceActDouble(income.getPurchasePriceAct()/100d);
            List<Item> allItems = itemService.getAllItems();
            model.addAttribute("currentItem", item);
            model.addAttribute("items", allItems);
            model.addAttribute("incomeForm", income);
            model.addAttribute("date", LocalDateTime.now().format(dateTimeFormatter));
            model.addAttribute("ppSum", (income.getCount() * income.getPurchasePrice()/100d));
            model.addAttribute("ppSumAct", (income.getCount() * income.getPurchasePriceAct()/100d));
            return "storage/edit_income";
        }
        return "redirect:/add_income";
    }
}
