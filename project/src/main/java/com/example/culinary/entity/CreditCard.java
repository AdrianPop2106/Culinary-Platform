package com.example.culinary.entity;

public class CreditCard {
    private int id;
    private int userId;
    private String cardOwner;
    private String cardNumber;
    private int cvw;

    public CreditCard(){}

    public CreditCard(int userId,String cardNumber,String cardOwner,int cvw){
        this.userId=userId;
        this.cardNumber=cardNumber;
        this.cardOwner=cardOwner;
        this.cvw=cvw;
    }

    public CreditCard(int id,int userId,String cardNumber,String cardOwner,int cvw){
        this(userId,cardNumber,cardOwner,cvw);
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

    public String getCardOwner() {
        return cardOwner;
    }

    public void setCardOwner(String cardOwner) {
        this.cardOwner = cardOwner;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCvw() {
        return cvw;
    }

    public void setCvw(int cvw) {
        this.cvw = cvw;
    }
}
