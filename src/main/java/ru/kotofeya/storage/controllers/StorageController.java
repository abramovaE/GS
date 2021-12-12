package ru.kotofeya.storage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kotofeya.storage.model.Item;
import ru.kotofeya.storage.service.ItemService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class StorageController {

    @Autowired
    private ItemService itemService;

    // TODO: 22.11.2021 сделать актуальные остатки
    @GetMapping("/show_storage")
    public String  showStorage(Model model) {
        Integer count = (Integer) model.asMap().get("count");
        List<Item> items = (List<Item>) model.asMap().get("items");
        if(count == null){
            count = 0;
        }
        if(items == null){
            items = itemService.getAllItems();
        }
        model.addAttribute("count", count);
        model.addAttribute("items", items);
        return "storage/all_items";
    }

    @GetMapping("/allItems/sortBy/{sortParam}/{count}")
    public String  showStorage(Model model, @PathVariable ("sortParam") String sortParam,
                               @PathVariable("count") Integer count) {
        final int c = count%2;
        List<Item> allItems = itemService.getAllItems();
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
                        return (c == 0) ? compareItems(o1.getType(), o2.getType()):
                                compareItems(o2.getType(), o1.getType());
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

                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy");
                            String date1 = o1.getDate();
                            String date2 = o2.getDate();
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
                        Integer o1Count = o1.getCount();
                        Integer o2Count = o2.getCount();
                        if(o1Count == null){
                            o1Count = 0;
                        }
                        if(o2Count == null){
                            o2Count = 0;
                        }
                        return (c == 0) ? o1Count - o2Count : o2Count - o1Count;
                    }
                });
                break;
            default:
                break;
        }
        model.addAttribute("items", allItems);
        count++;
        model.addAttribute("count", count);
        return showStorage(model);
    }

    private int compareItems(String o1, String o2){
            if(o1 == null){
                o1 = "";
            }
            if(o2 == null){
                o2 = "";
            }
            return o1.compareTo(o2);
        }
}