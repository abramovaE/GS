package ru.kotofeya.storage.model.incomes;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "edited_income_main")
public class EditedIncomeMain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "income_main_id")
    private Long incomeMainId;
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
    @Column(name = "create_store")
    private String createStore;
    @Column(name = "edit_store")
    private String editStore;
    @Column(name = "create_income_strings")
    private String createIncomeStringIds;
    @Column(name = "edit_income_strings")
    private String editIncomeStringIds;
    public EditedIncomeMain() {}
    public EditedIncomeMain(IncomeMain incomeMainFromDb, IncomeMain incomeMain,
                            String editedDate, String editUserName, List<IncomeString> incomeStringList){
        this.incomeMainId = incomeMainFromDb.getId();
        this.setCreateUserName(incomeMainFromDb.getUserName());
        this.setEditUserName(editUserName);
        this.setCreateDate(incomeMainFromDb.getDate());
        this.setEditDate(editedDate);
        this.newDate = incomeMain.getDate();
        this.setCreateStore(incomeMainFromDb.getStore());
        this.setEditStore(incomeMain.getStore());
        Set<IncomeString> incomeStringListDb = incomeMainFromDb.getIncomeStrings();
        List<Long> incomeStringIds = new ArrayList<>();
        incomeStringListDb.stream().forEach(
                it->incomeStringIds.add(it.getId()));
        this.setCreateIncomeStringIds(incomeStringIds.toString());
        List<Long> editIncomeStringIds = new ArrayList<>();
        incomeStringList.stream().forEach(
                it->editIncomeStringIds.add(it.getId()));
        this.setEditIncomeStringIds(editIncomeStringIds.toString());
    }
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public Long getIncomeMainId() {return incomeMainId;}
    public void setIncomeMainId(Long incomeMainId) {this.incomeMainId = incomeMainId;}
    public String getCreateUserName() {return createUserName;}
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }
    public String getEditUserName() {return editUserName;}
    public void setEditUserName(String editUserName) {this.editUserName = editUserName;}
    public String getCreateDate() {return createDate;}
    public void setCreateDate(String createDate) {this.createDate = createDate;}
    public String getEditDate() {return editDate;}
    public void setEditDate(String editDate) {this.editDate = editDate;}
    public String getNewDate() {return newDate;}
    public void setNewDate(String newDate) {this.newDate = newDate;}
    public String getCreateStore() {return createStore;}
    public void setCreateStore(String createStore) {this.createStore = createStore;}
    public String getEditStore() {return editStore;}
    public void setEditStore(String editStore) {this.editStore = editStore;}
    public String getCreateIncomeStringIds() {return createIncomeStringIds;}
    public void setCreateIncomeStringIds(String createIncomeStringIds) {
        this.createIncomeStringIds = createIncomeStringIds;
    }
    public String getEditIncomeStringIds() {return editIncomeStringIds;}
    public void setEditIncomeStringIds(String editIncomeStringIds) {
        this.editIncomeStringIds = editIncomeStringIds;
    }
}