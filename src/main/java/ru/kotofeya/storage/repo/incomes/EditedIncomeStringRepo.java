package ru.kotofeya.storage.repo.incomes;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.storage.model.incomes.EditedIncomeString;

public interface EditedIncomeStringRepo extends JpaRepository<EditedIncomeString, Long> {
}
