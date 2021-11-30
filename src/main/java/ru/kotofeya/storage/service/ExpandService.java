package ru.kotofeya.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kotofeya.storage.model.Expand;
import ru.kotofeya.storage.model.Income;
import ru.kotofeya.storage.repo.ExpandRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ExpandService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    ExpandRepository expandRepository;

    @Transactional
    public List<Expand> getTodayExpands(String date) {
        return expandRepository.findExpandsByDate(date);
    }

    public void saveExpand(Expand expand){
        Expand expandFromDb;
        if(expand.getId() == null){
            expandFromDb = new Expand();
        } else {
            expandFromDb = expandRepository.findById(expand.getId()).orElse(new Expand());
        }
        expandFromDb.setDate(expand.getDate());
        expandFromDb.setItem(expand.getItem());
        expandFromDb.setBatchNumber(expand.getBatchNumber());
        expandFromDb.setCount(expand.getCount());
        expandFromDb.setUserName(expand.getUserName());
        expandFromDb.setSalePrice((expand.getSalePrice()));
        expandRepository.save(expandFromDb);
    }

    @Transactional
    public Expand getExpandById(long id){
        return expandRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteExpandById(long id){
        expandRepository.deleteById(id);
    }
}
