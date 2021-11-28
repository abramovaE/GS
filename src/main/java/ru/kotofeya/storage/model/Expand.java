package ru.kotofeya.storage.model;

import javax.persistence.*;

@Entity
@Table(name = "expand")
public class Expand {
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
    @Column(name = "sale_price")
    private int salePrice;
    @Column(name = "batch_number")
    private int batchNumber;

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
    public int getSalePrice() {return salePrice;}
    public void setSalePrice(int salePrice) {this.salePrice = salePrice;}
    public int getBatchNumber() {return batchNumber;}
    public void setBatchNumber(int batchNumber) {
        this.batchNumber = batchNumber;
    }
}
