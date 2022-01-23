package ru.kotofeya.storage.model.items;


import javax.persistence.*;

@Entity
@Table(name = "edited_item")
public class EditedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "item_id")
    private Long itemId;
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
    @Column(name = "create_article")
    private String createArticle;
    @Column(name = "edit_article")
    private String editArticle;
    @Column(name = "create_name")
    private String createName;
    @Column(name = "edit_name")
    private String editName;
    @Column(name = "create_ean")
    private String createEan;
    @Column(name = "edit_ean")
    private String editEan;
    @Column(name = "create_mp_link_yandex")
    private String createMpLinkYandex;
    @Column(name = "edit_mp_link_yandex")
    private String editMpLinkYandex;
    @Column(name = "create_mp_link_sber")
    private String createMpLinkSber;
    @Column(name = "edit_mp_link_sber")
    private String editMpLinkSber;

    public EditedItem() {}
    public EditedItem(Item oldItem, Item newItem, String editUserName, String editDate){
        setItemId(oldItem.getId());
        setCreateUserName(oldItem.getUserName());
        setEditUserName(editUserName);
        setCreateDate(oldItem.getDate());
        setEditDate(editDate);
        setNewDate(newItem.getDate());
        setCreateArticle(oldItem.getArticle());
        setEditArticle(newItem.getArticle());
        setCreateName(oldItem.getName());
        setEditName(newItem.getName());
        setCreateEan(oldItem.getEan());
        setEditEan(newItem.getEan());
        setCreateMpLinkYandex(oldItem.getMpLinkYandex());
        setCreateMpLinkSber(oldItem.getMpLinkSber());
        setEditMpLinkYandex(oldItem.getMpLinkSber());
        setEditMpLinkSber(newItem.getMpLinkSber());
    }
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public Long getItemId() {return itemId;}
    public void setItemId(Long itemId) {this.itemId = itemId;}
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
    public String getCreateArticle() {return createArticle;}
    public void setCreateArticle(String createArticle) {this.createArticle = createArticle;}
    public String getEditArticle() {return editArticle;}
    public void setEditArticle(String editArticle) {this.editArticle = editArticle;}
    public String getCreateName() {return createName;}
    public void setCreateName(String createName) {this.createName = createName;}
    public String getEditName() {return editName;}
    public void setEditName(String editName) {this.editName = editName;}
    public String getCreateEan() {return createEan;}
    public void setCreateEan(String createEan) {this.createEan = createEan;}
    public String getEditEan() {return editEan;}
    public void setEditEan(String editEan) {this.editEan = editEan;}
    public String getCreateMpLinkYandex() {return createMpLinkYandex;}
    public void setCreateMpLinkYandex(String createMpLinkYandex) {this.createMpLinkYandex = createMpLinkYandex;}
    public String getEditMpLinkYandex() {return editMpLinkYandex;}
    public void setEditMpLinkYandex(String editMpLinkYandex) {this.editMpLinkYandex = editMpLinkYandex;}
    public String getCreateMpLinkSber() {return createMpLinkSber;}
    public void setCreateMpLinkSber(String createMpLinkSber) {this.createMpLinkSber = createMpLinkSber;}
    public String getEditMpLinkSber() {return editMpLinkSber;}
    public void setEditMpLinkSber(String editMpLinkSber) {this.editMpLinkSber = editMpLinkSber;}
}
