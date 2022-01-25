package ru.kotofeya.storage.model.incomes;


import ru.kotofeya.storage.model.items.Item;

import javax.persistence.*;

@Entity
@Table(name = "income_string")
public class IncomeString {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "date")
    private String date;
    @ManyToOne
    private Item item;
    @Column(name = "count")
    private int count;
    @Column(name = "purchase_price")
    private int purchasePrice;
    @Column(name = "purchase_price_act")
    private int purchasePriceAct;
    @Column(name = "store_article")
    private String storeArticle;
    @Transient
    private double purchasePriceDouble;
    @Transient
    private double purchasePriceActDouble;
    @ManyToOne(fetch = FetchType.EAGER)
    private IncomeMain incomeMain;
    @Column(name = "note")
    private String note;

    public IncomeString() {}
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}
    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}
    public Item getItem() {return item;}
    public void setItem(Item item) {this.item = item;}
    public int getCount() {return count;}
    public void setCount(int count) {this.count = count;}
    public int getPurchasePrice() {return purchasePrice;}
    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
    public int getPurchasePriceAct() {return purchasePriceAct;}
    public void setPurchasePriceAct(int purchasePriceAct) {
        this.purchasePriceAct = purchasePriceAct;
    }
    public String getStoreArticle() {return storeArticle;}
    public void setStoreArticle(String storeArticle) {
        this.storeArticle = storeArticle;
    }
    public double getPurchasePriceDouble() {return purchasePriceDouble;}
    public void setPurchasePriceDouble(double purchasePriceDouble) {
        this.purchasePriceDouble = purchasePriceDouble;
    }
    public double getPurchasePriceActDouble() {
        return purchasePriceActDouble;
    }
    public void setPurchasePriceActDouble(double purchasePriceActDouble) {
        this.purchasePriceActDouble = purchasePriceActDouble;
    }
    public IncomeMain getIncomeMain() {return incomeMain;}
    public void setIncomeMain(IncomeMain incomeMain) {this.incomeMain = incomeMain;}

    public String getNote() {return note;}
    public void setNote(String note) {this.note = note;}

    @Override
    public String toString() {
        return "Income{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", date='" + date + '\'' +
                ", count=" + count +
                ", purchasePrice=" + purchasePrice +
                ", purchasePriceAct=" + purchasePriceAct +
                ", storeArticle='" + storeArticle + '\'' +
                '}';
    }
}
