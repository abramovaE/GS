package ru.kotofeya.balance.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotofeya.balance.model.Money;

public interface MoneyRepo extends JpaRepository<Money, Long> {
}
