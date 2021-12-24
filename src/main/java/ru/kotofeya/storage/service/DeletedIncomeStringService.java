package ru.kotofeya.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kotofeya.storage.model.DeletedIncomeString;
import ru.kotofeya.storage.repo.DeletedIncomeStringRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class DeletedIncomeStringService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    DeletedIncomeStringRepo deletedIncomeStringRepo;

    @Transactional
    public void saveDeletedIncome(DeletedIncomeString deletedIncome){
        deletedIncomeStringRepo.save(deletedIncome);
    }

}
