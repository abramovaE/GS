package ru.kotofeya.storage.controllers.expands;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kotofeya.storage.model.expands.*;
import ru.kotofeya.storage.model.incomes.IncomeString;
import ru.kotofeya.storage.model.items.Item;
import ru.kotofeya.storage.service.*;
import ru.kotofeya.storage.service.expands.*;
import ru.kotofeya.storage.service.incomes.IncomeStringService;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class ExpandMainController {

    @Autowired
    private ExpandMainService expandMainService;
    @Autowired
    private ExpandStringService expandStringService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private EditedExpandMainService editedExpandMainService;
    @Autowired
    private DeletedExpandStringService deletedExpandStringService;
    @Autowired
    private DeletedExpandMainService deletedExpandMainService;
    @Autowired
    private IncomeStringService incomeStringService;


    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @GetMapping("/expands_main")
    public String getAllExpandsMain(Model model){
        List<ExpandMain> expandsMains = expandMainService.getAllExpandsMain();
        for(ExpandMain expandMain: expandsMains){
            setSumsForJsp(expandMain);
        }
        model.addAttribute("expandsMain", expandsMains);
        return "storage/expands/expands_main";
    }

    @GetMapping("/add_expand_main")
    public String  addExpandMain(Model model) {
        List<Item> allItems = itemService.getAllItems().stream().filter(it->(it.getCount() != null && it.getCount()>0))
                .collect(Collectors.toList());
        model.addAttribute("items", allItems);
        model.addAttribute("eans", allItems.stream().map(it->it.getEan()).collect(Collectors.toSet()));
        model.addAttribute("expandMainForm", new ExpandMain());
        model.addAttribute("date", LocalDateTime.now().format(dateTimeFormatter));
        model.addAttribute("expandString", new ExpandString());
        for(Item item: allItems){
            List<IncomeString> incomes = incomeStringService.getAllItemIncomes(item);
            List<ExpandString> expands = expandStringService.getAllItemExpands(item);
            int cost = 0;
            for(IncomeString incomeString: incomes){
                cost = cost + incomeString.getPurchasePriceAct() * incomeString.getCount();
            }
            for(ExpandString expandString: expands){
                cost = cost - expandString.getSalePrice() * expandString.getCount();
            }
            item.setMiddlePrice(cost);
        }
        return "storage/expands/add_expand_main";
    }

    @PostMapping("/add_expand_main")
    public String  addExpandMain(Model model,
                                   @ModelAttribute("expandMainForm") ExpandMain expandMain,
                                   @ModelAttribute ("expandJson") String expandJson) {
        expandMainService.saveExpandMain(expandMain);
        Set<ExpandString> expandStrings = createExpandStringsFromJson(expandJson, expandMain.getId());
        expandStrings.stream().forEach(it->expandStringService.saveExpand(it));
        expandMain.setExpandStrings(expandStrings);
        expandMainService.saveExpandMain(expandMain);
        return "redirect:/expands_main";
    }


    @GetMapping("/show_expand_main/{expandId}/{editUserName}")
    public String showExpandMain(Model model,
                                   @PathVariable("expandId") Long expandId,
                                   @PathVariable("editUserName") String editUserName) {
        List<Item> allItems = itemService.getAllItems();
        ExpandMain expandMain = expandMainService.findById(expandId);
        expandMain = setSumsForJsp(expandMain);
        model.addAttribute("expandMain", expandMain);
        model.addAttribute("items", allItems);
        model.addAttribute("eans", allItems.stream().map(it->it.getEan()).collect(Collectors.toSet()));
        return "storage/expands/show_expand_main";
    }


    @PostMapping("/show_expand_main/{expandId}/{editUserName}")
    public String showExpandMain(Model model,
                                    @ModelAttribute ("expandMain") ExpandMain expandMain,
                                    @ModelAttribute ("expandJson") String expandJson,
                                    @PathVariable("editUserName") String editUserName) {
        ExpandMain expandMainFromDb = expandMainService.findById(expandMain.getId());
        Set<ExpandString> expandStringsListDb = expandMainFromDb.getExpandStrings();
        List<Long> expandStringIds = new ArrayList<>();
        expandStringsListDb.stream().forEach(it->expandStringIds.add(it.getId()));
        Set<ExpandString> expandStrings = createExpandStringsFromJson(expandJson, expandMain.getId());
        expandStrings.stream().forEach(it->expandStringService.saveExpand(it));
        List<ExpandString> expandStringList = expandStringService.findByExpandMain(expandMain);
        EditedExpandMain editedExpandMain = new EditedExpandMain(expandMainFromDb, expandMain,
                LocalDateTime.now().format(dateTimeFormatter), editUserName, expandStringList);
        expandMainFromDb.setDate(expandMain.getDate());
        expandMainFromDb.setStore(expandMain.getStore());
        expandMainFromDb.setUserName(expandMain.getUserName());
        expandMainService.saveExpandMain(expandMainFromDb);
        editedExpandMainService.saveEditedExpandMain(editedExpandMain);
        return "redirect:/expands_main";
    }


    @GetMapping("/delete_expand_main/{expandMainId}/{deleteUserName}")
    public String deleteExpand(@PathVariable ("expandMainId") Long expandMainId,
                               @PathVariable ("deleteUserName") String deleteUserName,
                               Model model) {
        ExpandMain expandMain = expandMainService.findById(expandMainId);
        if(expandMain != null){
            Set<ExpandString> expandStrings = expandMain.getExpandStrings();
            for(ExpandString expandString: expandStrings){
                DeletedExpandString deletedExpand = new DeletedExpandString(expandString,
                        LocalDateTime.now().format(dateTimeFormatter),
                        deleteUserName);
                deletedExpandStringService.saveDeletedExpand(deletedExpand);
                expandStringService.deleteExpandById(expandString.getId());
            }
            DeletedExpandMain deletedExpandMain = new DeletedExpandMain(expandMain,
                    LocalDateTime.now().format(dateTimeFormatter),
                    deleteUserName);
            deletedExpandMainService.saveDeletedExpandMain(deletedExpandMain);
            expandMainService.deleteExpandMainById(expandMain.getId());
        }
        return "redirect:/expands_main";
    }









    private Set<ExpandString> createExpandStringsFromJson(String expandJson, Long expandMainId){
        ExpandMain expandMain = expandMainService.findById(expandMainId);
        Gson gson = new Gson();
        Type listType = new TypeToken<List<ExpandJson>>(){}.getType();
        List<ExpandJson> expandJsonList = gson.fromJson(expandJson, listType);
        Set<ExpandString> expandStrings = new HashSet<>();
        for(ExpandJson i: expandJsonList){
            ExpandString expandString = new ExpandString();
            expandString.setUserName(expandMain.getUserName());
            expandString.setDate(expandMain.getDate());
            expandString.setItem(itemService.getById(i.getItemId()));
            expandString.setCount(i.getCount());
            expandString.setSalePrice((int) (i.getPrice() * 100));
            expandString.setBatchNumber(i.getBatchNumber());
            expandString.setExpandMain(expandMain);
            expandStrings.add(expandString);
        }
        return expandStrings;
    }

    private ExpandMain setSumsForJsp(ExpandMain expandMain){
        int sum = 0;
        List<ExpandString> expandStrings = expandStringService.findByExpandMain(expandMain);
        for(ExpandString expandString: expandStrings){
            sum += expandString.getCount() * expandString.getSalePrice();
        }
        expandMain.setSum(sum);
        return expandMain;
    }
}

class ExpandJson{
    @Expose
    private int itemId;
    @Expose
    private int count;
    @Expose
    private double price;
    @Expose
    private int batchNumber;

    public ExpandJson() {}
    public int getItemId() {
        return itemId;
    }
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double purPrice) {
        this.price = purPrice;
    }
    public int getBatchNumber() {
        return batchNumber;
    }
    public void setBatchNumber(int batchNumber) {
        this.batchNumber = batchNumber;
    }

    @Override
    public String toString() {
        return "ExpandJson{" +
                "itemId=" + itemId +
                ", count=" + count +
                ", price=" + price +
                ", batchNumber=" + batchNumber +
                '}';
    }
}

