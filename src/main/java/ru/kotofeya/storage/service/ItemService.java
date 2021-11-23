package ru.kotofeya.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kotofeya.storage.model.Item;
import ru.kotofeya.storage.repo.ItemRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ItemService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        Item itemFromDb;
        if(item.getId() == null){
            itemFromDb = new Item();
        } else {
            itemFromDb = itemRepository.findById(item.getId()).orElse(new Item());
        }
        itemFromDb.setArticle(item.getArticle());
        itemFromDb.setName(item.getName());
        itemFromDb.setType(item.getType());
        itemFromDb.setEan(item.getEan());
        itemFromDb.setUserName(item.getUserName());
        itemFromDb.setDate(item.getDate());
        itemRepository.save(itemFromDb);
    }

    @Transactional
    public void deleteItem(long id){
        itemRepository.deleteById(id);
    }

    @Transactional
    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }
}
