package ru.kotofeya.storage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kotofeya.storage.model.Item;
import ru.kotofeya.storage.service.ItemService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;


//    @GetMapping("/all_items")
//    public String  allItems(Model model) {
//        model.addAttribute("allItems", itemService.getAllItems());
//        return "storage/all_items";
//    }

    @GetMapping("/add_item")
    public String  addItem(Model model) {
        model.addAttribute("itemForm", new Item());
        return "storage/add_item";
    }

    @PostMapping("/add_item")
    public String addItem(@ModelAttribute("itemForm") Item item, Model model) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");
        item.setDate(LocalDateTime.now().format(dateTimeFormatter));
        item.setCount(0);
        itemService.saveItem(item);
        return "redirect:/show_storage";
    }
}
