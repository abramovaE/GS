package ru.kotofeya.storage.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kotofeya.storage.model.*;
import ru.kotofeya.storage.service.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@Controller
public class IncomeStringController {
    @Autowired
    private IncomeStringService incomeStringService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private DeletedIncomeService deletedIncomeService;
    @Autowired
    private IncomeMainService incomeMainService;
    @Autowired
    private EditIncomeStringService editIncomeStringService;

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");

    @GetMapping({"/delete_income_string/{incomeStringId}/{deleteUserName}",
            "show_income_main/{incomeMainId}/delete_income_string/{incomeStringId}/{deleteUserName}"})
    public String deleteIncome(@PathVariable ("incomeStringId") Long incomeStringId,
                               @PathVariable ("deleteUserName") String deleteUserName,
                               @PathVariable ("incomeMainId") Long incomeMainId,
                               Model model) {
        IncomeString incomeString = incomeStringService.getIncomeById(incomeStringId);
        if(incomeString != null){
            DeletedIncomeString deletedIncome = new DeletedIncomeString(incomeString,
                    LocalDateTime.now().format(dateTimeFormatter),
                    deleteUserName);
            IncomeMain incomeMain = incomeMainService.findById(incomeMainId);
            Set<IncomeString> incomeStrings = incomeMain.getIncomeStrings();
            incomeStrings.remove(incomeString);
            incomeMain.setIncomeStrings(incomeStrings);
            deletedIncomeService.saveDeletedIncome(deletedIncome);
            incomeStringService.deleteIncomeById(incomeString.getId());
            incomeMainService.saveIncomeMain(incomeMain);
        }
        return "redirect:/show_income_main/" +
                incomeString.getIncomeMain().getId() + "/" + deleteUserName;
    }

    @GetMapping({"/show_income_string/{incomeStringId}/{editUserName}",
            "show_income_main/{incomeMainId}/show_income_string/{incomeStringId}/{editUserName}"})
    public String  showIncomeString(@PathVariable ("incomeStringId") Long incomeStringId,
                                    @PathVariable ("editUserName") String editUserName,
                                    @PathVariable ("incomeMainId") Long incomeMainId,
                                    Model model) {
        IncomeString incomeString = incomeStringService.getIncomeById(incomeStringId);
        List<Item> allItems = itemService.getAllItems();
        model.addAttribute("items", allItems);
        incomeString.setPurchasePriceDouble(incomeString.getPurchasePrice()/100d);
        incomeString.setPurchasePriceActDouble(incomeString.getPurchasePriceAct()/100d);
        model.addAttribute("incomeStringForm", incomeString);
        model.addAttribute("date", LocalDateTime.now().format(dateTimeFormatter));
        return "storage/incomes/show_income_string";
    }

    @PostMapping({"/show_income_string/{incomeStringId}/{editUserName}",
            "show_income_main/{incomeMainId}/show_income_string/{incomeStringId}/{editUserName}"})
    public String  showIncomeString(@ModelAttribute ("incomeStringForm") IncomeString incomeString,
                                    @PathVariable ("incomeStringId") Long incomeStringId,
                                    @PathVariable ("editUserName") String editUserName,
                                    @PathVariable ("incomeMainId") Long incomeMainId,
                                    Model model) {
        IncomeString incomeStringFromDb = incomeStringService.getIncomeById(incomeStringId);
        incomeString.setPurchasePrice((int) (incomeString.getPurchasePriceDouble() * 100));
        incomeString.setPurchasePriceAct((int) (incomeString.getPurchasePriceActDouble() * 100));

        EditedIncomeString editedIncomeString = new EditedIncomeString(incomeStringFromDb, incomeString,
                LocalDateTime.now().format(dateTimeFormatter), editUserName);

        editIncomeStringService.saveEditedIncomeString(editedIncomeString);
        incomeStringService.saveIncome(incomeString);
        return "redirect:/show_income_main/" + incomeMainId + "/" + editUserName;
    }
}
