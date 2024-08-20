package com.truongphuc.Model;

public class User {
    private String id;
    private String userName;
    private String fullName;
    private String password;

    public User(){}

    public User(String id, String userName, String fullName, String password) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
