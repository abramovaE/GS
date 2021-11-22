package ru.kotofeya.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kotofeya.storage.repo.ExpandRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class ExpandService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    ExpandRepository expandRepository;
}
