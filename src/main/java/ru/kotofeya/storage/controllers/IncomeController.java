package ru.kotofeya.storage.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kotofeya.storage.model.Income;
import ru.kotofeya.storage.model.Item;
import ru.kotofeya.storage.service.IncomeService;
import ru.kotofeya.storage.service.ItemService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class IncomeController {
    @Autowired
    private IncomeService incomeService;

    @Autowired
    private ItemService itemService;

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");


    @GetMapping("/add_income")
    public String  addIncome(Model model) {

        List<Item> allItems = itemService.getAllItems();
        List<Income> todayIncomes = incomeService.getTodayIncomes(LocalDateTime.now().format(dateTimeFormatter));
        model.addAttribute("items", allItems);
        model.addAttribute("incomeForm", new Income());
        model.addAttribute("todayIncomes", todayIncomes);

        return "storage/add_income";
    }

    @PostMapping("/add_income")
    public String addItem(@ModelAttribute("incomeForm") Income income, Model model) {
        System.out.println("post add income: " + income);
        income.setDate(LocalDateTime.now().format(dateTimeFormatter));
        incomeService.saveIncome(income);
        return "redirect:/add_income";
    }
}
