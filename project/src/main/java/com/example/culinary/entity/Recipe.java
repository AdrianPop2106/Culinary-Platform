package com.example.culinary.entity;

import java.util.*;

public class Recipe {
    private int id;
    private int chefId;
    private String title;
    private String description;

    Recipe(){}
    Recipe(int chefId,String title,String description){
        this.chefId=chefId;
        this.title=title;
        this.description=description;
        //for(String i:ing.keySet()){
            //this.ing.put(Integer.parseInt(i),Integer.parseInt(ing.get(i)));
       // }
    }

    public Recipe(int id, int chefId, String title, String description){
        this(chefId, title, description);
        this.id=id;
    }

    /*public void addIngredient(int ingId,int quantity){
        ing.put(ingId,quantity);
    }*/

    /*public void removeIngredient(int ingId){
        ing.remove(ingId);
    }*/

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

    /*public Map<Integer,Integer> getIngredients() {
        return ing;
    }*/
}
