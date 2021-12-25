package ru.kotofeya.storage.model.expands;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "expand_main")
public class ExpandMain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "date")
    private String date;
    @Column(name = "store")
    private String store;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "expandMain")
    private Set<ExpandString> expandStrings;
    @Transient
    private int sum;

    public ExpandMain() {}
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}
    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}
    public String getStore() {return store;}
    public void setStore(String store) {this.store = store;}
    public Set<ExpandString> getExpandStrings() {return expandStrings;}
    public void setExpandStrings(Set<ExpandString> incomeStrings) {
        this.expandStrings = expandStrings;
    }
    public int getSum() {return sum;}
    public void setSum(int sum) {this.sum = sum;}

    @Override
    public String toString() {
        return "ExpandMain{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", date='" + date + '\'' +
                ", store='" + store + '\'' +
//                ", expandStrings=" + expandStrings +
//                ", sum=" + sum +
                '}';
    }
}
