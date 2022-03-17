package com.example.culinary.entity;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private int id;
    private int chefId;
    private String title;
    private String description;
    private List<Ingredient> ing;

    Recipe(){}
    Recipe(int chefId,String title,String description){
        this.chefId=chefId;
        this.title=title;
        this.description=description;
        ing=new ArrayList<Ingredient>();
    }

    Recipe(int id,int chefId,String title,String description){
        this(chefId, title, description);
        this.id=id;
    }

    public void addIngredient(Ingredient i){
        ing.add(i);
    }

    public void removeIngredient(Ingredient i){
        ing.remove(i);
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
}
