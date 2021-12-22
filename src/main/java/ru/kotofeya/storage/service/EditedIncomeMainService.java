package ru.kotofeya.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kotofeya.storage.model.DeletedIncomeString;
import ru.kotofeya.storage.model.EditedIncomeMain;
import ru.kotofeya.storage.repo.DeletedIncomeStringRepo;
import ru.kotofeya.storage.repo.EditedIncomeMainRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class EditedIncomeMainService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    EditedIncomeMainRepo editedIncomeMainRepo;

    @Transactional
    public void saveEditedIncomeMain(EditedIncomeMain editedIncomeMain){
        editedIncomeMainRepo.save(editedIncomeMain);
    }
}