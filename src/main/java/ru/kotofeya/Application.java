package ru.kotofeya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}


//при удалении строки в show_income_main записывать также в edited_income_main
//при редактировании main entities сохраняется только одна строка
//отредактированная дата не сохраняется add date field to edited entities;
//check correctItemCount
//create expand
//delete expand
//edit expand
//
//create income blank
//create expand blank
//
//календать для выбора даты