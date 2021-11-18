package ru.kotofeya.storage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kotofeya.storage.service.ItemService;
import ru.kotofeya.storage.service.StorageService;

@Controller
public class StorageController {
    @Autowired
    private StorageService storageService;

    @GetMapping("/storage_main")
    public String storage(Model model) {
        return "storage/storage_main";
    }
}
