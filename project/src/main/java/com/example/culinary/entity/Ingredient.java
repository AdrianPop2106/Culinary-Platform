package com.example.culinary.entity;

public class Ingredient {
    private int id;
    private String title;
    private String description;
    private boolean custom;
    private int quantity;
    private String measure;

    Ingredient(){ }

    Ingredient(String title,String description,boolean custom,int quantity,String measure){
        this.title=title;
        this.description=description;
        this.custom=custom;
        this.quantity=quantity;
        this.measure=measure;
    }

    public Ingredient(int id, String title,String description,boolean custom,int quantity,String measure){
        this(title, description, custom,quantity,measure);
        this.id=id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getCustom() {
        return custom;
    }

    public void setCustom(boolean custom) {
        this.custom = custom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }
}
