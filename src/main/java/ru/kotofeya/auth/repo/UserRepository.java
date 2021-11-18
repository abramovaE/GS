package ru.kotofeya.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.auth.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
