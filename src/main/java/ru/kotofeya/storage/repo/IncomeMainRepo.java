package ru.kotofeya.storage.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.storage.model.IncomeMain;

public interface IncomeMainRepo extends JpaRepository<IncomeMain, Long> {
}
