package ru.kotofeya.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kotofeya.storage.model.IncomeMain;
import ru.kotofeya.storage.repo.IncomeMainRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class IncomeMainService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    IncomeMainRepo incomeMainRepo;

    @Transactional
    public List<IncomeMain> getAllIncomesMain(){
        return incomeMainRepo.findAll();
    }

    @Transactional
    public void saveIncomeMain(IncomeMain incomeMain){
        incomeMainRepo.save(incomeMain);
    }
    @Transactional
    public IncomeMain findById(long id){
        return incomeMainRepo.findById(id).orElse(null);
    }
    @Transactional
    public void deleteIncomeMainById(long id){
        incomeMainRepo.deleteById(id);
    }
}
