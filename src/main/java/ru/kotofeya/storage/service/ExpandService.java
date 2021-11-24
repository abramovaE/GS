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

}
