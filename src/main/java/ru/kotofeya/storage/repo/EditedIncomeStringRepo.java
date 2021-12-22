package ru.kotofeya.storage.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.storage.model.EditedIncomeString;

public interface EditedIncomeStringRepo extends JpaRepository<EditedIncomeString, Long> {
}
