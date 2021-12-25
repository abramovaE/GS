package ru.kotofeya.storage.service.expands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kotofeya.storage.model.expands.EditedExpandMain;
import ru.kotofeya.storage.repo.expands.EditedExpandMainRepo;

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
