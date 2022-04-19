package com.example.shareameal.applicationlogic.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shareameal.R;
import com.example.shareameal.domain.Meal;
import com.example.shareameal.presentation.DetailActivity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealHolder> {
    private final String TAG = MealAdapter.class.getSimpleName();
    private final DecimalFormat df = new DecimalFormat("0.00");
    private final SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
    private ArrayList<Meal> mealList;
    private LayoutInflater inflater;

    // Constructor to create a MealAdapter
    public MealAdapter(Context context, ArrayList<Meal> mealList) {
        this.inflater = LayoutInflater.from(context);
        this.mealList = mealList;
        // Create a Log that the method is finished
        Log.i(TAG, "MealAdapter constructor finished!");
    }

    // A class that holds the meals
    class MealHolder extends RecyclerView.ViewHolder {
        private ImageView mealImage;
        private TextView mealName;
        private TextView mealDate;
        private TextView mealCity;
        private TextView mealPrice;
        private MealAdapter adapter;

        // Constructor to create a MealHolder
        public MealHolder(@NonNull View itemView, MealAdapter adapter) {
            super(itemView);
            this.mealImage = itemView.findViewById(R.id.imageView);
            this.mealName = itemView.findViewById(R.id.name);
            this.mealDate = itemView.findViewById(R.id.date);
            this.mealCity = itemView.findViewById(R.id.city);
            this.mealPrice = itemView.findViewById(R.id.price);
            this.adapter = adapter;
            // Create a Log that the method is finished
            Log.i(TAG, "MealHolder constructor finished!");
        }
    }

    // Inflate the item layout and return the MealHolder
    @NonNull
    @Override
    public MealAdapter.MealHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Assign the meal_item layout to the View item
        View item = inflater.inflate(R.layout.meal_item, parent, false);
        // Create a Log that the method is finished
        Log.i(TAG, "onCreateViewHolder method finished!");
        // Return MealHolder
        return new MealHolder(item, this);
    }

    // Connect the data to the MealHolder
    @Override
    public void onBindViewHolder(@NonNull MealAdapter.MealHolder holder, int position) {
        // Get the current Meal
        Meal currentMeal = (Meal) mealList.get(position);
        // Assign the values to the TextViews in the holder
        Glide.with(holder.itemView.getContext())
                .load(currentMeal.getImageUrl())
                .centerCrop()
                .into(holder.mealImage);
        holder.mealName.setText(currentMeal.getName());
        holder.mealDate.setText(sdf.format(currentMeal.getDateTime()));
        holder.mealCity.setText(currentMeal.getCook().getCity());
        holder.mealPrice.setText("€" + df.format(currentMeal.getPrice()));
        // Add an onclick on each item to navigate to the detail page
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Give the specific meal as intent
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("MEAL", currentMeal);
                // Start Activity with the intent
                holder.itemView.getContext().startActivity(intent);
            }
        });
        // Create a Log that the method is finished
        Log.i(TAG, "onBindViewHolder method finished!");
    }

    // Return the size from the mealList
    @Override
    public int getItemCount() {
        // Create a Log that the method is finished
        Log.i(TAG, "getItemCount method finished!");
        // Return the size of mealList
        return mealList.size();
    }
}

