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
        incomeFromDb.setPurchasePrice((int) (income.getPurchasePriceDouble() * 100));
        incomeFromDb.setPurchasePriceAct((int) (income.getPurchasePriceActDouble() * 100));
        incomeFromDb.setDate(income.getDate());
        incomeFromDb.setItem(income.getItem());
        incomeFromDb.setBatchNumber(income.getBatchNumber());
        incomeFromDb.setCount(income.getCount());
        incomeFromDb.setUserName(income.getUserName());
        incomeFromDb.setStore(income.getStore());
        incomeFromDb.setStoreArticle(income.getStoreArticle());
        incomeRepository.save(incomeFromDb);
    }

    @Transactional
    public Income getIncomeById(long id){
        return incomeRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteIncomeById(long id){
        incomeRepository.deleteById(id);
    }
}
