package com.example.shareameal.presentation;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shareameal.R;
import com.example.shareameal.domain.Meal;
import com.example.shareameal.domain.Participant;
import com.example.shareameal.applicationlogic.adapters.ParticipantAdapter;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DetailActivity extends AppCompatActivity {
    private final String TAG = DetailActivity.class.getSimpleName();
    private final DecimalFormat df = new DecimalFormat("0.00");
    private final SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
    private ArrayList<Participant> participantList;
    private RecyclerView recyclerView;
    private ParticipantAdapter participantAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_detail);
        // Assign data from intent to their TextViews
        assignDataToTextViews();
        // Get a handle to the RecyclerView.
        recyclerView = findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        participantAdapter = new ParticipantAdapter(this, participantList);
        // Connect the adapter with the RecyclerView.
        recyclerView.setAdapter(participantAdapter);
        // Set focusable on false
        recyclerView.setFocusable(false);
        // Give the RecyclerView a layout manager.
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
        // Create a Log that the method is finished
        Log.i(TAG, "onCreate method finished!");
    }

    // Assign data from intent to their TextViews
    public void assignDataToTextViews() {
        // Get the intent and store the data from the intent in variables
        Meal meal = (Meal) getIntent().getSerializableExtra("MEAL");
        String firstName = meal.getCook().getFirstName();
        String lastName = meal.getCook().getLastName();
        String emailAddress = meal.getCook().getEmailAddress();
        String phoneNumber = meal.getCook().getPhoneNumber();
        String street = meal.getCook().getStreet();
        String city = meal.getCook().getCity();
        String name = meal.getName();
        String description = meal.getDescription();
        String image = meal.getImageUrl();
        Date date = meal.getDateTime();
        Date createDate = meal.getCreateDateTime();
        Date updateDate = meal.getUpdateDateTime();
        double price = meal.getPrice();
        boolean isVega = meal.isVega();
        boolean isVegan = meal.isVegan();
        boolean isActive = meal.isActive();
        boolean isToTakeHome = meal.isToTakeHome();
        int maxAmountOfParticipants = meal.getMaxAmountOfParticipants();
        ArrayList<String> allergens = meal.getAllergens();
        participantList = meal.getParticipants();

        // Find the views and store them in a TextView
        TextView detailCookName = findViewById(R.id.meal_cook_name);
        TextView detailCookEmailAddress = findViewById(R.id.meal_cook_email);
        TextView detailCookPhoneNumber = findViewById(R.id.meal_cook_phone);
        TextView detailCookAddress = findViewById(R.id.meal_address);
        TextView detailMealName = findViewById(R.id.meal_name);
        TextView detailMealDescription = findViewById(R.id.meal_description);
        ImageView detailMealImage = findViewById(R.id.meal_image);
        TextView detailMealDate = findViewById(R.id.meal_date);
        TextView detailMealCreateDate = findViewById(R.id.meal_create_date);
        TextView detailMealUpdateDate = findViewById(R.id.meal_update_date);
        TextView detailMealPrice = findViewById(R.id.meal_price);
        TextView detailMealVega = findViewById(R.id.meal_vega);
        TextView detailMealVegan = findViewById(R.id.meal_vegan);
        TextView detailMealActive = findViewById(R.id.meal_active);
        TextView detailMealToTakeHome = findViewById(R.id.meal_take_home);
        TextView detailMealMaxParticipants = findViewById(R.id.meal_max_participants);
        TextView detailMealAllergens = findViewById(R.id.meal_allergens);

        // Assign the variables to their views
        detailCookName.setText(firstName + " " + lastName);
        detailCookEmailAddress.setText(emailAddress);
        detailCookPhoneNumber.setText(phoneNumber);
        detailCookAddress.setText(street + ", " + city);
        detailMealName.setText(name);
        detailMealDescription.setText(description);
        Glide.with(detailMealImage.getContext())
                .load(image)
                .centerCrop()
                .into(detailMealImage);
        detailMealDate.setText(getString(R.string.served_on) + " " + sdf.format(date));
        detailMealCreateDate.setText(getString(R.string.created_on) + " " + sdf.format(createDate));
        detailMealUpdateDate.setText(getString(R.string.updated_on) + " " + sdf.format(updateDate));
        detailMealPrice.setText("€" + df.format(price));
        detailMealMaxParticipants.setText(getString(R.string.max_participants) + " " + maxAmountOfParticipants);
        // Create a StringBuilder for allergens
        StringBuilder allergenString = new StringBuilder();
        // Get each allergen and put them in the StringBuilder
        for(int i = 0; i < allergens.size(); i++) {
            if(i == (allergens.size() - 1)) {
                allergenString.append(allergens.get(i).toString());
            } else {
                allergenString.append(allergens.get(i).toString());
                allergenString.append(", ");
            }
        }
        // Check if StringBuilder is empty
        if(allergenString.length() == 0) {
            detailMealAllergens.setText(R.string.allergens);
        } else {
            detailMealAllergens.setText(getString(R.string.allergenic_info) + " " + allergenString);
        }
        // Check if isVega boolean is true
        if(isVega) {
            // Have 2 spaces behind check icon, otherwise it will be another icon
            detailMealVega.setText(R.string.vega_check);
        } else {
            detailMealVega.setText(R.string.vega_false);
        }
        // Check if isVegan boolean is true
        if(isVegan) {
            // Have 2 spaces behind check icon, otherwise it will be another icon
            detailMealVegan.setText(R.string.vegan_check);
        } else {
            detailMealVegan.setText(R.string.vegan_false);
        }
        // Check if isActive boolean is true
        if(isActive) {
            // Have 2 spaces behind check icon, otherwise it will be another icon
            detailMealActive.setText(R.string.active_check);
        } else {
            detailMealActive.setText(R.string.active_false);
        }
        // Check if isToTakeHome boolean is true
        if(isToTakeHome) {
            // Have 2 spaces behind check icon, otherwise it will be another icon
            detailMealToTakeHome.setText(R.string.to_take_home_check);
        } else {
            detailMealToTakeHome.setText(R.string.to_take_home_false);
        }
        // Create a Log that the method is finished
        Log.i(TAG, "assignDataToTextViews method finished!");
    }
}
