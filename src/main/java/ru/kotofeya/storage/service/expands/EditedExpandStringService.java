package ru.kotofeya.storage.service.expands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kotofeya.storage.model.expands.EditedExpandString;
import ru.kotofeya.storage.repo.expands.EditedExpandStringRepo;

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
