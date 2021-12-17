package ru.kotofeya.storage.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kotofeya.storage.model.DeletedIncomeString;
import ru.kotofeya.storage.model.IncomeString;
import ru.kotofeya.storage.model.Item;
import ru.kotofeya.storage.service.DeletedIncomeService;
import ru.kotofeya.storage.service.IncomeStringService;
import ru.kotofeya.storage.service.ItemService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class IncomeStringController {
    @Autowired
    private IncomeStringService incomeStringService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private DeletedIncomeService deletedIncomeService;

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");

    @GetMapping("/add_income_string")
    public String  addIncomeString(Model model) {
        List<Item> allItems = itemService.getAllItems();
        List<IncomeString> todayIncomeStrings = incomeStringService.getTodayIncomes(LocalDateTime.now().format(dateTimeFormatter));
        model.addAttribute("items", allItems);
        model.addAttribute("incomeStringForm", new IncomeString());
        model.addAttribute("todayIncomeStrings", todayIncomeStrings);
        model.addAttribute("date", LocalDateTime.now().format(dateTimeFormatter));
        return "storage/add_income_string";
    }

    @PostMapping("/add_income_string")
    public String addIncome(@ModelAttribute("incomeStringForm") IncomeString incomeString, Model model) {
        System.out.println("post add income: " + incomeString);
        incomeString.setDate(LocalDateTime.now().format(dateTimeFormatter));
        incomeString.setPurchasePrice((int) (incomeString.getPurchasePriceDouble() * 100));
        incomeString.setPurchasePriceAct((int) (incomeString.getPurchasePriceActDouble() * 100));
        incomeStringService.saveIncome(incomeString);
        Item item = itemService.getById(incomeString.getItem().getId());
        if(item != null) {
            int count = item.getCount() == null? 0 : item.getCount();
            item.setCount(count + incomeString.getCount());
            itemService.saveItem(item);
        }
        return "redirect:/add_income_string";
    }
    @GetMapping("/delete_income_string/{incomeStringId}/{deleteUserName}")
    public String deleteIncome(@PathVariable ("incomeStringId") Long incomeStringId,
                               @PathVariable ("deleteUserName") String deleteUserName,
                               Model model) {
        IncomeString incomeString = incomeStringService.getIncomeById(incomeStringId);
        if(incomeString != null){
            Item incomeItem = itemService.getById(incomeString.getItem().getId());
            if(incomeItem != null){
                incomeItem.setCount(incomeItem.getCount() - incomeString.getCount());
                itemService.saveItem(incomeItem);
            }
            DeletedIncomeString deletedIncome = new DeletedIncomeString(incomeString,
                    LocalDateTime.now().format(dateTimeFormatter),
                    deleteUserName);
            deletedIncomeService.saveDeletedIncome(deletedIncome);
            incomeStringService.deleteIncomeById(incomeString.getId());
        }
        return "redirect:/add_income_string";
    }

//    @GetMapping("/edit_income/{incomeId}")
//    public String editIncome(@PathVariable ("incomeId") Long incomeId,
//                               Model model) {
//        Income income = incomeService.getIncomeById(incomeId);
//        if(income != null){
//            Item item = itemService.getById(income.getItem().getId());
//            income.setItem(item);
//            income.setPurchasePriceDouble(income.getPurchasePrice()/100d);
//            income.setPurchasePriceActDouble(income.getPurchasePriceAct()/100d);
//            List<Item> allItems = itemService.getAllItems();
//            model.addAttribute("currentItem", item);
//            model.addAttribute("items", allItems);
//            model.addAttribute("incomeForm", income);
//            model.addAttribute("date", LocalDateTime.now().format(dateTimeFormatter));
//            model.addAttribute("ppSum", (income.getCount() * income.getPurchasePrice()/100d));
//            model.addAttribute("ppSumAct", (income.getCount() * income.getPurchasePriceAct()/100d));
//            return "storage/edit_income";
//        }
//        return "redirect:/add_income";
//    }
//
//    @PostMapping("/edit_income/{incomeId}")
//    public String editIncome(Model model,
//                             @PathVariable("incomeId") Long incomeId,
//                             @ModelAttribute("incomeForm") Income income) {
    // TODO: 30.11.2021 save, logging, save editIncome 
//        incomeService.saveIncome(income);
//        return "redirect:/edit_income/" + incomeId;
//    }

    @GetMapping("/income_strings")
    public String  showIncomes(Model model) {
        List<IncomeString> incomeStrings = incomeStringService.getAllIncomes();
        Collections.sort(incomeStrings, new Comparator<IncomeString>() {
            @Override
            public int compare(IncomeString o1, IncomeString o2) {
                String date1 = o1.getDate();
                String date2 = o2.getDate();
                if (date1 == null) {
                    date1 = LocalDate.now().format(dateTimeFormatter);
                }
                if (date2 == null) {
                    date2 = LocalDate.now().format(dateTimeFormatter);
                }
                LocalDate localDate1 = LocalDate.parse(date1, dateTimeFormatter);
                LocalDate localDate2 = LocalDate.parse(date2, dateTimeFormatter);
                return localDate2.compareTo(localDate1);
            }
        });
        model.addAttribute("incomeStrings", incomeStrings);
        return"storage/income_strings";
    }
}
