package ru.kotofeya.storage.repo.incomes;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.storage.model.incomes.IncomeMain;
import ru.kotofeya.storage.model.incomes.IncomeString;

import java.util.List;

public interface IncomeStringRepo extends JpaRepository<IncomeString, Long> {
    List<IncomeString> findIncomeByDate(String date);
    List<IncomeString> findIncomeStringByIncomeMain(IncomeMain incomeMain);
}
