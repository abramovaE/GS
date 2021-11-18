package ru.kotofeya.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.auth.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
