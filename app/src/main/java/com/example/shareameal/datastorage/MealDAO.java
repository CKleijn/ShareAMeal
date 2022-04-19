package com.example.shareameal.datastorage;

import java.io.BufferedReader;
import java.net.HttpURLConnection;

public interface MealDAO {
    // Let all classes that implements this interface implement the getMealInfo method
    static String getMealInfo() {
        String mealJSONString = null;
        return mealJSONString;
    }
}
