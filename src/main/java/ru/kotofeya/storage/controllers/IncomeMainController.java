package ru.kotofeya.storage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kotofeya.storage.model.IncomeMain;
import ru.kotofeya.storage.service.IncomeMainService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class IncomeMainController {
    @Autowired
    private IncomeMainService incomeMainService;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");

    @GetMapping("/add_income_main")
    public String  addIncomeString(Model model) {
//        List<IncomeString> allItems = itemService.getAllItems();
//        List<IncomeString> todayIncomeStrings = incomeStringService.getTodayIncomes(LocalDateTime.now().format(dateTimeFormatter));
//        model.addAttribute("items", allItems);
        model.addAttribute("incomeMainForm", new IncomeMain());
//        model.addAttribute("todayIncomeStrings", todayIncomeStrings);
        model.addAttribute("date", LocalDateTime.now().format(dateTimeFormatter));
        return "storage/add_income_main";
    }

    @PostMapping("/add_income_main")
    public String  addIncomeString(Model model,
                                   @ModelAttribute ("incomeMainForm") IncomeMain incomeMain) {
        incomeMainService.saveIncomeMain(incomeMain);
        return "redirect:/incomes_main";
    }

    @GetMapping("/incomes_main")
    public String getAllIncomesMAin(Model model){
        List<IncomeMain> incomesMain = incomeMainService.getAllIncomesMain();
        model.addAttribute("incomesMain", incomesMain);
        return "storage/incomes_main";
    }
}
