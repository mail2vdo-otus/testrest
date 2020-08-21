package org.vorobiev.serviceCatalog;

import java.util.Date;

public class Request {



    private Date openDate;
    private Date dueDate;
    private String typecode;
    private Deal deal;

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

    public String getTypecode() {
        return typecode;
    }

    public void setTypecode(String typecode) {
        this.typecode = typecode;
    }

    public Deal getDeal() {
        return deal;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }

    public Request(Date openDate, Date dueDate, String typecode, Deal deal) {
        this.openDate = openDate;
        this.dueDate = dueDate;
        this.typecode = typecode;
        this.deal = deal;
    }
}
