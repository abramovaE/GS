package ru.kotofeya.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kotofeya.storage.repo.ItemRepository;
import ru.kotofeya.storage.repo.StorageRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class StorageService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    StorageRepository storageRepository;


}
