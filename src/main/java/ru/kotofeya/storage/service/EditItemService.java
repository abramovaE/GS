package ru.kotofeya.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kotofeya.storage.model.items.EditedItem;
import ru.kotofeya.storage.repo.EditItemRepo;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class EditItemService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    EditItemRepo editItemRepo;

    @Transactional
    public void save(EditedItem editedItem){
        editItemRepo.save(editedItem);
    }

    @Transactional
    public List<EditedItem> findAll (){ return editItemRepo.findAll();}

}
