package ru.kotofeya.storage.model.expands;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "edited_expand_main")
public class EditedExpandMain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "expand_main_id")
    private Long expandMainId;
    @Column(name = "create_user_name")
    private String createUserName;
    @Column(name = "edit_user_name")
    private String editUserName;
    @Column(name = "create_date")
    private String createDate;
    @Column(name = "new_date")
    private String newDate;
    @Column(name = "edit_date")
    private String editDate;
    @Column(name = "create_store")
    private String createStore;
    @Column(name = "edit_store")
    private String editStore;
    @Column(name = "create_expand_strings")
    private String createExpandStringIds;
    @Column(name = "edit_expand_strings")
    private String editExpandStringIds;

    public EditedExpandMain() {}
    public EditedExpandMain(ExpandMain expandMainFromDb, ExpandMain expandMain,
                            String editedDate, String editUserName, List<ExpandString> expandStringList){
        this.expandMainId = expandMain.getId();
        this.setCreateUserName(expandMainFromDb.getUserName());
        this.setEditUserName(editUserName);
        this.setCreateDate(expandMainFromDb.getDate());
        this.setEditDate(editedDate);
        this.setNewDate(expandMain.getDate());
        this.setCreateStore(expandMainFromDb.getStore());
        this.setEditStore(expandMain.getStore());
        Set<ExpandString> expandStringListDb = expandMainFromDb.getExpandStrings();
        List<Long> expandStringIds = new ArrayList<>();
        expandStringListDb.stream().forEach(
                it->expandStringIds.add(it.getId()));
        this.setCreateExpandStringIds(expandStringIds.toString());
        List<Long> editExpandStringIds = new ArrayList<>();
        expandStringList.stream().forEach(
                it->editExpandStringIds.add(it.getId()));
        this.setEditExpandStringIds(editExpandStringIds.toString());
    }
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public Long getExpandMainId() {return expandMainId;}
    public void setExpandMainId(Long expandMainId) {this.expandMainId = expandMainId;}
    public String getCreateUserName() {return createUserName;}
    public void setCreateUserName(String createUserName) {this.createUserName = createUserName;}
    public String getEditUserName() {return editUserName;}
    public void setEditUserName(String editUserName) {this.editUserName = editUserName;}
    public String getCreateDate() {return createDate;}
    public void setCreateDate(String createDate) {this.createDate = createDate;}
    public String getNewDate() {return newDate;}
    public void setNewDate(String newDate) {this.newDate = newDate;}
    public String getEditDate() {return editDate;}
    public void setEditDate(String editDate) {this.editDate = editDate;}
    public String getCreateStore() {return createStore;}
    public void setCreateStore(String createStore) {this.createStore = createStore;}
    public String getEditStore() {return editStore;}
    public void setEditStore(String editStore) {this.editStore = editStore;}
    public String getCreateExpandStringIds() {return createExpandStringIds;}
    public void setCreateExpandStringIds(String createExpandStringIds) {
        this.createExpandStringIds = createExpandStringIds;}
    public String getEditExpandStringIds() {return editExpandStringIds;}
    public void setEditExpandStringIds(String editExpandStringIds) {
        this.editExpandStringIds = editExpandStringIds;}
}
