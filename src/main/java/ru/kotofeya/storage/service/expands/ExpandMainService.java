package ru.kotofeya.storage.service.expands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kotofeya.storage.model.expands.ExpandMain;
import ru.kotofeya.storage.repo.expands.ExpandMainRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ExpandMainService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    ExpandMainRepo expandMainRepo;

    @Transactional
    public List<ExpandMain> getAllExpandsMain(){
        return expandMainRepo.findAll();
    }

    @Transactional
    public void saveExpandMain(ExpandMain expandMain){
        expandMainRepo.save(expandMain);
    }

    @Transactional
    public ExpandMain findById(long id){
        return expandMainRepo.findById(id).orElse(null);
    }

    @Transactional
    public void deleteExpandMainById(Long id){
        expandMainRepo.deleteById(id);
    }
}
