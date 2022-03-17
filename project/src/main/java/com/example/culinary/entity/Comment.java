package com.example.culinary.entity;

import java.util.Date;

public class Comment {
    private int id;
    private int userId;
    private int recipeId;
    private Date commentDate;
    private String message;

    public Comment(){}

    public Comment(int userId,int recipeId,Date commentDate,String message){
        this.commentDate=commentDate;
        this.userId=userId;
        this.message=message;
        this.recipeId=recipeId;
    }

    public Comment(int id,int userId,int recipeId,Date commentDate,String message){
        this(userId,recipeId,commentDate,message);
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

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
}
