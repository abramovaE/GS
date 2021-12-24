package ru.kotofeya.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kotofeya.storage.model.*;
import ru.kotofeya.storage.repo.ExpandMainRepo;
import ru.kotofeya.storage.repo.ExpandStringRepo;
import ru.kotofeya.storage.repo.ItemRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ExpandStringService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    ExpandStringRepo expandStringRepo;
    @Autowired
    ItemRepo itemRepo;
    public void saveExpand(ExpandString expandString){
        ExpandString expandStringFromDb;
        if(expandString.getId() == null){
            expandStringFromDb = new ExpandString();
        } else {
            expandStringFromDb = expandStringRepo.findById(expandString.getId()).orElse(new ExpandString());
        }
        Item item = itemRepo.findById(expandString.getItem().getId()).orElse(null);
        correctItemCount(item, expandStringFromDb.getCount(), expandString.getCount());
        expandStringRepo.save(expandString);
    }
    @Transactional
    public void correctItemCount(Item item, int oldCount, int newCount){
        if(item != null){
            int currentCount = item.getCount() == null? 0 : item.getCount();
            item.setCount(currentCount + oldCount - newCount);
            itemRepo.save(item);
        }
    }
    @Transactional
    public List<ExpandString> findByExpandMain(ExpandMain expandMain){
        return expandStringRepo.findExpandStringByExpandMain(expandMain);
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