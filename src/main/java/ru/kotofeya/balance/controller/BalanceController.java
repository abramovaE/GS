package ru.kotofeya.balance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kotofeya.balance.model.Money;
import ru.kotofeya.balance.service.MoneyService;
import ru.kotofeya.storage.model.incomes.IncomeMain;
import ru.kotofeya.storage.model.incomes.IncomeString;
import ru.kotofeya.storage.service.incomes.IncomeMainService;
import ru.kotofeya.storage.service.incomes.IncomeStringService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class BalanceController {

    @Autowired
    private MoneyService moneyService;
    @Autowired
    IncomeMainService incomeMainService;
    @Autowired
    IncomeStringService incomeStringService;


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
        List<Money> allMoney = moneyService.findAllMoney();
        List<IncomeMain> incomesMain = incomeMainService.getAllIncomesMain();
        for(IncomeMain incomeMain: incomesMain){
            setSumsForJsp(incomeMain);
        }
        int allMoneySum = allMoney.stream().mapToInt(it->it.getSum()).sum();
        int allIncomeActSum = incomesMain.stream().mapToInt(it->it.getSumAct()).sum();

        model.addAttribute("allMoneySum", allMoneySum);
        model.addAttribute("allMoney", allMoney);
//        model.addAttribute("incomesMain", incomesMain);
        model.addAttribute("availableMoney", allMoneySum - allIncomeActSum);

        return "balance/all_money";
    }

    private IncomeMain setSumsForJsp(IncomeMain incomeMain){
        int sum = 0;
        int sumAct = 0;
        List<IncomeString> incomeStrings = incomeStringService.findByIncomeMain(incomeMain);
        for(IncomeString incomeString: incomeStrings){
            sum += incomeString.getCount() * incomeString.getPurchasePrice();
            sumAct += incomeString.getCount() * incomeString.getPurchasePriceAct();
        }
        incomeMain.setSum(sum);
        incomeMain.setSumAct(sumAct);
        return incomeMain;
    }
}
