package com.example.culinary.entity;

public class Ingredient {
    private int id;
    private String title;
    private String description;
    private boolean custom;

    Ingredient(){ }

    Ingredient(String title,String description,boolean custom){
        this.title=title;
        this.description=description;
        this.custom=custom;
    }

    public Ingredient(int id, String title,String description,boolean custom){
        this(title, description, custom);
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
}
