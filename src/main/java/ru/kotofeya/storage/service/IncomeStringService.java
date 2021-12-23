package ru.kotofeya.storage.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kotofeya.storage.model.IncomeMain;
import ru.kotofeya.storage.model.IncomeString;
import ru.kotofeya.storage.model.Item;
import ru.kotofeya.storage.repo.IncomeStringRepo;
import ru.kotofeya.storage.repo.ItemRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class IncomeStringService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    IncomeStringRepo incomeStringRepo;
    @Autowired
    ItemRepo itemRepo;

    @Transactional
    public List<IncomeString> findByIncomeMain(IncomeMain incomeMain){
        return incomeStringRepo.findIncomeStringByIncomeMain(incomeMain);
    }

    @Transactional
    public void saveIncome(IncomeString incomeString){
        IncomeString incomeStringFromDb;
        if(incomeString.getId() == null){
            incomeStringFromDb = new IncomeString();
        } else {
            incomeStringFromDb = incomeStringRepo.findById(incomeString.getId()).orElse(new IncomeString());
        }
        Item item = itemRepo.findById(incomeString.getItem().getId()).orElse(null);
        correctItemCount(item, incomeStringFromDb.getCount(), incomeString.getCount());
        incomeStringRepo.save(incomeString);
    }

    @Transactional
    public IncomeString getIncomeById(long id){
        return incomeStringRepo.findById(id).orElse(null);
    }

    @Transactional
    public void deleteIncomeById(long id){
        IncomeString incomeString = incomeStringRepo.findById(id).orElse(new IncomeString());
        Item item = itemRepo.findById(incomeString.getItem().getId()).orElse(null);
        correctItemCount(item, incomeString.getCount(), 0);
        incomeStringRepo.deleteById(id);
    }

    @Transactional
    public void correctItemCount(Item item, int oldCount, int newCount){
        if(item != null){
            int currentCount = item.getCount() == null? 0 : item.getCount();
            item.setCount(currentCount - oldCount + newCount);
            itemRepo.save(item);
        }
    }

    @Transactional
    public List<IncomeString> getAllIncomes(){
        return incomeStringRepo.findAll();
    }

}
