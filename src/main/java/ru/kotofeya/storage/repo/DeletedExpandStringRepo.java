package ru.kotofeya.storage.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.storage.model.DeletedExpandString;

public interface DeletedExpandStringRepo extends JpaRepository<DeletedExpandString, Long> {
}
