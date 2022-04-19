package com.example.shareameal.presentation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.shareameal.R;
import com.example.shareameal.domain.Cook;
import com.example.shareameal.domain.Meal;
import com.example.shareameal.domain.Participant;
import com.example.shareameal.applicationlogic.adapters.MealAdapter;
import com.example.shareameal.applicationlogic.loaders.MealLoader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private final String TAG = MainActivity.class.getSimpleName();
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private ArrayList<Meal> mealList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MealAdapter mealAdapter;
    private Boolean vegaFilter;
    private Boolean veganFilter;
    private Boolean toTakeHomeFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the Async loader
        LoaderManager.getInstance(this).initLoader(0, null, this);

        // Get a handle to the RecyclerView.
        recyclerView = findViewById(R.id.mainRecyclerView);
        // Create an adapter and supply the data to be displayed.
        mealAdapter = new MealAdapter(this, mealList);
        // Connect the adapter with the RecyclerView.
        recyclerView.setAdapter(mealAdapter);
        // Give the RecyclerView a layout manager.
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        }

        // Get the shared preferences that automatically were created and assign them
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        vegaFilter = sharedPreferences.getBoolean(FilterActivity.KEY_PREF_VEGA_SWITCH, false);
        veganFilter = sharedPreferences.getBoolean(FilterActivity.KEY_PREF_VEGAN_SWITCH, false);
        toTakeHomeFilter = sharedPreferences.getBoolean(FilterActivity.KEY_PREF_TO_TAKE_HOME_SWITCH, false);
        // Create a Log that the method is finished
        Log.i(TAG, "onCreate method finished!");
    }

    // Create the menu options
    public boolean onCreateOptionsMenu(Menu menu) {
        // Assign MenuInflater
        MenuInflater inflater = getMenuInflater();
        // Inflate the menu to the inflater
        inflater.inflate(R.menu.menu, menu);
        // Create a Log that the method is finished
        Log.i(TAG, "onCreateOptionsMenu method finished!");
        // Return true boolean
        return true;
    }

    // Start activity when clicking the option
    public boolean onOptionsItemSelected(MenuItem item) {
        // Start activity with intent
        startActivity(new Intent(this, FilterActivity.class));
        // Create a Log that the method is finished
        Log.i(TAG, "onOptionsItemSelected method finished!");
        // Return true boolean
        return true;
    }

    // Load the MealLoader
    @NonNull
    @Override
    public Loader onCreateLoader(int id, @Nullable Bundle args) {
        // Create a Log that the method is finished
        Log.i(TAG, "onCreateLoader method finished!");
        // Return the MealLoader
        return new MealLoader(this);
    }

    // Put each found result in a Meal object and store them in the ArrayList
    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        // Clear list before adding new items
        mealList.clear();
        // Check if data is ready to parse
        if(data.equals("FAILED")) {
            Toast.makeText(this, "API got some problems and couldn't load the data!", Toast.LENGTH_LONG).show();
        } else {
            // Assign JSON String to a Meal and store them in the mealList
            assignJSONToArrayList(data);
            // Notify the adapter that new data is available
            mealAdapter.notifyDataSetChanged();
            // Show a message about the result of the query
            Toast.makeText(this, mealAdapter.getItemCount() + " " + getString(R.string.results), Toast.LENGTH_SHORT).show();
        }
        // Create a Log that the method is finished
        Log.i(TAG, "onLoadFinished method finished!");
    }

    // Assign JSON String to a Meal and store them in the mealList
    public ArrayList<Meal> assignJSONToArrayList(String data) {
        try {
            // Get the results array
            JSONObject jsonObject = new JSONObject(data);
            JSONArray itemsArray = jsonObject.getJSONArray("result");
            // For each meal get the data
            for (int i = 0; i < itemsArray.length(); i++) {
                // Get the meal object
                JSONObject meal = itemsArray.getJSONObject(i);
                // Get all the data from the meal
                String mealName = meal.getString("name");
                String mealDescription = meal.getString("description");
                String mealImageUrl = meal.getString("imageUrl");
                String mealDateTime = meal.getString("dateTime");
                String mealCreateDateTime = meal.getString("createDate");
                String mealUpdateDateTime = meal.getString("updateDate");
                double mealPrice = meal.getDouble("price");
                boolean mealIsVega = meal.getBoolean("isVega");
                boolean mealIsVegan = meal.getBoolean("isVegan");
                boolean mealIsToTakeHome = meal.getBoolean("isToTakeHome");
                boolean mealIsActive = meal.getBoolean("isActive");
                int mealMaxAmountOfParticipants = meal.getInt("maxAmountOfParticipants");

                // Get the allergens from the specific meal
                JSONArray mealAllergensArray = meal.getJSONArray("allergenes");
                // Assign each allergen in an ArrayList
                ArrayList<String> mealAllergens = new ArrayList<>();
                for (int m = 0; m < mealAllergensArray.length(); m++) {
                    mealAllergens.add(mealAllergensArray.get(m).toString());
                }

                // Get the cook object from the specific meal
                JSONObject cook = meal.getJSONObject("cook");
                // Get all the data from the cook
                int cookId = cook.getInt("id");
                String cookFirstName = cook.getString("firstName");
                String cookLastName = cook.getString("lastName");
                String cookEmailAddress = cook.getString("emailAdress");
                String cookPhoneNumber = cook.getString("phoneNumber");
                String cookStreet = cook.getString("street");
                String cookCity = cook.getString("city");
                // Get the array with the roles
                JSONArray cookRolesArray = cook.getJSONArray("roles");
                // Assign each role in an ArrayList
                ArrayList<String> cookRoles = new ArrayList<>();
                for (int j = 0; j < cookRolesArray.length(); j++) {
                    cookRoles.add(cookRolesArray.get(j).toString());
                }
                boolean cookIsActive = cook.getBoolean("isActive");

                // Get the array with the participants
                JSONArray participantsArray = meal.getJSONArray("participants");
                ArrayList<Participant> participants = new ArrayList<>();
                for (int k = 0; k < participantsArray.length(); k++) {
                    JSONObject participant = participantsArray.getJSONObject(k);
                    // Get all the data from the participants
                    int participantId = participant.getInt("id");
                    String participantFirstName = participant.getString("firstName");
                    String participantLastName = participant.getString("lastName");
                    String participantEmailAddress = participant.getString("emailAdress");
                    String participantPhoneNumber = participant.getString("phoneNumber");
                    String participantStreet = participant.getString("street");
                    String participantCity = participant.getString("city");
                    // Get the array with the roles
                    JSONArray participantRolesArray = participant.getJSONArray("roles");
                    // Assign each role in an ArrayList
                    ArrayList<String> participantRoles = new ArrayList<>();
                    for (int l = 0; l < participantRolesArray.length(); l++) {
                        participantRoles.add(participantRolesArray.get(l).toString());
                    }
                    boolean participantIsActive = participant.getBoolean("isActive");
                    // Put each participant in the Participant ArrayList
                    participants.add(new Participant(participantId, participantFirstName, participantLastName, participantEmailAddress, participantPhoneNumber,
                            participantStreet, participantCity, participantRoles, participantIsActive));
                }

                // Create a meal and put each Meal in the Meal ArrayList
                Meal mealObject = new Meal(mealName, mealDescription, mealImageUrl, sdf.parse(mealDateTime), sdf.parse(mealCreateDateTime), sdf.parse(mealUpdateDateTime),
                        mealPrice, mealIsVega, mealIsVegan, mealIsToTakeHome, mealIsActive, mealMaxAmountOfParticipants, mealAllergens,
                        new Cook(cookId, cookFirstName, cookLastName, cookEmailAddress, cookPhoneNumber, cookStreet, cookCity, cookRoles, cookIsActive), participants);

                mealList.add(mealObject);

                // Apply the filters that the user choose
                filterMealList(mealIsVega, mealIsVegan, mealIsToTakeHome, mealObject);
            }
        } catch (Exception e) {
            // Create a Log that something went wrong
            Log.e(TAG, "Something went wrong!");
            e.printStackTrace();
        }
        // Create a Log that the method is finished
        Log.i(TAG, "assignJSONToArrayList method finished!");
        // Return the mealList
        return mealList;
    }

    // Apply the filters that the user choose
    public ArrayList<Meal> filterMealList(boolean mealIsVega, boolean mealIsVegan, boolean mealIsToTakeHome, Meal meal) {
        // Check if vegaFiler is checked
        if (vegaFilter) {
            // Check if meal.isVega is false
            if (!mealIsVega) {
                // Remove meal from the mealList if meal.isVega is false
                mealList.remove(meal);
            }
        }
        // Check if veganFiler is checked
        if (veganFilter) {
            // Check if meal.isVegan is false
            if (!mealIsVegan) {
                // Remove meal from the mealList if meal.isVegan is false
                mealList.remove(meal);
            }
        }
        // Check if toTakeHomeFilter is checked
        if (toTakeHomeFilter) {
            // Check if meal.isToTakeHome is false
            if (!mealIsToTakeHome) {
                // Remove meal from the mealList if meal.isToTakeHome is false
                mealList.remove(meal);
            }
        }
        // Create a Log that the method is finished
        Log.i(TAG, "filterMealList method finished!");
        // Return the updated mealList
        return mealList;
    }

    // Reset the Loader
    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        // Create a Log that the method is finished
        Log.i(TAG, "onLoaderReset method finished!");
    }
}