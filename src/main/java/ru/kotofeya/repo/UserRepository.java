package ru.kotofeya.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
