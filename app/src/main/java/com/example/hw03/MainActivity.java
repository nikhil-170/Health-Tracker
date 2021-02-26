package com.example.hw03;

/*
HomeWork03
FileName - Group1C_HW03
Group1B- Pramukh Nagendra &
         Nikhil Surya Petiti
 */


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements HealthRecordFragment.HealthRecListener, CreateHealthFragment.CreateHealthListener, DetailHealthFragment.DetailHealthListener {

    ArrayList<HealthRecord> recordList = new ArrayList<>();
    HealthRecord displayRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().add(R.id.rootLayout,new HealthRecordFragment(),"Health Record List").commit();

    }

    @Override
    public void callCreateHealthFrag() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootLayout, new CreateHealthFragment(), "Create Health")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public ArrayList<HealthRecord> getHealthRecordList() {
        return recordList;
    }

    @Override
    public void callDetailHealthFrag(HealthRecord displayRecord) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootLayout, new DetailHealthFragment(), "Detail Health")
                .addToBackStack(null)
                .commit();

        this.displayRecord = displayRecord;
    }

    @Override
    public void deleteHealthRecord(HealthRecord healthRecord) {
        recordList.remove(healthRecord);
    }

    @Override
    public void setHealthRecord(HealthRecord healthRecord) {
        recordList.add(healthRecord);
    }

    @Override
    public HealthRecord getDisplayRecord() {
        return displayRecord;
    }
}