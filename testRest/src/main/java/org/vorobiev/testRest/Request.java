package org.vorobiev.testRest;

import javax.persistence.*;

@Entity
@Table(name = "request")
public class Request {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String message;
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
