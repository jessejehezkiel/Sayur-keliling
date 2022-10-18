package com.example.sayurkelilingrevisi.Model;

public class Pedagang {

    private String id;
    private String usernamepdg;
    private String areapdg;
    private String imageURL;

    public Pedagang() {
    }

    public Pedagang(String id, String usernamepdg, String areapdg, String imageURL) {
        this.id = id;
        this.usernamepdg = usernamepdg;
        this.areapdg = areapdg;
        this.imageURL = imageURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsernamepdg() {
        return usernamepdg;
    }

    public void setUsernamepdg(String usernamepdg) {
        this.usernamepdg = usernamepdg;
    }

    public String getAreapdg() {
        return areapdg;
    }

    public void setAreapdg(String areapdg) {
        this.areapdg = areapdg;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

}
