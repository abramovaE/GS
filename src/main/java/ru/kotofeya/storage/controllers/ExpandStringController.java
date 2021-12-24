package ru.kotofeya.storage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kotofeya.storage.model.*;
import ru.kotofeya.storage.service.DeletedExpandService;
import ru.kotofeya.storage.service.ExpandStringService;
import ru.kotofeya.storage.service.ItemService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class ExpandStringController {
    @Autowired
    private ExpandStringService expandStringService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private DeletedExpandService deletedExpandService;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");

//    @GetMapping({"/show_expand_string/{incomeStringId}/{editUserName}",
//            "show_income_main/{incomeMainId}/show_income_string/{incomeStringId}/{editUserName}"})
//    public String  showIncomeString(@PathVariable ("incomeStringId") Long incomeStringId,
//                                    @PathVariable ("editUserName") String editUserName,
//                                    @PathVariable ("incomeMainId") Long incomeMainId,
//                                    Model model) {
//        IncomeString incomeString = incomeStringService.getIncomeById(incomeStringId);
//        List<Item> allItems = itemService.getAllItems();
//        model.addAttribute("items", allItems);
//        incomeString.setPurchasePriceDouble(incomeString.getPurchasePrice()/100d);
//        incomeString.setPurchasePriceActDouble(incomeString.getPurchasePriceAct()/100d);
//        model.addAttribute("incomeStringForm", incomeString);
//        model.addAttribute("date", LocalDateTime.now().format(dateTimeFormatter));
//        return "storage/incomes/show_income_string";
//    }


//    @GetMapping("/add_expand_string")
//    public String  addExpand(Model model) {
//        List<Item> allItems = itemService.getAllItemsWithCount();
//        model.addAttribute("items", allItems);
//        model.addAttribute("date", LocalDateTime.now().format(dateTimeFormatter));
//        model.addAttribute("expandStringForm", new ExpandString());
//        return "storage/add_expand_string";
//    }

//    @PostMapping("/add_income_main")
//    public String  addIncomeString(Model model,
//                                   @ModelAttribute ("incomeMainForm") IncomeMain incomeMain,
//                                   @ModelAttribute ("incomeJson") String incomeJson) {
//        incomeMainService.saveIncomeMain(incomeMain);
//        Set<IncomeString> incomeStrings = createIncomeStringsFromJson(incomeJson, incomeMain.getId());
//        incomeStrings.stream().forEach(it->incomeStringService.saveIncome(it));
//        incomeMain.setIncomeStrings(incomeStrings);
//        incomeMainService.saveIncomeMain(incomeMain);
//        return "redirect:/incomes_main";
//    }


//    @PostMapping("/add_expand_string")
//    public String addExpandMain(Model model,
//                                @ModelAttribute("expandStringForm") ExpandString expandString,
//                                @ModelAttribute ("expandJson") String expandJson) {
//        System.out.println("post add expand: " + expandString);
//        System.out.println("post add json: " + expandJson);
//
//        expandString.setDate(LocalDateTime.now().format(dateTimeFormatter));
//        expandString.setSalePrice((int) (expandString.getSalePriceDouble() * 100d));
////        expandStringService.saveExpand(expandString);
//        Item item = itemService.getById(expandString.getItem().getId());
//        if(item != null) {
//            int count = item.getCount() == null? 0 : item.getCount();
//            item.setCount(count - expandString.getCount());
//            itemService.saveItem(item);
//        }
//        return "redirect:/add_expand_string";
//    }

//    @GetMapping("/delete_expand_string/{expandStringId}/{deleteUserName}")
//    public String deleteIncome(@PathVariable("expandStringId") Long expandStringId,
//                               @PathVariable ("deleteUserName") String deleteUserName,
//                               Model model) {
//        ExpandString expandString = expandStringService.getExpandById(expandStringId);
//        if(expandString != null){
//            Item expandItem = itemService.getById(expandString.getItem().getId());
//            if(expandItem != null){
//                expandItem.setCount(expandItem.getCount() + expandString.getCount());
//                itemService.saveItem(expandItem);
//            }
//            DeletedExpandString deletedExpandString = new DeletedExpandString(expandString,
//                    LocalDateTime.now().format(dateTimeFormatter),
//                    deleteUserName);
//            deletedExpandService.saveDeletedExpand(deletedExpandString);
//            expandStringService.deleteExpandById(expandString.getId());
//        }
//        return "redirect:/add_expand_string";
//    }
//
//    @GetMapping("/expand_strings")
//    public String  showIncomes(Model model) {
//        List<ExpandString> expandStrings = expandStringService.getAllExpands();
//        Collections.sort(expandStrings, new Comparator<ExpandString>() {
//            @Override
//            public int compare(ExpandString o1, ExpandString o2) {
//                String date1 = o1.getDate();
//                String date2 = o2.getDate();
//                if (date1 == null) {
//                    date1 = LocalDate.now().format(dateTimeFormatter);
//                }
//                if (date2 == null) {
//                    date2 = LocalDate.now().format(dateTimeFormatter);
//                }
//                LocalDate localDate1 = LocalDate.parse(date1, dateTimeFormatter);
//                LocalDate localDate2 = LocalDate.parse(date2, dateTimeFormatter);
//                return localDate2.compareTo(localDate1);
//            }
//        });
//        model.addAttribute("expandStrings", expandStrings);
//        return "storage/expand_strings";
//    }
}