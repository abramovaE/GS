package ru.kotofeya.storage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kotofeya.storage.model.Expand;
import ru.kotofeya.storage.model.Income;
import ru.kotofeya.storage.service.ExpandService;
import ru.kotofeya.storage.service.ItemService;

@Controller
public class ExpandController {
    @Autowired
    private ExpandService expandService;


    @GetMapping("/add_expand")
    public String  addIncome(Model model) {
        model.addAttribute("expandForm", new Expand());
        return "storage/add_expand";
    }
}
