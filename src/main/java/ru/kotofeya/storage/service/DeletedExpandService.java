package ru.kotofeya.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kotofeya.storage.model.DeletedExpandString;
import ru.kotofeya.storage.repo.DeletedExpandStringRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class DeletedExpandService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    DeletedExpandStringRepo deletedExpandStringRepo;

    @Transactional
    public void saveDeletedExpand(DeletedExpandString deletedExpandString){
        deletedExpandStringRepo.save(deletedExpandString);
    }
}
