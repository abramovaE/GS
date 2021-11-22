package ru.kotofeya.storage.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kotofeya.storage.repo.IncomeRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class IncomeService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    IncomeRepository incomeRepository;
}
