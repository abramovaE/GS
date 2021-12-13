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
import ru.kotofeya.storage.service.ExpandService;
import ru.kotofeya.storage.service.ItemService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class ExpandController {
    @Autowired
    private ExpandService expandService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private DeletedExpandService deletedExpandService;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");

    @GetMapping("/add_expand")
    public String  addIncome(Model model) {
        List<Item> allItems = itemService.getAllItemsWithCount();
        List<Expand> todayExpands = expandService.getTodayExpands(LocalDateTime.now().format(dateTimeFormatter));
        model.addAttribute("items", allItems);
        model.addAttribute("date", LocalDateTime.now().format(dateTimeFormatter));
        model.addAttribute("expandForm", new Expand());
        model.addAttribute("todayExpands", todayExpands);
        return "storage/add_expand";
    }

    @PostMapping("/add_expand")
    public String addItem(@ModelAttribute("expandForm") Expand expand, Model model) {
        System.out.println("post add expand: " + expand);
        expand.setDate(LocalDateTime.now().format(dateTimeFormatter));
        expand.setSalePrice((int) (expand.getSalePriceDouble() * 100d));
        expandService.saveExpand(expand);
        Item item = itemService.getById(expand.getItem().getId());
        if(item != null) {
            int count = item.getCount() == null? 0 : item.getCount();
            item.setCount(count - expand.getCount());
            itemService.saveItem(item);
        }
        return "redirect:/add_expand";
    }

    @GetMapping("/delete_expand/{expandId}/{deleteUserName}")
    public String deleteIncome(@PathVariable("expandId") Long expandId,
                               @PathVariable ("deleteUserName") String deleteUserName,
                               Model model) {
        Expand expand = expandService.getExpandById(expandId);
        if(expand != null){
            Item expandItem = itemService.getById(expand.getItem().getId());
            if(expandItem != null){
                expandItem.setCount(expandItem.getCount() + expand.getCount());
                itemService.saveItem(expandItem);
            }
            DeletedExpand deletedExpand = new DeletedExpand(expand,
                    LocalDateTime.now().format(dateTimeFormatter),
                    deleteUserName);
            deletedExpandService.saveDeletedExpand(deletedExpand);
            expandService.deleteExpandById(expand.getId());
        }
        return "redirect:/add_expand";
    }

    @GetMapping("/expands")
    public String  showIncomes(Model model) {
        List<Expand> expands = expandService.getAllExpands();
        Collections.sort(expands, new Comparator<Expand>() {
            @Override
            public int compare(Expand o1, Expand o2) {
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
        model.addAttribute("expands", expands);
        return"storage/expands";
    }
}