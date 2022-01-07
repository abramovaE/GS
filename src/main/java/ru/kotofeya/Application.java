package ru.kotofeya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

//    После создания товара - идёт перенаправление на остатки,
//    приход при котором было вызвано создание товара при этом теряется


