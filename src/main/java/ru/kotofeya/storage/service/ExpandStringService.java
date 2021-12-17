package ru.kotofeya.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kotofeya.storage.model.ExpandString;
import ru.kotofeya.storage.repo.ExpandStringRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ExpandStringService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    ExpandStringRepo expandStringRepo;

    @Transactional
    public List<ExpandString> getTodayExpands(String date) {
        return expandStringRepo.findExpandsByDate(date);
    }

    public void saveExpand(ExpandString expandString){
        ExpandString expandStringFromDb;
        if(expandString.getId() == null){
            expandStringFromDb = new ExpandString();
        } else {
            expandStringFromDb = expandStringRepo.findById(expandString.getId()).orElse(new ExpandString());
        }
        expandStringFromDb.setDate(expandString.getDate());
        expandStringFromDb.setItem(expandString.getItem());
        expandStringFromDb.setBatchNumber(expandString.getBatchNumber());
        expandStringFromDb.setCount(expandString.getCount());
        expandStringFromDb.setUserName(expandString.getUserName());
        expandStringFromDb.setSalePrice((expandString.getSalePrice()));
        expandStringRepo.save(expandStringFromDb);
    }

    @Transactional
    public ExpandString getExpandById(long id){
        return expandStringRepo.findById(id).orElse(null);
    }

    @Transactional
    public void deleteExpandById(long id){
        expandStringRepo.deleteById(id);
    }

    @Transactional
    public List<ExpandString> getAllExpands(){
        return expandStringRepo.findAll();
    }
}
