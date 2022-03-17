package com.example.culinary.entity;

public class User{
    private int id;
    private String userName;
    private String email;
    private String pass;

    User(){ }

    User(String userName,String email,String pass){
        this.userName=userName;
        this.email=email;
        this.pass=pass;
    }

    public User(int id, String userName, String email, String pass){
        this(userName, email, pass);
        this.id=id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName=userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email=email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass=pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
    }
}
