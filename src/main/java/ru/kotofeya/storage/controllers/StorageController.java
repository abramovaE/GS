package ru.kotofeya.storage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kotofeya.storage.model.expands.ExpandString;
import ru.kotofeya.storage.model.incomes.IncomeString;
import ru.kotofeya.storage.model.items.Item;
import ru.kotofeya.storage.service.ItemService;
import ru.kotofeya.storage.service.expands.ExpandStringService;
import ru.kotofeya.storage.service.incomes.IncomeStringService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class StorageController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private IncomeStringService incomeStringService;
    @Autowired
    private ExpandStringService expandStringService;

    @GetMapping("/show_storage")
    public String  showStorage(Model model) {
        model.addAttribute("count", getCount(model));
        List<Item> items = getItems(model)
                .stream().filter(it->(it.getCount() != null && it.getCount() > 0))
                .collect(Collectors.toList());

        setMiddlePrice(items);
        model.addAttribute("items", items);

        return "storage/items/all_items";
    }

    private void setMiddlePrice(List<Item> items){
        for(Item item: items){
            List<IncomeString> incomes = incomeStringService.getAllItemIncomes(item);
            List<ExpandString> expands = expandStringService.getAllItemExpands(item);
            int cost = 0;
            for(IncomeString incomeString: incomes){
                cost = cost + incomeString.getPurchasePriceAct() * incomeString.getCount();
            }
            for(ExpandString expandString: expands){
                cost = cost - expandString.getSalePrice() * expandString.getCount();
            }
            if(item.getCount() != null && item.getCount() > 0) {
                item.setMiddlePrice(cost / item.getCount());
            }
            else {
                item.setMiddlePrice(0);
            }
        }
    }

    private int getCount(Model model){
        Integer count = (Integer) model.asMap().get("count");
        return (count == null) ? 0 : count;
    }

    private List<Item> getItems(Model model){
        List<Item> items = (List<Item>) model.asMap().get("items");
        return (items == null) ? itemService.getAllItems() : items;
    }

    @GetMapping("/items_main")
    public String getAllItems(Model model) {
        model.addAttribute("count", getCount(model));
        model.addAttribute("items", getItems(model));
        return "storage/items/items_main";
    }

    @GetMapping("/allItems/sortBy/{sortParam}/{count}")
    public String  showStorage(Model model, @PathVariable ("sortParam") String sortParam,
                               @PathVariable("count") Integer count) {
        final int c = count%2;
        List<Item> allItems = itemService.getAllItems()
                .stream().filter(it->it.getCount() != null && it.getCount() > 0)
                .collect(Collectors.toList());
        setMiddlePrice(allItems);
        sortItems(allItems, sortParam, c);
        model.addAttribute("items", allItems);
        count++;
        model.addAttribute("count", count);
        return showStorage(model);
    }

    @GetMapping("/items_main/sortBy/{sortParam}/{count}")
    public String  showItems(Model model, @PathVariable ("sortParam") String sortParam,
                             @PathVariable("count") Integer count) {
        final int c = count%2;
        List<Item> allItems = itemService.getAllItems();
        setMiddlePrice(allItems);
        sortItems(allItems, sortParam, c);
        model.addAttribute("items", allItems);
        count++;
        model.addAttribute("count", count);
        return getAllItems(model);
    }

    private void sortItems(List<Item> allItems, String sortParam, int c){
        Collections.sort(allItems, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                switch (sortParam) {
                    case "article":
                        return (c == 0) ? compareItems(o1.getArticle(), o2.getArticle()) :
                                compareItems(o2.getArticle(), o1.getArticle());
                    case "name":
                        return (c == 0) ? compareItems(o1.getName(), o2.getName()):
                                compareItems(o2.getName(), o1.getName());
                    case "artYandex":
                        return (c == 0) ? compareItems(o1.getYandexArt(), o2.getYandexArt()):
                                compareItems(o2.getYandexArt(), o1.getYandexArt());
                    case "artSber":
                        return (c == 0) ? compareItems(o1.getSberArt(), o2.getSberArt()):
                                compareItems(o2.getSberArt(), o1.getSberArt());
                    case "linkYandex":
                        return (c == 0) ? compareItems(o1.getMpLinkYandex(), o2.getMpLinkYandex()):
                                compareItems(o2.getMpLinkYandex(), o1.getMpLinkYandex());
                    case "linkSber":
                        return (c == 0) ? compareItems(o1.getMpLinkSber(), o2.getMpLinkSber()):
                                compareItems(o2.getMpLinkSber(), o1.getMpLinkSber());
                    case "ean":
                        return (c == 0) ? compareItems(o1.getEan(), o2.getEan()):
                                compareItems(o2.getEan(), o1.getEan());
                    case "username":
                        return (c == 0) ? compareItems(o1.getUserName(), o2.getUserName()):
                                compareItems(o2.getUserName(), o1.getUserName());
                    case "date":
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                        LocalDate localDate1 = LocalDate.parse(validateDate(o1.getDate()), formatter);
                        LocalDate localDate2 = LocalDate.parse(validateDate(o2.getDate()), formatter);
                        return (c == 0) ? localDate1.compareTo(localDate2)
                                : localDate2.compareTo(localDate1);
                    case "count":
                        return (c == 0) ? compareInts(o1.getCount(), o2.getCount()) :
                                compareInts(o2.getCount(), o1.getCount());
                    case "middlePrice":
                        return (c == 0) ? compareInts(o1.getMiddlePrice(), o2.getMiddlePrice()) :
                                compareInts(o2.getMiddlePrice(), o1.getMiddlePrice());
                    case "sum":
                        return (c == 0) ? compareInts(o1.getMiddlePrice() * o1.getCount(), o2.getMiddlePrice() * o2.getCount()) :
                                compareInts(o2.getMiddlePrice() * o2.getCount(), o1.getMiddlePrice() * o1.getCount());
                    default:
                        return o1.getId().compareTo(o2.getId());
                }
            }
        });
    }



    private String validateDate(String date1){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        System.out.println(date1);


        if(date1 != null && !date1.isEmpty() && date1.split("\\.")[2].length() == 2){
            date1 = date1.split("\\.")[0] + "." +
                    date1.split("\\.")[1] + ".20" +
                    date1.split("\\.")[2];
        }
        if (date1 == null || date1.isEmpty()) {
            date1 = LocalDate.now().format(formatter);
        }
        return  date1;
    }

    private int compareInts(Integer o1, Integer o2){
        int o1Count = (o1 == null) ? 0 : o1;
        int o2Count = (o2 == null) ? 0 : o2;
        return o1Count - o2Count;
    }

    private int compareItems(String o1, String o2){
        o1 = (o1 == null) ? "" : o1;
        o2 = (o2 == null) ? "" : o2;
            return o1.compareTo(o2);
        }
}

