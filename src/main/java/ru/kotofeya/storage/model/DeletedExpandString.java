package ru.kotofeya.storage.model;

import javax.persistence.*;

@Entity
@Table(name = "deleted_expand_string")
public class DeletedExpandString {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "create_user_name")
    private String createUserName;
    @Column(name = "delete_user_name")
    private String deleteUserName;
    @Column(name = "delete_date")
    private String deleteDate;
    @Column(name = "create_date")
    private String createDate;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "count")
    private int count;
    @Column(name = "sale_price")
    private int salePrice;
    @Column(name = "batch_number")
    private int batchNumber;
    @Column(name = "expand_main_id")
    private long expandMainId;

    public DeletedExpandString() {}
    public DeletedExpandString(ExpandString expandString, String deleteDate, String deleteUserName) {
        this.createUserName = expandString.getUserName();
        this.deleteUserName = deleteUserName;
        this.createDate = expandString.getDate();
        this.deleteDate = deleteDate;
        this.itemName = expandString.getItem().getName();
        this.count = expandString.getCount();
        this.salePrice = expandString.getSalePrice();
        this.batchNumber = expandString.getBatchNumber();
        this.expandMainId = expandString.getExpandMain().getId();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCreateUserName() {
        return createUserName;
    }
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }
    public String getDeleteUserName() {
        return deleteUserName;
    }
    public void setDeleteUserName(String deleteUserName) {
        this.deleteUserName = deleteUserName;
    }
    public String getDeleteDate() {
        return deleteDate;
    }
    public void setDeleteDate(String deleteDate) {
        this.deleteDate = deleteDate;
    }
    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public int getSalePrice() {
        return salePrice;
    }
    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }
    public int getBatchNumber() {
        return batchNumber;
    }
    public void setBatchNumber(int batchNumber) {
        this.batchNumber = batchNumber;
    }
    public long getExpandMainId() {return expandMainId;}
    public void setExpandMainId(long expandMainId) {this.expandMainId = expandMainId;}
}
