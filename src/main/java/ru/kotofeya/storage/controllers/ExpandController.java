package ru.kotofeya.storage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kotofeya.storage.model.Expand;
import ru.kotofeya.storage.model.Income;
import ru.kotofeya.storage.model.Item;
import ru.kotofeya.storage.service.ExpandService;
import ru.kotofeya.storage.service.ItemService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class ExpandController {
    @Autowired
    private ExpandService expandService;
    @Autowired
    private ItemService itemService;
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
        expandService.saveExpand(expand);
        Item item = itemService.getById(expand.getItem().getId());
        if(item != null) {
            int count = item.getCount() == null? 0 : item.getCount();
            item.setCount(count - expand.getCount());
            itemService.saveItem(item);
        }
        return "redirect:/add_expand";
    }
}
