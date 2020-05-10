package org.vorobiev.testRest;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "restuser")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="firstname")
    private String firstName;
    @Column(name="surname")
    private String surName;
    @Column(name="birthdate")
    private Date birthDate;

    public User(){this.firstName="";this.surName=""; };
    public User( String firstName, String surName, Date birthDate) {

        this.firstName = firstName;
        this.surName = surName;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", birthaDate=" + birthDate +
                '}';
    }
}
