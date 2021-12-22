package ru.kotofeya.storage.model;

import javax.persistence.*;
@Entity
@Table(name = "edited_income_string")
public class EditedIncomeString {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "create_user_name")
    private String createUserName;
    @Column(name = "edit_user_name")
    private String editUserName;
    @Column(name = "create_date")
    private String createDate;
    @Column(name = "edit_date")
    private String editDate;
    @Column(name = "create_items")
    private String createItemIds;
    @Column(name = "edit_items")
    private String editItemIds;
    @Column(name = "create_count")
    private int createCount;
    @Column(name = "edit_count")
    private int editCount;
    @Column(name = "create_purchase_price")
    private int createPurchasePrice;
    @Column(name = "edit_purchase_price")
    private int editPurchasePrice;
    @Column(name = "create_purchase_price_act")
    private int createPurchasePriceAct;
    @Column(name = "edit_purchase_price_act")
    private int editPurchasePriceAct;
    @Column(name = "create_store_article")
    private String createStoreArticle;
    @Column(name = "edit_store_article")
    private String editStoreArticle;
    @Column(name = "create_store")
    private String createStore;
    @Column(name = "edit_store")
    private String editStore;
    @Column(name = "create_batch_number")
    private int createBatchNumber;
    @Column(name = "edit_batch_number")
    private int editBatchNumber;
    @Column(name = "create_income_main")
    private int createIncomeMain;
    @Column(name = "edit_income_main")
    private int editIncomeMain;

    public EditedIncomeString() {}
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getCreateUserName() {return createUserName;}
    public void setCreateUserName(String createUserName) {this.createUserName = createUserName;}
    public String getEditUserName() {return editUserName;}
    public void setEditUserName(String editUserName) {this.editUserName = editUserName;}
    public String getCreateDate() {return createDate;}
    public void setCreateDate(String createDate) {this.createDate = createDate;}
    public String getEditDate() {return editDate;}
    public void setEditDate(String editDate) {this.editDate = editDate;}
    public String getCreateItemIds() {return createItemIds;}
    public void setCreateItemIds(String createItemIds) {this.createItemIds = createItemIds;}
    public String getEditItemIds() {return editItemIds;}
    public void setEditItemIds(String editItemIds) {this.editItemIds = editItemIds;}
    public int getCreateCount() {return createCount;}
    public void setCreateCount(int createCount) {this.createCount = createCount;}
    public int getEditCount() {return editCount;}
    public void setEditCount(int editCount) {this.editCount = editCount;}
    public int getCreatePurchasePrice() {return createPurchasePrice;}
    public void setCreatePurchasePrice(int createPurchasePrice) {this.createPurchasePrice = createPurchasePrice;}
    public int getEditPurchasePrice() {return editPurchasePrice;}
    public void setEditPurchasePrice(int editPurchasePrice) {this.editPurchasePrice = editPurchasePrice;}
    public int getCreatePurchasePriceAct() {return createPurchasePriceAct;}
    public void setCreatePurchasePriceAct(int createPurchasePriceAct) {this.createPurchasePriceAct = createPurchasePriceAct;}
    public int getEditPurchasePriceAct() {return editPurchasePriceAct;}
    public void setEditPurchasePriceAct(int editPurchasePriceAct) {this.editPurchasePriceAct = editPurchasePriceAct;}
    public String getCreateStoreArticle() {return createStoreArticle;}
    public void setCreateStoreArticle(String createStoreArticle) {this.createStoreArticle = createStoreArticle;}
    public String getEditStoreArticle() {return editStoreArticle;}
    public void setEditStoreArticle(String editStoreArticle) {this.editStoreArticle = editStoreArticle;}
    public String getCreateStore() {return createStore;}
    public void setCreateStore(String createStore) {this.createStore = createStore;}
    public String getEditStore() {return editStore;}
    public void setEditStore(String editStore) {this.editStore = editStore;}
    public int getCreateBatchNumber() {return createBatchNumber;}
    public void setCreateBatchNumber(int createBatchNumber) {this.createBatchNumber = createBatchNumber;}
    public int getEditBatchNumber() {return editBatchNumber;}
    public void setEditBatchNumber(int editBatchNumber) {this.editBatchNumber = editBatchNumber;}
    public int getCreateIncomeMain() {return createIncomeMain;}
    public void setCreateIncomeMain(int createIncomeMain) {this.createIncomeMain = createIncomeMain;}
    public int getEditIncomeMain() {return editIncomeMain;}
    public void setEditIncomeMain(int editIncomeMain) {this.editIncomeMain = editIncomeMain;}
}
