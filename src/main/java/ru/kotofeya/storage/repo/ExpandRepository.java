package ru.kotofeya.storage.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.storage.model.Expand;

import java.util.List;

public interface ExpandRepository  extends JpaRepository<Expand, Long> {
    List<Expand> findExpandsByDate(String date);

}
