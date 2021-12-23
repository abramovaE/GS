package ru.kotofeya.storage.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.storage.model.IncomeMain;
import ru.kotofeya.storage.model.IncomeString;

import java.util.List;

public interface IncomeStringRepo extends JpaRepository<IncomeString, Long> {
    List<IncomeString> findIncomeByDate(String date);
    List<IncomeString> findIncomeStringByIncomeMain(IncomeMain incomeMain);
}
