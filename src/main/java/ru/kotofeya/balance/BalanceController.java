package ru.kotofeya.balance;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BalanceController {


    @GetMapping("/add_money")
    public String  addMoney(Model model) {
        return "balance/add_money";
    }



}
