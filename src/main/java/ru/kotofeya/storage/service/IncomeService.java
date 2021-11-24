package ru.kotofeya.storage.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kotofeya.storage.model.Income;
import ru.kotofeya.storage.model.Item;
import ru.kotofeya.storage.repo.IncomeRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class IncomeService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    IncomeRepository incomeRepository;

    @Transactional
    public List<Income> getTodayIncomes(String date) {
        return incomeRepository.findIncomeByDate(date);
    }

    @Transactional
    public void saveIncome(Income income){
        Income incomeFromDb;
        if(income.getId() == null){
            incomeFromDb = new Income();
        } else {
            incomeFromDb = incomeRepository.findById(income.getId()).orElse(new Income());
        }
        incomeFromDb.setDate(income.getDate());
        incomeFromDb.setItem(income.getItem());
        incomeFromDb.setBatchNumber(income.getBatchNumber());
        incomeFromDb.setCount(income.getCount());
        incomeFromDb.setUserName(income.getUserName());
        incomeFromDb.setPurchasePrice(income.getPurchasePrice());
        incomeFromDb.setPurchasePriceAct(income.getPurchasePriceAct());
        incomeFromDb.setStoreArticle(income.getStore());
        incomeFromDb.setStoreArticle(income.getStoreArticle());
        incomeRepository.save(incomeFromDb);
    }
}
