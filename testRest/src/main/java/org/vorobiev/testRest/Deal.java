package org.vorobiev.testRest;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "deal")

//create table deal(id serial primary key, code varchar(10), openDate Date,endDate Date, amount money, rate float,
// fundISO char(3),clientId ,  FOREIGN KEY (clientId) REFERENCES client (Id))
public class Deal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int dealid;
    @Column(name="code")
    private String code;

    @Column(name="opendate")
    private Date openDate;
    @Column(name="enddate")
    private Date endDate;


    @Column(name="amount")
    private double amount;
    @Column(name="rate")
    private float rate;

    @Column(name="fundiso")
    private String  fundISO;


    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "clientid")
    private Client client;

    public Deal(){this.code="";};

    public Deal(int dealid, String code, Date openDate, Date endDate, double amount, float rate, String fundISO, Client client) {
        this.dealid = dealid;
        this.code = code;
        this.openDate = openDate;
        this.endDate = endDate;
        this.amount = amount;
        this.rate = rate;
        this.fundISO = fundISO;
        this.client = client;
    }

    public int getDealid() {
        return dealid;
    }

    public void setDealid(int dealid) {
        this.dealid = dealid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getFundISO() {
        return fundISO;
    }

    public void setFundISO(String fundISO) {
        this.fundISO = fundISO;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Deal{" +
                "id=" + dealid +
                ", code='" + code + '\'' +
                ", openDate=" + openDate +
                ", endDate=" + endDate +
                ", amount=" + amount +
                ", rate=" + rate +
                ", fundISO='" + fundISO + '\'' +
                ", client=" + client +
                '}';
    }
}
