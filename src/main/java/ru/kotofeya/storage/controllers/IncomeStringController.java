package ru.kotofeya.storage.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kotofeya.storage.model.*;
import ru.kotofeya.storage.service.DeletedIncomeService;
import ru.kotofeya.storage.service.IncomeMainService;
import ru.kotofeya.storage.service.IncomeStringService;
import ru.kotofeya.storage.service.ItemService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Controller
public class IncomeStringController {
    @Autowired
    private IncomeStringService incomeStringService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private DeletedIncomeService deletedIncomeService;
    @Autowired
    private IncomeMainService incomeMainService;

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
    @GetMapping({"/delete_income_string/{incomeStringId}/{deleteUserName}",
            "show_income_main/{incomeMainId}/delete_income_string/{incomeStringId}/{deleteUserName}"})
    public String deleteIncome(@PathVariable ("incomeStringId") Long incomeStringId,
                               @PathVariable ("deleteUserName") String deleteUserName,
                               @PathVariable ("incomeMainId") Long incomeMainId,
                               Model model) {

        IncomeString incomeString = incomeStringService.getIncomeById(incomeStringId);
        if(incomeString != null){
            Item incomeItem = itemService.getById(incomeString.getItem().getId());
            if(incomeItem != null){
                Integer incomeItemCount = incomeItem.getCount();
                if(incomeItemCount == null){
                    incomeItemCount = 0;
                }
                Integer incomeStringCount = incomeString.getCount();
                if(incomeStringCount == null){
                    incomeStringCount = 0;
                }
                incomeItem.setCount(incomeItemCount - incomeStringCount);
                itemService.saveItem(incomeItem);
            }
            DeletedIncomeString deletedIncome = new DeletedIncomeString(incomeString,
                    LocalDateTime.now().format(dateTimeFormatter),
                    deleteUserName);

            IncomeMain incomeMain = incomeMainService.findById(incomeMainId);
            Set<IncomeString> incomeStrings = incomeMain.getIncomeStrings();
            incomeStrings.remove(incomeString);
            incomeMain.setIncomeStrings(incomeStrings);

            deletedIncomeService.saveDeletedIncome(deletedIncome);
            incomeStringService.deleteIncomeById(incomeString.getId());
            incomeMainService.saveIncomeMain(incomeMain);

        }

        return "redirect:/show_income_main/" +
                incomeString.getIncomeMain().getId() + "/" + deleteUserName;
    }


    @GetMapping({"/show_income_string/{incomeStringId}/{editUserName}",
            "show_income_main/{incomeMainId}/show_income_string/{incomeStringId}/{editUserName}"})
    public String  showIncomeString(@PathVariable ("incomeStringId") Long incomeStringId,
                                    @PathVariable ("editUserName") String editUserName,
                                    @PathVariable ("incomeMainId") Long incomeMainId,
                                    Model model) {
        IncomeString incomeString = incomeStringService.getIncomeById(incomeStringId);
        List<Item> allItems = itemService.getAllItems();
        model.addAttribute("items", allItems);
        model.addAttribute("incomeStringForm", incomeString);
        model.addAttribute("date", LocalDateTime.now().format(dateTimeFormatter));
        return "storage/show_income_string";
    }

    @PostMapping({"/show_income_string/{incomeStringId}/{editUserName}",
            "show_income_main/{incomeMainId}/show_income_string/{incomeStringId}/{editUserName}"})
    public String  showIncomeStringPost(@PathVariable ("incomeStringId") Long incomeStringId,
                                    @PathVariable ("editUserName") String editUserName,
                                    @PathVariable ("incomeMainId") Long incomeMainId,
                                    Model model) {
        System.out.println("post show");
        EditedIncomeString editedIncomeString = new EditedIncomeString();



//        IncomeString incomeString = incomeStringService.getIncomeById(incomeStringId);
//        List<Item> allItems = itemService.getAllItems();
//        model.addAttribute("items", allItems);
//        model.addAttribute("incomeStringForm", incomeString);
//        model.addAttribute("date", LocalDateTime.now().format(dateTimeFormatter));
        return "redirect:/show_income_main/" + incomeMainId + "/" + editUserName;
    }


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
