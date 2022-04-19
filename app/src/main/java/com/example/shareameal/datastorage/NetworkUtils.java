package com.example.shareameal.datastorage;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils implements MealDAO {
    private static final String TAG = NetworkUtils.class.getSimpleName();
    private static final String MEAL_BASE_URL =  "https://shareameal-api.herokuapp.com/api/meal";

    // Make an API request with the given query and store the results in a String
    public static String getMealInfo() {
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        BufferedReader reader = null;
        String mealJSONString = null;

        try {
            Uri builtURI = Uri.parse(MEAL_BASE_URL).buildUpon().build();

            URL requestURL = new URL(builtURI.toString());

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }

            if (builder.length() == 0) {
                return null;
            }

            mealJSONString = builder.toString();

        } catch (IOException e) {
            // Create a Log that something went wrong
            Log.e(TAG, "Something went wrong!");
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // Create a Log that something went wrong
                    Log.e(TAG, "Something went wrong!");
                    e.printStackTrace();
                }
            }
        }
        // Create a Log that the method is finished
        Log.i(TAG, "getMealInfo method finished!");
        // Return JSON String
        return mealJSONString;
    }
}
