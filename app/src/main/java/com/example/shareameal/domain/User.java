package com.example.shareameal.domain;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class User implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String street;
    private String city;
    private ArrayList<String> roles;
    private boolean isActive;

    public User(int id, String firstName, String lastName, String emailAddress, String phoneNumber, String street, String city, ArrayList<String> roles, boolean isActive) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.city = city;
        this.roles = roles;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public ArrayList<String> getRoles() {
        return roles;
    }

    public boolean isActive() {
        return isActive;
    }
}
