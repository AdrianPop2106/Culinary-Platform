package com.example.culinary.entity;

public class Chef extends User{
    private boolean ordering;

    public Chef(){ }

    public Chef(String userName, String email, String pass, boolean ordering){
        super(userName,email,pass);
        this.ordering=ordering;
    }

    public Chef(int id,String userName, String email, String pass, boolean ordering){
        super(id,userName,email,pass);
        this.ordering=ordering;
    }

    public boolean getOrdering(){
        return ordering;
    }

    public void setOrdering(boolean order){
        ordering=order;
    }
}
