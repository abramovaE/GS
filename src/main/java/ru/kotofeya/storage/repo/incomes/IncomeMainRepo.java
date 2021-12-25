package ru.kotofeya.storage.repo.incomes;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.storage.model.incomes.IncomeMain;

public interface IncomeMainRepo extends JpaRepository<IncomeMain, Long> {
}
