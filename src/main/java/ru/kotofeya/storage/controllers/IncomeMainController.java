package ru.kotofeya.storage.controllers;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kotofeya.storage.model.IncomeMain;
import ru.kotofeya.storage.model.IncomeString;
import ru.kotofeya.storage.model.Item;
import ru.kotofeya.storage.service.IncomeMainService;
import ru.kotofeya.storage.service.IncomeStringService;
import ru.kotofeya.storage.service.ItemService;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class IncomeMainController {
    @Autowired
    private IncomeMainService incomeMainService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private IncomeStringService incomeStringService;

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");

    @GetMapping("/add_income_main")
    public String  addIncomeString(Model model) {
        List<Item> allItems = itemService.getAllItems();
        System.out.println("allitems size: " + allItems);
        model.addAttribute("items", allItems);
        model.addAttribute("eans", allItems.stream().map(it->it.getEan()).collect(Collectors.toSet()));
        model.addAttribute("incomeMainForm", new IncomeMain());
        model.addAttribute("date", LocalDateTime.now().format(dateTimeFormatter));
        model.addAttribute("incomeJson", new String());
        model.addAttribute("incomeString", new IncomeString());
        return "storage/add_income_main";
    }

    @PostMapping("/add_income_main")
    public String  addIncomeString(Model model,
                                   @ModelAttribute ("incomeMainForm") IncomeMain incomeMain,
                                   @ModelAttribute ("incomeJson") String incomeJson) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<IncomeJson>>(){}.getType();
        List<IncomeJson> incomeJsonList = gson.fromJson(incomeJson, listType);
        Set<IncomeString> incomeStrings = new HashSet<>();
        for(IncomeJson i: incomeJsonList){
            IncomeString incomeString = new IncomeString();
            incomeString.setUserName(incomeMain.getUserName());
            incomeString.setDate(incomeMain.getDate());
            incomeString.setItem(itemService.getById(i.getItemId()));
            incomeString.setCount(i.getCount());
            incomeString.setPurchasePrice((int) (i.getPurPrice() * 100));
            incomeString.setPurchasePriceAct((int) (i.getPurPriceAct() * 100));
            incomeString.setStoreArticle(i.getStoreArticle());
            incomeString.setStore(i.getStore());
            incomeString.setBatchNumber(i.getBatchNumber());
            incomeString.setIncomeMain(incomeMain);
            incomeStrings.add(incomeString);
        }
        incomeMain.setIncomeStrings(incomeStrings);
        incomeMainService.saveIncomeMain(incomeMain);
        return "redirect:/incomes_main";
    }

    @GetMapping("/show_income_main/{incomeId}")
    public String  showIncomeString(Model model,
                                    @PathVariable("incomeId") Long incomeId) {
        List<Item> allItems = itemService.getAllItems();
        IncomeMain incomeMain = incomeMainService.findById(incomeId);
        model.addAttribute("incomeMain", incomeMain);
        int sum = 0;
            int sumAct = 0;
            List<IncomeString> incomeStrings = incomeStringService.getAllIncomes();
            for(IncomeString incomeString: incomeStrings){
                if(incomeString.getIncomeMain() != null &&
                        incomeString.getIncomeMain().getId() != null &&
                        incomeString.getIncomeMain().getId().equals(incomeMain.getId())){
                    sum += incomeString.getCount() * incomeString.getPurchasePrice();
                    sumAct += incomeString.getCount() * incomeString.getPurchasePriceAct();
                }
            incomeMain.setSum(sum);
            incomeMain.setSumAct(sumAct);
        }

        model.addAttribute("items", allItems);
        model.addAttribute("eans", allItems.stream().map(it->it.getEan()).collect(Collectors.toSet()));
        model.addAttribute("incomeMainForm", new IncomeMain());
        model.addAttribute("date", LocalDateTime.now().format(dateTimeFormatter));
        model.addAttribute("incomeJson", new String());
        model.addAttribute("incomeString", new IncomeString());
        return "storage/show_income_main";
    }

    @GetMapping("/incomes_main")
    public String getAllIncomesMAin(Model model){
        List<IncomeMain> incomesMain = incomeMainService.getAllIncomesMain();
        for(IncomeMain incomeMain: incomesMain){
            int sum = 0;
            int sumAct = 0;
            List<IncomeString> incomeStrings = incomeStringService.getAllIncomes();

            for(IncomeString incomeString: incomeStrings){
                if(incomeString.getIncomeMain() != null &&
                        incomeString.getIncomeMain().getId() != null &&
                        incomeString.getIncomeMain().getId().equals(incomeMain.getId())){
                    sum += incomeString.getCount() * incomeString.getPurchasePrice();
                    sumAct += incomeString.getCount() * incomeString.getPurchasePriceAct();
                }
            }
            incomeMain.setSum(sum);
            incomeMain.setSumAct(sumAct);
        }
        model.addAttribute("incomesMain", incomesMain);
        return "storage/incomes_main";
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
    private String store;
    @Expose
    private int batchNumber;

    public IncomeJson() {
    }
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
    public String getStore() {
        return store;
    }
    public void setStore(String store) {
        this.store = store;
    }
    public int getBatchNumber() {
        return batchNumber;
    }
    public void setBatchNumber(int batchNumber) {
        this.batchNumber = batchNumber;
    }
}

