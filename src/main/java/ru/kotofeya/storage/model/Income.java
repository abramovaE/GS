package ru.kotofeya.storage.model;

import javax.persistence.*;

@Entity
@Table(name = "income")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "user_id")
    private int userId;
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

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public int getUserId() {return userId;}
    public void setUserId(int userId) {this.userId = userId;}
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
}
