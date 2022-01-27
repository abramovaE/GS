package ru.kotofeya.balance.model;
import javax.persistence.*;

@Entity
@Table(name = "money")
public class Money {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "date")
    private String date;
    @Column(name = "sum")
    private Integer sum;
    @Transient
    private Double doubleSum;

    public Money() {}
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}
    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}
    public Integer getSum() {return sum;}
    public void setSum(Integer sum) {this.sum = sum;}
    public Double getDoubleSum() {return doubleSum;}
    public void setDoubleSum(Double doubleSum) {this.doubleSum = doubleSum;}
}
