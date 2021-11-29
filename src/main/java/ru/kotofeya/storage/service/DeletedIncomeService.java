package ru.kotofeya.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kotofeya.storage.model.DeletedIncome;
import ru.kotofeya.storage.model.Expand;
import ru.kotofeya.storage.repo.DeletedIncomeRepository;
import ru.kotofeya.storage.repo.ExpandRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class DeletedIncomeService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    DeletedIncomeRepository deletedIncomeRepository;

    @Transactional
    public void saveDeletedIncome(DeletedIncome deletedIncome){
        deletedIncomeRepository.save(deletedIncome);
    }
}
