package ru.kotofeya.storage.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.storage.model.Income;

public interface IncomeRepository extends JpaRepository<Income, Long> {
}
