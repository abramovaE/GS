package ru.kotofeya.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
