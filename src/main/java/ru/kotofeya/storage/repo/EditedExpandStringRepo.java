package ru.kotofeya.storage.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.storage.model.EditedExpandString;

public interface EditedExpandStringRepo extends JpaRepository<EditedExpandString, Long> {
}
