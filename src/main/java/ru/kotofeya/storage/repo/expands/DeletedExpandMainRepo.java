package ru.kotofeya.storage.repo.expands;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.storage.model.expands.DeletedExpandMain;

public interface DeletedExpandMainRepo extends JpaRepository<DeletedExpandMain, Long> {
}
