package com.example.shareameal.presentation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceFragmentCompat;

import android.util.Log;

import com.example.shareameal.R;


public class FilterFragment extends PreferenceFragmentCompat {
    private final String TAG = FilterFragment.class.getSimpleName();

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        // Set the preferences.xml into the fragment
        setPreferencesFromResource(R.xml.preferences, rootKey);
        // Create a Log that the method is finished
        Log.i(TAG, "onCreatePreferences method finished!");
    }
}