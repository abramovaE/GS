package ru.kotofeya.storage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kotofeya.storage.model.items.Item;
import ru.kotofeya.storage.service.ItemService;

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

    @GetMapping("/show_storage")
    public String  showStorage(Model model) {
        model.addAttribute("count", getCount(model));
        model.addAttribute("items", getItems(model)
                .stream().filter(it->it.getCount() > 0)
                .collect(Collectors.toList()));
        return "storage/items/all_items";
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
                .stream().filter(it->it.getCount() > 0)
                .collect(Collectors.toList());
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
        sortItems(allItems, sortParam, c);
        model.addAttribute("items", allItems);
        count++;
        model.addAttribute("count", count);
        return getAllItems(model);
    }


    private void sortItems(List<Item> allItems, String sortParam, int c){
        switch (sortParam){
            case "article":
                Collections.sort(allItems, new Comparator<Item>() {
                    @Override
                    public int compare(Item o1, Item o2) {
                        return (c == 0) ? compareItems(o1.getArticle(), o2.getArticle()):
                                compareItems(o2.getArticle(), o1.getArticle());
                    }
                });
                break;
            case "name":
                Collections.sort(allItems, new Comparator<Item>() {
                    @Override
                    public int compare(Item o1, Item o2) {
                        return (c == 0) ? compareItems(o1.getName(), o2.getName()):
                                compareItems(o2.getName(), o1.getName());
                    }
                });
                break;
            case "type":
                Collections.sort(allItems, new Comparator<Item>() {
                    @Override
                    public int compare(Item o1, Item o2) {
                        return (c == 0) ? compareItems(o1.getMarketplaceArt(), o2.getMarketplaceArt()):
                                compareItems(o2.getMarketplaceArt(), o1.getMarketplaceArt());
                    }
                });
                break;
            case "link":
                Collections.sort(allItems, new Comparator<Item>() {
                    @Override
                    public int compare(Item o1, Item o2) {
                        return (c == 0) ? compareItems(o1.getMpLink(), o2.getMpLink()):
                                compareItems(o2.getMpLink(), o1.getMpLink());
                    }
                });
                break;
            case "ean":
                Collections.sort(allItems, new Comparator<Item>() {
                    @Override
                    public int compare(Item o1, Item o2) {
                        return (c == 0) ? compareItems(o1.getEan(), o2.getEan()):
                                compareItems(o2.getEan(), o1.getEan());
                    }
                });
                break;
            case "username":
                Collections.sort(allItems, new Comparator<Item>() {
                    @Override
                    public int compare(Item o1, Item o2) {
                        return (c == 0) ? compareItems(o1.getUserName(), o2.getUserName()):
                                compareItems(o2.getUserName(), o1.getUserName());
                    }
                });
                break;
            case "date":
                Collections.sort(allItems, new Comparator<Item>() {
                    @Override
                    public int compare(Item o1, Item o2) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                        String date1 = o1.getDate();
                        String date2 = o2.getDate();
                        if(date1.split("\\.")[2].length() == 2){
                            date1 = date1.split("\\.")[0] + "." +
                                    date1.split("\\.")[1] + ".20" +
                                    date1.split("\\.")[2];
                        }
                        if(date2.split("\\.")[2].length() == 2){
                            date2 = date2.split("\\.")[0] + "." +
                                    date2.split("\\.")[1] + ".20" +
                                    date2.split("\\.")[2];
                        }
                        if (date1 == null) {
                            date1 = LocalDate.now().format(formatter);
                        }
                        if (date2 == null) {
                            date2 = LocalDate.now().format(formatter);
                        }
                        LocalDate localDate1 = LocalDate.parse(date1, formatter);
                        LocalDate localDate2 = LocalDate.parse(date2, formatter);
                        return (c == 0) ? localDate1.compareTo(localDate2)
                                : localDate2.compareTo(localDate1);
                    }
                });
                break;
            case "count":
                Collections.sort(allItems, new Comparator<Item>() {
                    @Override
                    public int compare(Item o1, Item o2) {
                        return (c == 0) ? compareInts(o1.getCount(), o2.getCount()) :
                                compareInts(o2.getCount(), o1.getCount());
                    }
                });
                break;
            default:
                break;
        }
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