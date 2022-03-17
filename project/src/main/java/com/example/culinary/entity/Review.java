package com.example.culinary.entity;

import java.util.Date;

public class Review extends Comment{
    private int rating;

    Review(){}
    Review(int userId,int recipeId, Date commentDate, String message,int rating){
        super(userId, recipeId,commentDate, message);
        this.rating=rating;
    }
    Review(int id,int userId,int recipeId, Date commentDate, String message,int rating){
        super(id, userId,recipeId, commentDate, message);
        this.rating=rating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
