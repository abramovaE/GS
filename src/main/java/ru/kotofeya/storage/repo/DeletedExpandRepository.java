package ru.kotofeya.storage.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.storage.model.DeletedExpand;

public interface DeletedExpandRepository  extends JpaRepository<DeletedExpand, Long> {
}
