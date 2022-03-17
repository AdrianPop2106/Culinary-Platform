package com.example.culinary.entity;

public class Order {
    private int id;
    private int userId;
    private int chefId;
    private String address;
    private int price;

    Order(){}

    Order(int userId,int chefId,String address,int price){
        this.userId=userId;
        this.chefId=chefId;
        this.address=address;
        this.price=price;
    }

    Order(int id,int userId,int chefId,String address,int price){
        this(userId, chefId, address, price);
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getChefId() {
        return chefId;
    }

    public void setChefId(int chefId) {
        this.chefId = chefId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
