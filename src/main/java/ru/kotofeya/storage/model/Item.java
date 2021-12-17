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
    @Column(name = "user_name")
    private String userName;
    //dd.MM.yyyy
    @Column(name = "date")
    private String date;
    @Column(name = "count")
    private Integer count;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "item")
    private Set<IncomeString> incomeStrings;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "item")
    private Set<ExpandString> expandStrings;



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
    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}
    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}
    public Integer getCount() {return count;}
    public void setCount(Integer count) {this.count = count;}

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", article='" + article + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", ean='" + ean + '\'' +
                ", userName='" + userName + '\'' +
                ", date='" + date + '\'' +
                ", incomes=" + incomeStrings +
                ", expands=" + expandStrings +
                '}';
    }
}
