package ru.kotofeya.storage.model.expands;

import javax.persistence.*;

@Entity
@Table(name = "edited_expand_string")
public class EditedExpandString {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "expand_string_id")
    private Long expandStringId;
    @Column(name = "create_user_name")
    private String createUserName;
    @Column(name = "edit_user_name")
    private String editUserName;
    @Column(name = "create_date")
    private String createDate;
    @Column(name = "edit_date")
    private String editDate;
    @Column(name = "new_date")
    private String newDate;
    @Column(name = "create_item")
    private Long createItemId;
    @Column(name = "edit_item")
    private Long editItemId;
    @Column(name = "create_count")
    private int createCount;
    @Column(name = "edit_count")
    private int editCount;
    @Column(name = "create_sale_price")
    private int createSalePrice;
    @Column(name = "edit_sale_price")
    private int editSalePrice;
    @Column(name = "create_batch_number")
    private int createBatchNumber;
    @Column(name = "edit_batch_number")
    private int editBatchNumber;
    @Column(name = "create_expand_main")
    private Long createExpandMainId;
    @Column(name = "edit_expand_main")
    private Long editExpandMainId;

    public EditedExpandString() {}
    public EditedExpandString(ExpandString expandStringFromDb, ExpandString expandString,
                              String editedDate, String editUserName) {
        this.expandStringId = expandStringFromDb.getId();
        this.createUserName = expandStringFromDb.getUserName();
        this.editUserName = editUserName;
        this.createDate = expandStringFromDb.getDate();
        this.editDate = editedDate;
        this.newDate = expandString.getDate();
        this.createItemId = expandStringFromDb.getItem().getId();
        this.editItemId = expandString.getItem().getId();
        this.createCount = expandStringFromDb.getCount();
        this.editCount = expandString.getCount();
        this.createSalePrice = expandStringFromDb.getSalePrice();
        this.editSalePrice = expandString.getSalePrice();
        this.createBatchNumber = expandStringFromDb.getBatchNumber();
        this.editBatchNumber = expandString.getBatchNumber();
        this.createExpandMainId = expandStringFromDb.getExpandMain().getId();
        this.editExpandMainId = expandString.getExpandMain().getId();
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public Long getExpandStringId() {return expandStringId;}
    public void setExpandStringId(Long expandStringId) {this.expandStringId = expandStringId;}
    public String getCreateUserName() {return createUserName;}
    public void setCreateUserName(String createUserName) {this.createUserName = createUserName;}
    public String getEditUserName() {return editUserName;}
    public void setEditUserName(String editUserName) {this.editUserName = editUserName;}
    public String getCreateDate() {return createDate;}
    public void setCreateDate(String createDate) {this.createDate = createDate;}
    public String getEditDate() {return editDate;}
    public void setEditDate(String editDate) {this.editDate = editDate;}
    public String getNewDate() {return newDate;}
    public void setNewDate(String newDate) {this.newDate = newDate;}
    public Long getCreateItemId() {return createItemId;}
    public void setCreateItemId(Long createItemId) {this.createItemId = createItemId;}
    public Long getEditItemId() {return editItemId;}
    public void setEditItemId(Long editItemId) {this.editItemId = editItemId;}
    public int getCreateCount() {return createCount;}
    public void setCreateCount(int createCount) {this.createCount = createCount;}
    public int getEditCount() {return editCount;}
    public void setEditCount(int editCount) {this.editCount = editCount;}
    public int getCreateSalePrice() {return createSalePrice;}
    public void setCreateSalePrice(int createSalePrice) {this.createSalePrice = createSalePrice;}
    public int getEditSalePrice() {return editSalePrice;}
    public void setEditSalePrice(int editSalePrice) {this.editSalePrice = editSalePrice;}
    public int getCreateBatchNumber() {return createBatchNumber;}
    public void setCreateBatchNumber(int createBatchNumber) {this.createBatchNumber = createBatchNumber;}
    public int getEditBatchNumber() {return editBatchNumber;}
    public void setEditBatchNumber(int editBatchNumber) {this.editBatchNumber = editBatchNumber;}
    public Long getCreateExpandMainId() {return createExpandMainId;}
    public void setCreateExpandMainId(Long createExpandMainId) {this.createExpandMainId = createExpandMainId;}
    public Long getEditExpandMainId() {return editExpandMainId;}
    public void setEditExpandMainId(Long editExpandMainId) {this.editExpandMainId = editExpandMainId;}
}
