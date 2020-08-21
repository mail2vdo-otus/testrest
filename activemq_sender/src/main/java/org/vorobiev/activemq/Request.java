package org.vorobiev.activemq;

public class Request {
    private int id;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Request(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public Request() {
        this.id = 0;
        this.status = "";
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}


