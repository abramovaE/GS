package ru.kotofeya.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kotofeya.storage.model.DeletedExpandMain;
import ru.kotofeya.storage.model.DeletedExpandString;
import ru.kotofeya.storage.repo.DeletedExpandMainRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class DeletedExpandMainService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    DeletedExpandMainRepo deletedExpandMainRepo;

    @Transactional
    public void saveDeletedExpandMain(DeletedExpandMain deletedExpandMain){
        deletedExpandMainRepo.save(deletedExpandMain);
    }
}
