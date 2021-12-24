package ru.kotofeya.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kotofeya.storage.model.EditedExpandString;
import ru.kotofeya.storage.repo.EditedExpandStringRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class EditedExpandStringService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    EditedExpandStringRepo editedExpandStringRepo;
    @Transactional
    public void saveEditedExpandString(EditedExpandString editedExpandString){
        editedExpandStringRepo.save(editedExpandString);
    }
}
