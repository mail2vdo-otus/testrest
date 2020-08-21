package org.vorobiev.serviceCatalog;


import java.util.Date;

public class Client {

    private String firstName;
    private String surName;
    private Date birthDate;
    private String  passport;

    public Client(String firstName, String surName, Date birthDate, String passport) {
        this.firstName = firstName;
        this.surName = surName;
        this.birthDate = birthDate;
        this.passport = passport;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }
}
