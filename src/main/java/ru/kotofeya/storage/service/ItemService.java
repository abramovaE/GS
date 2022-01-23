package ru.kotofeya.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kotofeya.storage.model.expands.ExpandString;
import ru.kotofeya.storage.model.incomes.IncomeString;
import ru.kotofeya.storage.model.items.Item;
import ru.kotofeya.storage.repo.ItemRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ItemService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    ItemRepo itemRepo;

    @Transactional
    public void saveItem(Item item){
        System.out.println("saveItem: " + item);
        itemRepo.save(item);
    }

    @Transactional
    public void deleteItem(long id){
        itemRepo.deleteById(id);
    }

    @Transactional
    public Item getById(long id){
        return itemRepo.findById(id).orElse(null);
    }

    @Transactional
    public List<Item> getAllItems(){
        return itemRepo.findAll();
    }

    @Transactional
    public List<Item> getAllItemsWithCount(){
        return itemRepo.findByCountIsNotNull();
    }

    @Transactional
    public Item findMaxIdItem(){
        return itemRepo.findTopByOrderByIdDesc().orElse(null);
    }

}
