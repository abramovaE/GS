package ru.kotofeya.storage.model;

import javax.persistence.*;
import java.util.List;

public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Item item;
    private Integer count;

}
