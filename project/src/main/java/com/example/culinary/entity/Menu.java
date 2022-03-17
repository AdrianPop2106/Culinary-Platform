package com.example.culinary.entity;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private int id;
    private int chefId;
    private String description;
    private double price;
    private List<Recipe> components;

    Menu(){}

    Menu(int chefId,String description,double price){
        this.chefId=chefId;
        this.description=description;
        this.price=price;
        components=new ArrayList<>();
    }

    Menu(int id,int chefId,String description,double price){
        this(chefId, description, price);
        this.id=id;
    }

    public void addRecipe(Recipe r){
        components.add(r);
    }

    public void removeRecipe(Recipe r){
        components.remove(r);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChefId() {
        return chefId;
    }

    public void setChefId(int chefId) {
        this.chefId = chefId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
