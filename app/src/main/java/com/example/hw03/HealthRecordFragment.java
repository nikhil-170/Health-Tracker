package com.example.hw03;

/*
HomeWork03
FileName - Group1C_HW03
Group1B- Pramukh Nagendra &
         Nikhil Surya Petiti
 */


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class HealthRecordFragment extends Fragment {

    Button addHealthRecButton;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    static TextView noRecTextView;
    static ArrayList<HealthRecord> healthList;
    static HealthRecyclerViewAdapter adapter;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_health_record, container, false);

        addHealthRecButton = view.findViewById(R.id.addHealthRecButton);
        recyclerView = view.findViewById(R.id.recyclerView);
        noRecTextView = view.findViewById(R.id.noRecTextView);

        context =getActivity();

        healthList = healthRecListener.getHealthRecordList();
        if(healthList.size()>0){
            noRecTextView.setVisibility(View.INVISIBLE);
        }

        //RecyclerView Adapter
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HealthRecyclerViewAdapter(healthList, context);
        recyclerView.setAdapter(adapter);


        addHealthRecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                healthRecListener.callCreateHealthFrag();
            }
        });


        return view;
    }

    public static HealthRecListener healthRecListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof HealthRecListener){
            healthRecListener = (HealthRecListener) context;
        }else{
            throw new RuntimeException(context.toString()+"not running");
        }
    }

    public interface HealthRecListener{
        void callCreateHealthFrag();
        ArrayList<HealthRecord> getHealthRecordList();
        void callDetailHealthFrag(HealthRecord displayRecord);
        void deleteHealthRecord(HealthRecord healthRecord);
    }

    public static void updateListAfterDelete(){
        healthList = healthRecListener.getHealthRecordList();
        adapter.notifyDataSetChanged();
        if(healthList.size()<=0){
            noRecTextView.setVisibility(View.VISIBLE);
        }
    }
}