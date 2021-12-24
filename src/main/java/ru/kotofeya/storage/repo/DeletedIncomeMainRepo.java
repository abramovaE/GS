package ru.kotofeya.storage.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.storage.model.DeletedExpandMain;
import ru.kotofeya.storage.model.DeletedIncomeMain;

public interface DeletedIncomeMainRepo extends JpaRepository<DeletedIncomeMain, Long> {
}
