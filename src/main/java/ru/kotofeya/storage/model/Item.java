package ru.kotofeya.storage.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kotofeya.auth.model.Role;
import ru.kotofeya.auth.model.User;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "article")
    private String article;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "ean")
    private String ean;
    @Transient
    @ManyToMany(mappedBy = "items")
    private Set<Storage> storages;

    public Item() {}
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getArticle() {return article;}
    public void setArticle(String article) {this.article = article;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}
    public String getEan() {return ean;}
    public void setEan(String ean) {this.ean = ean;}
    public Set<Storage> getStorages() {return storages;}
    public void setStorages(Set<Storage> storages) {this.storages = storages;}
}
