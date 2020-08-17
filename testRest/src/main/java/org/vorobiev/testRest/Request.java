package org.vorobiev.testRest;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "request")
public class Request implements Serializable  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String message;

    @Column(name="code")
    private String code;

    @Column(name="opendate")
    private Date openDate;
    @Column(name="duedate")
    private Date dueDate;

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "dealid")
    private Deal deal;

    public Request(int id, String message, String code, Date openDate, Date dueDate, Deal deal) {
        this.id = id;
        this.message = message;
        this.code = code;
        this.openDate = openDate;
        this.dueDate = dueDate;
        this.deal = deal;
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Deal getDeal() {
        return deal;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }

    public Request(){
        this.message = "" ;
    }
    public Request(String message){
        this.message = message ;
    }
    public String getMessage() {
        return message;
    }

    public int getId() {
        return id;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Request{" + "id=" + id + ", message=" + message + '}';
    }

}
