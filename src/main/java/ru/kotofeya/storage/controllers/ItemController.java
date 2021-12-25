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

    @GetMapping("/add_item")
    public String  addItem(Model model) {
        Item item = new Item();
        Item maxIdItem = itemService.findMaxIdItem();
        long maxItemId = maxIdItem == null? 0 : maxIdItem.getId();
        maxItemId++;
        item.setArticle("gs" + String.format("%06d", maxItemId));
        model.addAttribute("itemForm", item);
        return "storage/items/add_item";
    }

    @PostMapping("/add_item")
    public String addItem(@ModelAttribute("itemForm") Item item, Model model) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");
        item.setDate(LocalDateTime.now().format(dateTimeFormatter));
        item.setCount(0);
        if(item.getArticle() == null) {
            Item maxIdItem = itemService.findMaxIdItem();
            long maxItemId = maxIdItem == null ? 0 : maxIdItem.getId();
            maxItemId++;
            item.setArticle("gs" + String.format("%06d", maxItemId));
        }
        itemService.saveItem(item);
        return "redirect:/show_storage";
    }
}
