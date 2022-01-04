package ru.kotofeya.storage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kotofeya.storage.model.items.Item;
import ru.kotofeya.storage.service.ItemService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


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

    @GetMapping("/items_main")
    public String getAllItems(Model model) {
        List<Item> items = itemService.getAllItems();
        model.addAttribute("items", items);
        return "storage/items/items_main";
    }



    @GetMapping("/show_item/{itemId}/{editUserName}")
    public String showItem(Model model,
                                 @PathVariable("itemId") Long itemId,
                                 @PathVariable("editUserName") String editUserName) {
//        List<Item> allItems = itemService.getAllItems();
//        IncomeMain incomeMain = incomeMainService.findById(incomeId);
//        incomeMain = setSumsForJsp(incomeMain);
        Item item = itemService.getById(itemId);
        model.addAttribute("item", item);
//        model.addAttribute("items", allItems);
//        model.addAttribute("eans", allItems.stream().map(it->it.getEan()).collect(Collectors.toSet()));
        return "storage/items/show_item";
    }

}
