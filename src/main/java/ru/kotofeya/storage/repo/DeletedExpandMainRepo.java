package ru.kotofeya.storage.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.storage.model.DeletedExpandMain;

public interface DeletedExpandMainRepo extends JpaRepository<DeletedExpandMain, Long> {
}
