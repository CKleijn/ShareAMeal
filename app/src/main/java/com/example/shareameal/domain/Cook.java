package com.example.shareameal.domain;

import java.util.ArrayList;

public class Cook extends User {
    public Cook(int id, String firstName, String lastName, String emailAddress, String phoneNumber, String street, String city, ArrayList<String> roles, boolean isActive) {
        super(id, firstName, lastName, emailAddress, phoneNumber, street, city, roles, isActive);
    }
}
