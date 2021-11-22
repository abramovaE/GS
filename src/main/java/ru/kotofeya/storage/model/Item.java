package ru.kotofeya.storage.model;

import javax.persistence.*;
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
    @Column(name = "user_id")
    private Integer userId;
    //dd.MM.yyyy
    @Column(name = "date")
    private String date;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Income> incomes;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Expand> expands;

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
    public Integer getUserId() {return userId;}
    public void setUserId(Integer userId) {this.userId = userId;}
    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}

}
