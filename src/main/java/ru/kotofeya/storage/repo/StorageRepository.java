package ru.kotofeya.storage.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.storage.model.Storage;

public interface StorageRepository extends JpaRepository<Storage, Long> {
}
