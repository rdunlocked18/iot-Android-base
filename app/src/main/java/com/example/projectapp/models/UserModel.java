package com.example.projectapp.models;

public class UserModel {
    String userName ;
    String password;
    String macID;
    Boolean available ;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMacID() {
        return macID;
    }

    public void setMacID(String macID) {
        this.macID = macID;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public UserModel() {
    }

    public UserModel(String userName, String password, String macID, Boolean available) {
        this.userName = userName;
        this.password = password;
        this.macID = macID;
        this.available = available;
    }
}