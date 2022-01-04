package ru.kotofeya.storage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kotofeya.storage.model.items.EditedItem;
import ru.kotofeya.storage.model.items.Item;
import ru.kotofeya.storage.service.EditItemService;
import ru.kotofeya.storage.service.ItemService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private EditItemService editItemService;

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");


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
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
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





    @GetMapping("/show_item/{itemId}/{editUserName}")
    public String showItem(Model model,
                                 @PathVariable("itemId") Long itemId,
                                 @PathVariable("editUserName") String editUserName) {
        Item item = itemService.getById(itemId);
        model.addAttribute("item", item);
        return "storage/items/show_item";
    }

    @PostMapping("/show_item/{itemId}/{editUserName}")
    public String showItem(Model model,
                           @ModelAttribute("item") Item item,
                           @PathVariable("itemId") Long itemId,
                           @PathVariable("editUserName") String editUserName) {
        Item itemFromDb = itemService.getById(itemId);
        EditedItem editedItem = new EditedItem(itemFromDb, item, editUserName,
                LocalDateTime.now().format(dateTimeFormatter));
        editItemService.save(editedItem);
        itemService.saveItem(item);
        model.addAttribute("item", item);
        return "storage/items/show_item";
    }


}
