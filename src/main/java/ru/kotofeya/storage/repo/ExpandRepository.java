package ru.kotofeya.storage.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.storage.model.Expand;

public interface ExpandRepository  extends JpaRepository<Expand, Long> {
}
