package ru.kotofeya.storage.repo.expands;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.storage.model.expands.ExpandMain;
import ru.kotofeya.storage.model.expands.ExpandString;
import ru.kotofeya.storage.model.items.Item;

import java.util.List;

public interface ExpandStringRepo extends JpaRepository<ExpandString, Long> {
    List<ExpandString> findExpandsByDate(String date);
    List<ExpandString> findExpandStringByExpandMain(ExpandMain expandMain);
    List<ExpandString> findExpandStringByItem(Item item);

}
