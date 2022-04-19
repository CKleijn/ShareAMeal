package com.example.shareameal.applicationlogic.loaders;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.shareameal.datastorage.NetworkUtils;

public class MealLoader extends AsyncTaskLoader<String> {
    private final String TAG = MealLoader.class.getSimpleName();

    // Constructor to create a MealLoader
    public MealLoader(Context context) {
        super(context);
        // Create a Log that the method is finished
        Log.i(TAG, "MealLoader constructor finished!");
    }

    // Load everything before the start
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        // Force the loader to load
        forceLoad();
        // Create a Log that the method is finished
        Log.i(TAG, "onStartLoading method finished!");
    }

    // Processing the getMealInfo in the background to get the results
    @Nullable
    @Override
    public String loadInBackground() {
        // Create a Log that the method is finished
        Log.i(TAG, "loadInBackground method finished!");
        // Return the JSON String from the NetworkUtils.getMealInfo()
        if(NetworkUtils.getMealInfo() != null) {
            return NetworkUtils.getMealInfo();
        } else {
            Log.d(TAG, "loadInBackground has failed!");
            return "FAILED";
        }
    }
}
