package ru.kotofeya.storage.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.storage.model.EditedIncomeMain;

public interface EditedIncomeMainRepo extends JpaRepository<EditedIncomeMain, Long> {

}
