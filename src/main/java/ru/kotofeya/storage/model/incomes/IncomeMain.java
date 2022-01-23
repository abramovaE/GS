package ru.kotofeya.storage.model.incomes;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "income_main")
public class IncomeMain {
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
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "incomeMain")
    private Set<IncomeString> incomeStrings;
    @Transient
    private int sum;
    @Transient
    private int sumAct;

    public IncomeMain() {}
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}
    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}
    public String getStore() {return store;}
    public void setStore(String store) {this.store = store;}
    public Set<IncomeString> getIncomeStrings() {return incomeStrings;}
    public void setIncomeStrings(Set<IncomeString> incomeStrings) {
        this.incomeStrings = incomeStrings;
    }

    public int getSum() {return sum;}
    public void setSum(int sum) {this.sum = sum;}
    public int getSumAct() {return sumAct;}
    public void setSumAct(int sumAct) {this.sumAct = sumAct;}

    @Override
    public String toString() {
        return "IncomeMain{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", date='" + date + '\'' +
                ", store='" + store + '\'' +
                '}';
    }
}
