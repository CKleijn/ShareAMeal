package com.example.shareameal.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class FilterActivity extends AppCompatActivity {
    private final String TAG = FilterActivity.class.getSimpleName();
    public static final String KEY_PREF_VEGA_SWITCH = "vega_switch";
    public static final String KEY_PREF_VEGAN_SWITCH = "vegan_switch";
    public static final String KEY_PREF_TO_TAKE_HOME_SWITCH = "to_take_home_switch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the fragment in the activity
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new FilterFragment()).commit();
        // Create a Log that the method is finished
        Log.i(TAG, "onCreate method finished!");
    }
}