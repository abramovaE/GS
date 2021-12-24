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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class ExpandStringController {
    @Autowired
    private ExpandStringService expandStringService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private DeletedExpandService deletedExpandService;
    @Autowired
    private ExpandMainService expandMainService;
    @Autowired
    private EditedExpandMainService editedExpandMainService;
    @Autowired
    private EditedExpandStringService editedExpandStringService;

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");

    @GetMapping({"/delete_expand_string/{expandStringId}/{deleteUserName}",
            "show_expand_main/{expandMainId}/delete_expand_string/{expandStringId}/{deleteUserName}"})
    public String deleteExpand(@PathVariable ("expandStringId") Long expandStringId,
                               @PathVariable ("deleteUserName") String deleteUserName,
                               @PathVariable ("expandMainId") Long expandMainId,
                               Model model) {
        ExpandString expandString = expandStringService.getExpandById(expandStringId);
        if(expandString != null){
            DeletedExpandString deletedExpand = new DeletedExpandString(expandString,
                    LocalDateTime.now().format(dateTimeFormatter),
                    deleteUserName);
            ExpandMain expandMain = expandMainService.findById(expandMainId);
            Set<ExpandString> expandStrings = new HashSet<>(expandMain.getExpandStrings());
            expandStrings.remove(expandString);
            EditedExpandMain editedExpandMain = new EditedExpandMain(expandMain, expandMain,
                    LocalDateTime.now().format(dateTimeFormatter),
                    deleteUserName, new ArrayList<>(expandStrings));
            expandMain.getExpandStrings().remove(expandString);
            editedExpandMainService.saveEditedExpandMain(editedExpandMain);
            deletedExpandService.saveDeletedExpand(deletedExpand);
            expandStringService.deleteExpandById(expandString.getId());
            expandMainService.saveExpandMain(expandMain);
        }
        return "redirect:/show_expand_main/" +
                expandString.getExpandMain().getId() + "/" + deleteUserName;
    }

     @GetMapping({"/show_expand_string/{expandStringId}/{editUserName}",
            "show_expand_main/{expandMainId}/show_expand_string/{expandStringId}/{editUserName}"})
    public String showExpandString(@PathVariable ("expandStringId") Long expandStringId,
                                    @PathVariable ("editUserName") String editUserName,
                                    @PathVariable ("expandMainId") Long expandMainId,
                                    Model model) {
        ExpandString expandString = expandStringService.getExpandById(expandStringId);
        List<Item> allItems = itemService.getAllItems();
        model.addAttribute("items", allItems);
        expandString.setSalePriceDouble(expandString.getSalePrice()/100d);
        model.addAttribute("expandStringForm", expandString);
        model.addAttribute("date", LocalDateTime.now().format(dateTimeFormatter));
        return "storage/expands/show_expand_string";
    }

    @PostMapping({"/show_expand_string/{expandStringId}/{editUserName}",
            "show_expand_main/{expandMainId}/show_expand_string/{expandStringId}/{editUserName}"})
    public String showExpandString(@ModelAttribute ("expandStringForm") ExpandString expandString,
                                    @PathVariable ("expandStringId") Long expandStringId,
                                    @PathVariable ("editUserName") String editUserName,
                                    @PathVariable ("expandMainId") Long expandMainId,
                                    Model model) {
        ExpandString expandStringFromDb = expandStringService.getExpandById(expandStringId);
        expandString.setSalePrice((int) (expandString.getSalePriceDouble() * 100));
        EditedExpandString editedExpandString = new EditedExpandString(expandStringFromDb, expandString,
                LocalDateTime.now().format(dateTimeFormatter), editUserName);
        editedExpandStringService.saveEditedExpandString(editedExpandString);
        expandStringService.saveExpand(expandString);
        return "redirect:/show_expand_main/" + expandMainId + "/" + editUserName;
    }
}