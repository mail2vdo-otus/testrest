package org.vorobiev.serviceCatalog;

public class Service {

    private String type;
    private String caption;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Service(String type, String caption) {
        this.type = type;
        this.caption = caption;
    }
}
