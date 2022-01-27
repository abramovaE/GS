package ru.kotofeya.balance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kotofeya.balance.model.Money;
import ru.kotofeya.balance.repo.MoneyRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class MoneyService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private MoneyRepo moneyRepo;

    @Transactional
    public void saveMoney(Money money){
        moneyRepo.save(money);
    }

    @Transactional
    public List<Money> findAllMoney(){
        return moneyRepo.findAll();
    }
}
