package ru.kotofeya.storage.model;

import javax.persistence.*;

@Entity
@Table(name = "income")
public class Income {
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
    @Column(name = "store")
    private String store;
    @Column(name = "batch_number")
    private int batchNumber;

    @Transient
    private double purchasePriceDouble;
    @Transient
    private double purchasePriceActDouble;

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
    public String getStore() {return store;}
    public void setStore(String store) {this.store = store;}
    public int getBatchNumber() {return batchNumber;}
    public void setBatchNumber(int batchNumber) {
        this.batchNumber = batchNumber;
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

    @Override
    public String toString() {
        return "Income{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", date='" + date + '\'' +
//                ", item=" + item +
                ", count=" + count +
                ", purchasePrice=" + purchasePrice +
                ", purchasePriceAct=" + purchasePriceAct +
                ", storeArticle='" + storeArticle + '\'' +
                ", store='" + store + '\'' +
                ", batchNumber=" + batchNumber +
                '}';
    }
}
