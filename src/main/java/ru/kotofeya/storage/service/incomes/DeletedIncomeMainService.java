package ru.kotofeya.storage.service.incomes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kotofeya.storage.model.incomes.DeletedIncomeMain;
import ru.kotofeya.storage.repo.incomes.DeletedIncomeMainRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class DeletedIncomeMainService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    DeletedIncomeMainRepo deletedIncomeMainRepo;

    @Transactional
    public void saveDeletedIncomeMain(DeletedIncomeMain deletedIncomeMain){
        deletedIncomeMainRepo.save(deletedIncomeMain);
    }
}
