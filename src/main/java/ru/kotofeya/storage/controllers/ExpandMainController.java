package ru.kotofeya.storage.controllers;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kotofeya.storage.model.*;
import ru.kotofeya.storage.service.ExpandMainService;
import ru.kotofeya.storage.service.ExpandStringService;
import ru.kotofeya.storage.service.IncomeMainService;
import ru.kotofeya.storage.service.ItemService;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");

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
        List<Item> allItems = itemService.getAllItems().stream().filter(it->it.getCount()>0).collect(Collectors.toList());
        model.addAttribute("items", allItems);
        model.addAttribute("eans", allItems.stream().map(it->it.getEan()).collect(Collectors.toSet()));
        model.addAttribute("expandMainForm", new ExpandMain());
        model.addAttribute("date", LocalDateTime.now().format(dateTimeFormatter));
        model.addAttribute("expandString", new ExpandString());
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

