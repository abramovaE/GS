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
//        IncomeMain incomeMainFromDb;
//        if(incomeMain.getId() == null){
//            incomeMainFromDb = new IncomeMain();
//        } else {
//            incomeMainFromDb = incomeMainRepo.findById(incomeMain.getId()).orElse(new IncomeMain());
//        }
//        incomeMainFromDb.setDate(incomeMain.getDate());
//        incomeMainFromDb.setStore(incomeMain.getStore());
//        incomeMainFromDb.setUserName(incomeMain.getUserName());
//        incomeMainFromDb.setIncomeStrings(incomeMain.getIncomeStrings());
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


// income main id = 102
//strings = 34, 35
//items = 6, 1


//id = 103;
//strings =


//id = 107
//strings = 40, 41
//items = 4 - 302
//        9 - -40;