package ru.kotofeya.storage.repo.incomes;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.storage.model.incomes.DeletedIncomeMain;

public interface DeletedIncomeMainRepo extends JpaRepository<DeletedIncomeMain, Long> {
}
