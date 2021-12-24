package ru.kotofeya.storage.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "deleted_income_main")
public class DeletedIncomeMain {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private Long id;
        @Column(name = "income_main_id")
        private Long incomeMainId;
        @Column(name = "create_user_name")
        private String createUserName;
        @Column(name = "delete_user_name")
        private String deleteUserName;
        @Column(name = "delete_date")
        private String deleteDate;
        @Column(name = "create_date")
        private String createDate;
        @Column(name = "store")
        private String store;
        @Column(name = "incomeStringIds")
        private String incomeStringIds;

    public DeletedIncomeMain() {}
    public DeletedIncomeMain(IncomeMain incomeMain, String deleteDate, String deleteUserName) {
        this.incomeMainId = incomeMain.getId();
        this.createUserName = incomeMain.getUserName();
        this.deleteUserName = deleteUserName;
        this.createDate = incomeMain.getDate();
        this.deleteDate = deleteDate;
        this.store = incomeMain.getStore();
        List<Long> incomeSrtingIds = new ArrayList<>();
        incomeMain.getIncomeStrings().stream().forEach(it->incomeSrtingIds.add(it.getId()));
        this.incomeStringIds = incomeSrtingIds.toString();
    }
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public Long getIncomeMainId() {return incomeMainId;}
    public void setIncomeMainId(Long incomeMainId) {this.incomeMainId = incomeMainId;}
    public String getCreateUserName() {return createUserName;}
    public void setCreateUserName(String createUserName) {this.createUserName = createUserName;}
    public String getDeleteUserName() {return deleteUserName;}
    public void setDeleteUserName(String deleteUserName) {this.deleteUserName = deleteUserName;}
    public String getDeleteDate() {return deleteDate;}
    public void setDeleteDate(String deleteDate) {this.deleteDate = deleteDate;}
    public String getCreateDate() {return createDate;}
    public void setCreateDate(String createDate) {this.createDate = createDate;}
    public String getStore() {return store;}
    public void setStore(String store) {this.store = store;}
    public String getIncomeStringIds() {return incomeStringIds;}
    public void setIncomeStringIds(String incomeStringIds) {this.incomeStringIds = incomeStringIds;}
}
