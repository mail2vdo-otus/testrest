package org.vorobiev.serviceCatalog;


import java.util.Date;

public class Deal {
    private String code;
    private Date openDate;
    private Date endDate;
    private double amount;
    private float rate;
    private String  fundISO;
    private Client client;

    public Deal(String code, Date openDate, Date endDate, double amount, float rate, String fundISO, Client client) {
        this.code = code;
        this.openDate = openDate;
        this.endDate = endDate;
        this.amount = amount;
        this.rate = rate;
        this.fundISO = fundISO;
        this.client = client;
    }

    public Deal() {
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
}

