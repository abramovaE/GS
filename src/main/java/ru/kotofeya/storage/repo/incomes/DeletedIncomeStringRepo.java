package ru.kotofeya.storage.repo.incomes;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.storage.model.incomes.DeletedIncomeString;

public interface DeletedIncomeStringRepo extends JpaRepository<DeletedIncomeString, Long> {
}
