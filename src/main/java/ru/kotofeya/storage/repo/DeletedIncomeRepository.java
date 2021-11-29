package ru.kotofeya.storage.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.storage.model.DeletedIncome;

public interface DeletedIncomeRepository  extends JpaRepository<DeletedIncome, Long> {
}
