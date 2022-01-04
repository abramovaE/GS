package ru.kotofeya.storage.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.storage.model.items.EditedItem;

public interface EditItemRepo extends JpaRepository<EditedItem, Long> {
}
