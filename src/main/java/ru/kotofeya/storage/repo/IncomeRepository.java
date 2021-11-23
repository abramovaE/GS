package ru.kotofeya.storage.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.storage.model.Income;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findIncomeByDate(String date);
}
