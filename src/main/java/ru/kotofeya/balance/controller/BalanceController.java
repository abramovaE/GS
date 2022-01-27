package ru.kotofeya.balance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kotofeya.balance.model.Money;
import ru.kotofeya.balance.service.MoneyService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class BalanceController {

    @Autowired
    private MoneyService moneyService;

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");


    @GetMapping("/add_money")
    public String  addMoney(Model model) {
        Money money = new Money();
        money.setDate(dateTimeFormatter.format(LocalDateTime.now()));
        model.addAttribute("money", money);
        return "balance/add_money";
    }

    @PostMapping("/add_money")
    public String  addMoney(Model model,
                            @ModelAttribute("money") Money money) {
        money.setSum((int) (money.getDoubleSum() * 100));
        moneyService.saveMoney(money);
        return "redirect:/";
    }

    @GetMapping("/all_money")
    public String allMoney(Model model) {
        model.addAttribute("allMoney", moneyService.findAllMoney());
        return "balance/all_money";
    }
}
