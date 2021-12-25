package ru.kotofeya.storage.model.incomes;
import javax.persistence.*;

@Entity
@Table(name = "deleted_income_string")
public class DeletedIncomeString {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "income_string_id")
    private Long incomeStringId;
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
    @Column(name = "purchase_price")
    private int purchasePrice;
    @Column(name = "purchase_price_act")
    private int purchasePriceAct;
    @Column(name = "store_article")
    private String storeArticle;
    @Column(name = "batch_number")
    private int batchNumber;
    @Column(name = "income_main_id")
    private long incomeMainId;

    public DeletedIncomeString() {}

    public DeletedIncomeString(IncomeString incomeString, String deleteDate, String deleteUserName) {
        this.incomeStringId = incomeString.getId();
        this.createUserName = incomeString.getUserName();
        this.deleteUserName = deleteUserName;
        this.createDate = incomeString.getDate();
        this.deleteDate = deleteDate;
        this.itemName = incomeString.getItem().getName();
        this.count = incomeString.getCount();
        this.purchasePrice = incomeString.getPurchasePrice();
        this.purchasePriceAct = incomeString.getPurchasePriceAct();
        this.storeArticle = incomeString.getStoreArticle();
        this.batchNumber = incomeString.getBatchNumber();
        this.incomeMainId = incomeString.getIncomeMain().getId();
    }
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public Long getIncomeStringId() {return incomeStringId;}
    public void setIncomeStringId(Long incomeStringId) {this.incomeStringId = incomeStringId;}
    public String getCreateUserName() {return createUserName;}
    public void setCreateUserName(String createUserName) {this.createUserName = createUserName;}
    public String getDeleteUserName() {return deleteUserName;}
    public void setDeleteUserName(String deleteUserName) {this.deleteUserName = deleteUserName;}
    public String getDeleteDate() {return deleteDate;}
    public void setDeleteDate(String deleteDate) {this.deleteDate = deleteDate;}
    public String getCreateDate() {return createDate;}
    public void setCreateDate(String createDate) {this.createDate = createDate;}
    public String getItemName() {return itemName;}
    public void setItemName(String itemName) {this.itemName = itemName;}
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
//    public String getStore() {return store;}
//    public void setStore(String store) {this.store = store;}
    public int getBatchNumber() {return batchNumber;}
    public void setBatchNumber(int batchNumber) {
        this.batchNumber = batchNumber;
    }
    public long getIncomeMainId() {return incomeMainId;}
    public void setIncomeMainId(long incomeMainId) {this.incomeMainId = incomeMainId;}

    @Override
    public String toString() {
        return "Income{" +
                "id=" + id +
                ", count=" + count +
                ", purchasePrice=" + purchasePrice +
                ", purchasePriceAct=" + purchasePriceAct +
                ", storeArticle='" + storeArticle + '\'' +
//                ", store='" + store + '\'' +
                ", batchNumber=" + batchNumber +
                '}';
    }
}