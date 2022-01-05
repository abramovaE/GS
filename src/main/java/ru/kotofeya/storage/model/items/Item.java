package ru.kotofeya.storage.model.items;

import ru.kotofeya.storage.model.expands.ExpandString;
import ru.kotofeya.storage.model.incomes.IncomeString;

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
    @Column(name = "marketplace_article")
    private String marketplaceArt;
    @Column(name = "ean")
    private String ean;
    @Column(name = "user_name")
    private String userName;
    //dd.MM.yyyy
    @Column(name = "date")
    private String date;
    @Column(name = "count")
    private Integer count;
    @Column(name = "mp_link")
    private String mpLink;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "item")
    private Set<IncomeString> incomeStrings;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "item")
    private Set<ExpandString> expandStrings;

    @Transient
    private int middlePrice;

    public Item() {}
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getArticle() {return article;}
    public void setArticle(String article) {this.article = article;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getMarketplaceArt() {return marketplaceArt;}
    public void setMarketplaceArt(String type) {this.marketplaceArt = type;}
    public String getEan() {return ean;}
    public void setEan(String ean) {this.ean = ean;}
    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}
    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}
    public Integer getCount() {return count;}
    public void setCount(Integer count) {this.count = count;}
    public String getMpLink() {return mpLink;}
    public void setMpLink(String mpLink) {this.mpLink = mpLink;}
    public int getMiddlePrice() {return middlePrice;}
    public void setMiddlePrice(int middlePrice) {this.middlePrice = middlePrice;}

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", article='" + article + '\'' +
                ", name='" + name + '\'' +
                ", type='" + marketplaceArt + '\'' +
                ", ean='" + ean + '\'' +
                ", userName='" + userName + '\'' +
                ", date='" + date + '\'' +
                ", count=" + count +
                ", middlePrice=" + middlePrice +

//                ", incomeStrings=" + incomeStrings +
//                ", expandStrings=" + expandStrings +
                '}';
    }
}
//
//купили
//10 шт по 100р
//15 шт по 90р
//20 шт по 80р
//средняя = 3950/45 = 87,7
//продали по 87,7 * 45 = 3950 ,
//        на складе теперь 0
//
//купили 10шт по 50р
//средняя 4450/55 = 80,9р
//продали по 80,9 * 10шт = 809