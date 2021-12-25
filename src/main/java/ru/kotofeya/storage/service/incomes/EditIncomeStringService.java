package ru.kotofeya.storage.service.incomes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kotofeya.storage.model.incomes.EditedIncomeString;
import ru.kotofeya.storage.repo.incomes.EditedIncomeStringRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class EditIncomeStringService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    EditedIncomeStringRepo editedIncomeStringRepo;
    @Transactional
    public void saveEditedIncomeString(EditedIncomeString editedIncomeString){
        editedIncomeStringRepo.save(editedIncomeString);
    }
}
