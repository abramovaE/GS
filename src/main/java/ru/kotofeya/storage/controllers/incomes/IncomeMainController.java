package ru.kotofeya.storage.controllers.incomes;

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
import ru.kotofeya.features.M11Creator;
import ru.kotofeya.storage.model.*;
import ru.kotofeya.storage.model.incomes.*;
import ru.kotofeya.storage.service.*;
import ru.kotofeya.storage.service.incomes.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class IncomeMainController {
    @Autowired
    private IncomeMainService incomeMainService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private IncomeStringService incomeStringService;
    @Autowired
    private DeletedIncomeStringService deletedIncomeStringService;
    @Autowired
    private EditedIncomeMainService editedIncomeMainService;
    @Autowired
    private DeletedIncomeMainService deletedIncomeMainService;

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @GetMapping("/add_income_main")
    public String  addIncomeMain(Model model) {
        List<Item> allItems = itemService.getAllItems();
        model.addAttribute("items", allItems);
        model.addAttribute("eans", allItems.stream().map(it->it.getEan()).collect(Collectors.toSet()));
        model.addAttribute("incomeMainForm", new IncomeMain());
        model.addAttribute("date", LocalDateTime.now().format(dateTimeFormatter));
        model.addAttribute("incomeJson", new String());
        model.addAttribute("incomeString", new IncomeString());
        return "storage/incomes/add_income_main";
    }

    @PostMapping("/add_income_main")
    public String  addIncomeMain(Model model,
                                   @ModelAttribute ("incomeMainForm") IncomeMain incomeMain,
                                   @ModelAttribute ("incomeJson") String incomeJson) {
        incomeMainService.saveIncomeMain(incomeMain);
        Set<IncomeString> incomeStrings = createIncomeStringsFromJson(incomeJson, incomeMain.getId());
        incomeStrings.stream().forEach(it->incomeStringService.saveIncome(it));
        incomeMain.setIncomeStrings(incomeStrings);
        incomeMainService.saveIncomeMain(incomeMain);
        return "redirect:/incomes_main";
    }

    @GetMapping("/show_income_main/{incomeId}/{editUserName}")
    public String showIncomeMain(Model model,
                                    @PathVariable("incomeId") Long incomeId,
                                    @PathVariable("editUserName") String editUserName) {
        List<Item> allItems = itemService.getAllItems();
        IncomeMain incomeMain = incomeMainService.findById(incomeId);
        incomeMain = setSumsForJsp(incomeMain);
        model.addAttribute("incomeMain", incomeMain);
        model.addAttribute("items", allItems);
        model.addAttribute("eans", allItems.stream().map(it->it.getEan()).collect(Collectors.toSet()));
        return "storage/incomes/show_income_main";
    }

    @PostMapping("/show_income_main/{incomeId}/{editUserName}")
    public String  showIncomeMain(Model model,
                                   @ModelAttribute ("incomeMain") IncomeMain incomeMain,
                                   @ModelAttribute ("incomeJson") String incomeJson,
                                   @PathVariable("editUserName") String editUserName) {
        IncomeMain incomeMainFromDb = incomeMainService.findById(incomeMain.getId());
        Set<IncomeString> incomeStringListDb = incomeMainFromDb.getIncomeStrings();
        List<Long> incomeStringIds = new ArrayList<>();
        incomeStringListDb.stream().forEach(
                it->incomeStringIds.add(it.getId()));
        Set<IncomeString> incomeStrings = createIncomeStringsFromJson(incomeJson, incomeMain.getId());
        incomeStrings.stream().forEach(it->incomeStringService.saveIncome(it));
        List<IncomeString> incomeStringList = incomeStringService.findByIncomeMain(incomeMain);
        EditedIncomeMain editedIncomeMain = new EditedIncomeMain(incomeMainFromDb,
                incomeMain,LocalDateTime.now().format(dateTimeFormatter), editUserName, incomeStringList);
        incomeMainFromDb.setDate(incomeMain.getDate());
        incomeMainFromDb.setStore(incomeMain.getStore());
        incomeMainFromDb.setUserName(incomeMain.getUserName());
        incomeMainService.saveIncomeMain(incomeMainFromDb);
        editedIncomeMainService.saveEditedIncomeMain(editedIncomeMain);
        return "redirect:/incomes_main";
    }

    @GetMapping("/incomes_main")
    public String getAllIncomesMain(Model model){
        List<IncomeMain> incomesMain = incomeMainService.getAllIncomesMain();
        for(IncomeMain incomeMain: incomesMain){
            setSumsForJsp(incomeMain);
        }
        model.addAttribute("incomesMain", incomesMain);
        return "storage/incomes/incomes_main";
    }

    @GetMapping("/delete_income_main/{incomeMainId}/{deleteUserName}")
    public String deleteIncome(@PathVariable ("incomeMainId") Long incomeMainId,
                               @PathVariable ("deleteUserName") String deleteUserName,
                               Model model) {
        IncomeMain incomeMain = incomeMainService.findById(incomeMainId);
        if(incomeMain != null){
            Set<IncomeString> incomeStrings = incomeMain.getIncomeStrings();
            for(IncomeString incomeString: incomeStrings){
                DeletedIncomeString deletedIncome = new DeletedIncomeString(incomeString,
                        LocalDateTime.now().format(dateTimeFormatter),
                        deleteUserName);
                deletedIncomeStringService.saveDeletedIncome(deletedIncome);
                incomeStringService.deleteIncomeById(incomeString.getId());
            }
            DeletedIncomeMain deletedIncomeMain = new DeletedIncomeMain(incomeMain,
                    LocalDateTime.now().format(dateTimeFormatter), deleteUserName);
            deletedIncomeMainService.saveDeletedIncomeMain(deletedIncomeMain);
            incomeMainService.deleteIncomeMainById(incomeMain.getId());
        }
        return "redirect:/incomes_main";
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

    private Set<IncomeString> createIncomeStringsFromJson(String incomeJson, Long incomeMainId){
        IncomeMain incomeMain = incomeMainService.findById(incomeMainId);
        Gson gson = new Gson();
        Type listType = new TypeToken<List<IncomeJson>>(){}.getType();
        List<IncomeJson> incomeJsonList = gson.fromJson(incomeJson, listType);
        Set<IncomeString> incomeStrings = new HashSet<>();
        if(incomeJsonList != null) {
            for (IncomeJson i : incomeJsonList) {
                IncomeString incomeString = new IncomeString();
                incomeString.setUserName(incomeMain.getUserName());
                incomeString.setDate(incomeMain.getDate());
                incomeString.setItem(itemService.getById(i.getItemId()));
                incomeString.setCount(i.getCount());
                incomeString.setPurchasePrice((int) (i.getPurPrice() * 100));
                incomeString.setPurchasePriceAct((int) (i.getPurPriceAct() * 100));
                incomeString.setStoreArticle(i.getStoreArticle());
                incomeString.setBatchNumber(i.getBatchNumber());
                incomeString.setIncomeMain(incomeMain);
                incomeStrings.add(incomeString);
            }
        }
        return incomeStrings;
    }
}

class IncomeJson{
    @Expose
    private int itemId;
    @Expose
    private int count;
    @Expose
    private double purPrice;
    @Expose
    private double purPriceAct;
    @Expose
    private String storeArticle;
    @Expose
    private int batchNumber;

    public IncomeJson() {}
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
    public double getPurPrice() {
        return purPrice;
    }
    public void setPurPrice(double purPrice) {
        this.purPrice = purPrice;
    }
    public double getPurPriceAct() {
        return purPriceAct;
    }
    public void setPurPriceAct(double purPriceAct) {
        this.purPriceAct = purPriceAct;
    }
    public String getStoreArticle() {
        return storeArticle;
    }
    public void setStoreArticle(String storeArticle) {
        this.storeArticle = storeArticle;
    }
    public int getBatchNumber() {
        return batchNumber;
    }
    public void setBatchNumber(int batchNumber) {
        this.batchNumber = batchNumber;
    }

    @Override
    public String toString() {
        return "IncomeJson{" +
                "itemId=" + itemId +
                ", count=" + count +
                ", purPrice=" + purPrice +
                ", purPriceAct=" + purPriceAct +
                ", storeArticle='" + storeArticle + '\'' +
                ", batchNumber=" + batchNumber +
                '}';
    }
}
