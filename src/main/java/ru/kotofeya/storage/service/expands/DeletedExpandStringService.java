package ru.kotofeya.storage.service.expands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kotofeya.storage.model.expands.DeletedExpandString;
import ru.kotofeya.storage.repo.expands.DeletedExpandStringRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class DeletedExpandStringService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    DeletedExpandStringRepo deletedExpandStringRepo;

    @Transactional
    public void saveDeletedExpand(DeletedExpandString deletedExpandString){
        deletedExpandStringRepo.save(deletedExpandString);
    }
}
