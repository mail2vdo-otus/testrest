package org.vorobiev.testRest;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "client")

public class Client implements Serializable {

//create table client(id serial primary key, firstName varchar(100),surName varchar(100), birthDate Date,
// passport varchar(11), address varchar(100))


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="clientid")
        private int clientid;

        @Column(name="firstname")
        private String firstName;
        @Column(name="surname")
        private String surName;
        @Column(name="birthdate")
        private Date birthDate;
        @Column(name="passport")
        private String  passport;

       /* @OneToMany(fetch = FetchType.EAGER,mappedBy="client",cascade = CascadeType.ALL)
        private Set<Deal> deal;

    public Set<Deal> getDeal() {
        return deal;
    }

    public void setDeal(Set<Deal> deal) {
        this.deal = deal;
    }
*/
    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name="address")
        private String address;


        public Client(){this.firstName="";this.surName=""; };
        public Client( String firstName, String surName, Date birthDate, String passport,String address) {

            this.firstName = firstName;
            this.surName = surName;
            this.birthDate = birthDate;
            this.passport = passport;
            this.address = address;


        }

        public int getClientid() {
            return clientid;
        }

        public void setClientid(int clientid) {
            this.clientid = clientid;
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
            return "Client{" +
                    "clientid=" + clientid +
                    ", firstName='" + firstName + '\'' +
                    ", surName='" + surName + '\'' +
                    ", birthaDate='" + birthDate +'\''+
                    ", passport='"+passport +'\''+
                    ", address="+address +
                    '}';
        }

    }


