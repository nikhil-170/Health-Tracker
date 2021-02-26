package com.example.hw03;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

/*
HomeWork03
FileName - Group1C_HW03
Group1B- Pramukh Nagendra &
         Nikhil Surya Petiti
 */


import java.util.ArrayList;

public class HealthRecyclerViewAdapter extends RecyclerView.Adapter<HealthRecyclerViewAdapter.HealthViewHolder> {

    ArrayList<HealthRecord> recordsList;
    Context context;

    public HealthRecyclerViewAdapter(ArrayList<HealthRecord> data, Context context) {
        this.context = context;
        recordsList = data;
    }


    @NonNull
    @Override
    public HealthViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.health_row_item, parent,false);
        return new HealthViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HealthViewHolder holder, int position) {

        HealthRecord healthRecord = recordsList.get(position);
        holder.listNameTV.setText(healthRecord.name);
        holder.listAgeTV.setText(context.getResources().getString(R.string.age)+healthRecord.age+"");
        setMoodViews(holder, healthRecord);

        holder.record = healthRecord;
    }

    @Override
    public int getItemCount() {
        return this.recordsList.size();
    }

    public static class HealthViewHolder extends RecyclerView.ViewHolder{

        ImageView listImageView;
        TextView listNameTV, listMoodTV, listAgeTV;
        Button deleteButton;
        HealthRecord record;

        public HealthViewHolder(@NonNull View itemView) {
            super(itemView);

            listImageView = itemView.findViewById(R.id.listImageView);
            listNameTV = itemView.findViewById(R.id.listNameTV);
            listMoodTV = itemView.findViewById(R.id.listMoodTV);
            listAgeTV = itemView.findViewById(R.id.listAgeTV);
            deleteButton = itemView.findViewById(R.id.deleteButton);

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HealthRecordFragment.healthRecListener.deleteHealthRecord(record);
                    HealthRecordFragment.updateListAfterDelete();
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HealthRecordFragment.healthRecListener.callDetailHealthFrag(record);
                }
            });
        }
    }

    @SuppressLint("SetTextI18n")
    private void setMoodViews(HealthViewHolder holder, HealthRecord healthRecord) {

        int moodVal = healthRecord.mood;
        if (moodVal >= 0 && moodVal < 2) {
            holder.listMoodTV.setText(context.getResources().getString(R.string.feeling)+context.getResources().getString(R.string.not_well));
            holder.listImageView.setImageResource(R.drawable.not_well);
        } else if (moodVal >= 2 && moodVal < 4) {
            holder.listMoodTV.setText(context.getResources().getString(R.string.feeling)+context.getResources().getString(R.string.sad));
            holder.listImageView.setImageResource(R.drawable.sad);

        } else if (moodVal >= 4 && moodVal < 6) {
            holder.listMoodTV.setText(context.getResources().getString(R.string.feeling)+context.getResources().getString(R.string.ok));
            holder.listImageView.setImageResource(R.drawable.ok);

        } else if (moodVal >= 6 && moodVal < 8) {
            holder.listMoodTV.setText(context.getResources().getString(R.string.feeling)+context.getResources().getString(R.string.good));
            holder.listImageView.setImageResource(R.drawable.good);

        } else if (moodVal >= 8 && moodVal <= 10) {
            holder.listMoodTV.setText(context.getResources().getString(R.string.feeling)+context.getResources().getString(R.string.very_good));
            holder.listImageView.setImageResource(R.drawable.very_good);
        }
    }

}
