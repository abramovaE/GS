package ru.kotofeya.storage.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kotofeya.storage.model.IncomeString;
import ru.kotofeya.storage.repo.IncomeStringRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class IncomeStringService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    IncomeStringRepo incomeStringRepo;

    @Transactional
    public List<IncomeString> getTodayIncomes(String date) {
        return incomeStringRepo.findIncomeByDate(date);
    }

    @Transactional
    public void saveIncome(IncomeString incomeString){
        IncomeString incomeStringFromDb;
        if(incomeString.getId() == null){
            incomeStringFromDb = new IncomeString();
        } else {
            incomeStringFromDb = incomeStringRepo.findById(incomeString.getId()).orElse(new IncomeString());
        }
        incomeStringFromDb.setPurchasePrice(incomeString.getPurchasePrice());
        incomeStringFromDb.setPurchasePriceAct(incomeString.getPurchasePriceAct());
        incomeStringFromDb.setDate(incomeString.getDate());
        incomeStringFromDb.setItem(incomeString.getItem());
        incomeStringFromDb.setBatchNumber(incomeString.getBatchNumber());
        incomeStringFromDb.setCount(incomeString.getCount());
        incomeStringFromDb.setUserName(incomeString.getUserName());
//        incomeStringFromDb.setStore(incomeString.getStore());
        incomeStringFromDb.setStoreArticle(incomeString.getStoreArticle());
        incomeStringFromDb.setIncomeMain(incomeString.getIncomeMain());
        incomeStringRepo.save(incomeStringFromDb);
    }

    @Transactional
    public IncomeString getIncomeById(long id){
        return incomeStringRepo.findById(id).orElse(null);
    }

    @Transactional
    public void deleteIncomeById(long id){
        incomeStringRepo.deleteById(id);
    }
    @Transactional
    public List<IncomeString> getAllIncomes(){
        return incomeStringRepo.findAll();
    }

}
