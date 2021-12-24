package ru.kotofeya.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kotofeya.storage.model.EditedExpandMain;
import ru.kotofeya.storage.repo.EditedExpandMainRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class EditedExpandMainService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    EditedExpandMainRepo editedExpandMainRepo;
    @Transactional
    public void saveEditedExpandMain(EditedExpandMain editedExpandMain){
        editedExpandMainRepo.save(editedExpandMain);
    }
}
