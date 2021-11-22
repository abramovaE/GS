package ru.kotofeya.storage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kotofeya.storage.model.Income;
import ru.kotofeya.storage.service.IncomeService;

@Controller
public class IncomeController {
    @Autowired
    private IncomeService incomeService;

    @GetMapping("/add_income")
    public String  addIncome(Model model) {
        model.addAttribute("incomeForm", new Income());
        return "storage/add_income";
    }
}
