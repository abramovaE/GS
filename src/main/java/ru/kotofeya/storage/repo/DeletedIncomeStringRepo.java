package ru.kotofeya.storage.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.storage.model.DeletedIncomeString;

public interface DeletedIncomeStringRepo extends JpaRepository<DeletedIncomeString, Long> {
}
