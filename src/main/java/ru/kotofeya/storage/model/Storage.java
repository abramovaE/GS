package ru.kotofeya.storage.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "storage")
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Item> items;

    public Storage() {}
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public Set<Item> getItems() {return items;}
    public void setItems(Set<Item> items) {this.items = items;}
}
