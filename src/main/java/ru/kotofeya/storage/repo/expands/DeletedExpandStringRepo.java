package ru.kotofeya.storage.repo.expands;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.storage.model.expands.DeletedExpandString;

public interface DeletedExpandStringRepo extends JpaRepository<DeletedExpandString, Long> {
}
