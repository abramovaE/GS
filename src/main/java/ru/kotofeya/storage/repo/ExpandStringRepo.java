package ru.kotofeya.storage.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.storage.model.ExpandMain;
import ru.kotofeya.storage.model.ExpandString;
import ru.kotofeya.storage.model.IncomeMain;
import ru.kotofeya.storage.model.IncomeString;

import java.util.List;

public interface ExpandStringRepo extends JpaRepository<ExpandString, Long> {
    List<ExpandString> findExpandsByDate(String date);
    List<ExpandString> findExpandStringByExpandMain(ExpandMain expandMain);

}
