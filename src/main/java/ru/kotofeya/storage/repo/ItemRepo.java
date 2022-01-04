package ru.kotofeya.storage.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.storage.model.items.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepo extends JpaRepository<Item, Long> {
    List<Item> findByCountIsNotNull();
    @Override
    Optional<Item> findById(Long aLong);
    Optional<Item> findTopByOrderByIdDesc();
}
