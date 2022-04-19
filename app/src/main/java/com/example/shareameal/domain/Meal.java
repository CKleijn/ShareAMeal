package com.example.shareameal.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class Meal implements Serializable {
    private String name;
    private String description;
    private String imageUrl;
    private Date dateTime;
    private Date createDateTime;
    private Date updateDateTime;
    private double price;
    private boolean isVega;
    private boolean isVegan;
    private boolean isToTakeHome;
    private boolean isActive;
    private int maxAmountOfParticipants;
    private ArrayList<String> allergens;
    private Cook cook;
    private ArrayList<Participant> participants;

    // Constructor to create a meal
    public Meal(String name, String description, String imageUrl, Date dateTime, Date createDateTime, Date updateDateTime, double price,
                boolean isVega, boolean isVegan, boolean isToTakeHome, boolean isActive, int maxAmountOfParticipants, ArrayList<String> allergens,
                Cook cook, ArrayList<Participant> participants) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.dateTime = dateTime;
        this.createDateTime = createDateTime;
        this.updateDateTime = updateDateTime;
        this.price = price;
        this.isVega = isVega;
        this.isVegan = isVegan;
        this.isToTakeHome = isToTakeHome;
        this.isActive = isActive;
        this.maxAmountOfParticipants = maxAmountOfParticipants;
        this.allergens = allergens;
        this.cook = cook;
        this.participants = participants;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public double getPrice() {
        return price;
    }

    public boolean isVega() {
        return isVega;
    }

    public boolean isVegan() {
        return isVegan;
    }

    public boolean isToTakeHome() {
        return isToTakeHome;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getMaxAmountOfParticipants() {
        return maxAmountOfParticipants;
    }

    public ArrayList<String> getAllergens() {
        return allergens;
    }

    public Cook getCook() {
        return cook;
    }

    public ArrayList<Participant> getParticipants() {
        return participants;
    }
}
