package ru.kotofeya.storage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kotofeya.storage.service.ItemService;

@Controller
public class StorageController {

    @Autowired
    private ItemService itemService;

    // TODO: 22.11.2021 сделать актуальные остатки
    @GetMapping("/show_storage")
    public String  addIncome(Model model) {
        model.addAttribute("items", itemService.getAllItems());
        return "storage/all_items";
    }
    
}
