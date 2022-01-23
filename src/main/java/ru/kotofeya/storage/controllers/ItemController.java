package ru.kotofeya.storage.controllers;

import com.google.gson.*;

import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kotofeya.storage.model.items.EditedItem;
import ru.kotofeya.storage.model.items.Item;
import ru.kotofeya.storage.service.EditItemService;
import ru.kotofeya.storage.service.ItemService;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ItemController {
    public static final String PREFIX= "b";
    @Autowired
    private ItemService itemService;
    @Autowired
    private EditItemService editItemService;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @GetMapping({"/add_item", "/show_income_main/{incomeMainId}/add_item"})
    public String  addItem(Model model) {
        Item item = new Item();
        Item maxIdItem = itemService.findMaxIdItem();
        long maxItemId = maxIdItem == null? 0 : maxIdItem.getId();
        maxItemId++;
        item.setArticle(PREFIX + String.format("%06d", maxItemId));
        model.addAttribute("itemForm", item);
        List<Item> allItems = itemService.getAllItems();
        model.addAttribute("eans", allItems.stream().map(it->it.getEan()).collect(Collectors.toSet()));
        return "storage/items/add_item";
    }

    @PostMapping({"/add_item", "/show_income_main/{incomeMainId}/add_item"})
    public String addItem(HttpServletRequest req, @ModelAttribute("itemForm") Item item, Model model) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        item.setDate(LocalDateTime.now().format(dateTimeFormatter));
        item.setCount(0);
        if(item.getArticle() == null) {
            Item maxIdItem = itemService.findMaxIdItem();
            long maxItemId = maxIdItem == null ? 0 : maxIdItem.getId();
            maxItemId++;
            item.setArticle(PREFIX + String.format("%06d", maxItemId));
        }
        itemService.saveItem(item);
        req.setAttribute("items", itemService.getAllItems());
        return "redirect:/close";
    }

    @GetMapping({"/update", "/show_income_main/{incomeMainId}/update"})
    @ResponseBody
    public String items() {
        System.out.println("updateitems");
        List<Item> items = itemService.getAllItems();
        List<ItemJson> itemJsons = new ArrayList<>();
        for(Item item: items){
            itemJsons.add(new ItemJson(item));
        }
        Type listType = new TypeToken<List<ItemJson>>() {}.getType();
//        System.out.println(new GsonBuilder().create().toJson(itemJsons, listType));
        return new GsonBuilder().create().toJson(itemJsons, listType);
    }

    @GetMapping({"/show_item/{itemId}/{editUserName}",
            "items_main/sortBy/{sortParam}/show_item/{itemId}/{editUserName}",
            "allItems/sortBy/{sortParam}/show_item/{itemId}/{editUserName}"})
    public String showItem(Model model,
                                 @PathVariable("itemId") Long itemId,
                                 @PathVariable("editUserName") String editUserName) {
        Item item = itemService.getById(itemId);
        model.addAttribute("item", item);
        List<Item> allItems = itemService.getAllItems();
        model.addAttribute("eans", allItems.stream().map(it->it.getEan()).collect(Collectors.toSet()));
        return "storage/items/show_item";
    }

    @PostMapping({"/show_item/{itemId}/{editUserName}",
            "items_main/sortBy/{sortParam}/show_item/{itemId}/{editUserName}",
            "allItems/sortBy/{sortParam}/show_item/{itemId}/{editUserName}"})
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
        return "redirect:/close";
    }
}

class ItemJson{
    private Long id;
    private String name;
    private String ean;

    public ItemJson() {
    }
    public ItemJson(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.ean = item.getEan();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }
}

