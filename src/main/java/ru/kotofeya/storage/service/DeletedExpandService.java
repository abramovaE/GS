package ru.kotofeya.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kotofeya.storage.model.DeletedExpand;
import ru.kotofeya.storage.model.DeletedIncome;
import ru.kotofeya.storage.repo.DeletedExpandRepository;
import ru.kotofeya.storage.repo.DeletedIncomeRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class DeletedExpandService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    DeletedExpandRepository deletedExpandRepository;

    @Transactional
    public void saveDeletedExpand(DeletedExpand deletedExpand){
        deletedExpandRepository.save(deletedExpand);
    }
}
