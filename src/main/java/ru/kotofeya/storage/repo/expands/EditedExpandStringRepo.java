package ru.kotofeya.storage.repo.expands;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.storage.model.expands.EditedExpandString;

public interface EditedExpandStringRepo extends JpaRepository<EditedExpandString, Long> {
}
