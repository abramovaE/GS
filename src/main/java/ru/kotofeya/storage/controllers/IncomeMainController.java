package ru.kotofeya.storage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kotofeya.storage.model.IncomeMain;
import ru.kotofeya.storage.model.IncomeString;
import ru.kotofeya.storage.model.Item;
import ru.kotofeya.storage.service.IncomeMainService;
import ru.kotofeya.storage.service.ItemService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class IncomeMainController {
    @Autowired
    private IncomeMainService incomeMainService;
    @Autowired
    private ItemService itemService;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");

    @GetMapping("/add_income_main")
    public String  addIncomeString(Model model) {
        List<Item> allItems = itemService.getAllItems();
        System.out.println("allitems size: " + allItems);
        model.addAttribute("items", allItems);
        model.addAttribute("incomeMainForm", new IncomeMain());
        model.addAttribute("date", LocalDateTime.now().format(dateTimeFormatter));
//        model.addAttribute("incomeStrings", new HashSet<IncomeString>());
        model.addAttribute("incomeJson", new String());
        model.addAttribute("incomeString", new IncomeString());

        return "storage/add_income_main";
    }



    @PostMapping("/add_income_main")
    public String  addIncomeString(Model model,
                                   @ModelAttribute ("incomeMainForm") IncomeMain incomeMain,
                                   @ModelAttribute ("incomeJson") String incomeJson) {
        System.out.println("incomeJson: " + incomeJson);
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
