package com.example.shareameal.applicationlogic.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shareameal.R;
import com.example.shareameal.domain.Participant;

import java.util.ArrayList;

public class ParticipantAdapter extends RecyclerView.Adapter<ParticipantAdapter.ParticipantHolder> {
    private final String TAG = ParticipantAdapter.class.getSimpleName();
    private ArrayList<Participant> participantList;
    private LayoutInflater inflater;

    // Constructor to create a ParticipantAdapter
    public ParticipantAdapter(Context context, ArrayList<Participant> participantList) {
        this.inflater = LayoutInflater.from(context);
        this.participantList = participantList;
        // Create a Log that the method is finished
        Log.i(TAG, "ParticipantAdapter constructor finished!");
    }

    // A class that holds the participants
    class ParticipantHolder extends RecyclerView.ViewHolder {
        private TextView participantName;
        private TextView participantEmail;
        private TextView participantPhoneNumber;
        private TextView participantAddress;
        private ParticipantAdapter adapter;

        // Constructor to create a ParticipantHolder
        public ParticipantHolder(@NonNull View itemView, ParticipantAdapter adapter) {
            super(itemView);
            this.participantName = itemView.findViewById(R.id.participant_name);
            this.participantEmail = itemView.findViewById(R.id.participant_email);
            this.participantPhoneNumber = itemView.findViewById(R.id.participant_phone);
            this.participantAddress = itemView.findViewById(R.id.participant_address);
            this.adapter = adapter;
            // Create a Log that the method is finished
            Log.i(TAG, "ParticipantHolder constructor finished!");
        }
    }

    // Inflate the item layout and return the ParticipantHolder
    @NonNull
    @Override
    public ParticipantAdapter.ParticipantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Assign the participant_item layout to the View item
        View item = inflater.inflate(R.layout.participant_item, parent, false);
        // Create a Log that the method is finished
        Log.i(TAG, "onCreateViewHolder method finished!");
        // Return ParticipantHolder
        return new ParticipantHolder(item, this);
    }

    // Connect the data to the ParticipantHolder
    @Override
    public void onBindViewHolder(@NonNull ParticipantAdapter.ParticipantHolder holder, int position) {
        // Get the current Participant
        Participant currentParticipant = (Participant) participantList.get(position);
        // Assign the values to the TextViews in the holder
        holder.participantName.setText(currentParticipant.getFirstName() + " " + currentParticipant.getLastName());
        holder.participantEmail.setText(currentParticipant.getEmailAddress());
        holder.participantPhoneNumber.setText(currentParticipant.getPhoneNumber());
        holder.participantAddress.setText(currentParticipant.getStreet() + ", " + currentParticipant.getCity());
        // Create a Log that the method is finished
        Log.i(TAG, "onBindViewHolder method finished!");
    }

    // Return the size from the participantList
    @Override
    public int getItemCount() {
        // Create a Log that the method is finished
        Log.i(TAG, "getItemCount method finished!");
        // Return the size of participantList
        return participantList.size();
    }
}
