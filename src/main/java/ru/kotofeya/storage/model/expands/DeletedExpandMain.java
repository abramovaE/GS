package ru.kotofeya.storage.model.expands;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "deleted_expand_main")
public class DeletedExpandMain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "expand_main_id")
    private Long expandMainId;
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
    @Column(name = "expandStringIds")
    private String expandStringIds;

    public DeletedExpandMain() {}
    public DeletedExpandMain(ExpandMain expandMain, String deleteDate, String deleteUserName) {
        this.expandMainId = expandMain.getId();
        this.createUserName = expandMain.getUserName();
        this.deleteUserName = deleteUserName;
        this.createDate = expandMain.getDate();
        this.deleteDate = deleteDate;
        this.store = expandMain.getStore();
        List<Long> expandSrtingIds = new ArrayList<>();
        expandMain.getExpandStrings().stream().forEach(it->expandSrtingIds.add(it.getId()));
        this.expandStringIds = expandSrtingIds.toString();
    }
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public Long getExpandMainId() {return expandMainId;}
    public void setExpandMainId(Long expandMainId) {this.expandMainId = expandMainId;}
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
    public String getExpandStringIds() {return expandStringIds;}
    public void setExpandStringIds(String expandStringIds) {this.expandStringIds = expandStringIds;}
}
