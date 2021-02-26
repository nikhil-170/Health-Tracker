package com.example.hw03;

/*
HomeWork03
FileName - Group1C_HW03
Group1B- Pramukh Nagendra &
         Nikhil Surya Petiti
 */


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class CreateHealthFragment extends Fragment {

    EditText nameEditText, dobEditText, phoneEditText;
    SeekBar seekBar;
    TextView seekBarText;
    Button submitButton, cancelButton;
    String name, phone;
    int age, mood;
    int seekBarChanged;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_health, container, false);

        nameEditText = view.findViewById(R.id.nameEditText);
        dobEditText = view.findViewById(R.id.dobEditText);
        phoneEditText = view.findViewById(R.id.phoneEditText);
        seekBar = view.findViewById(R.id.seekBar);
        seekBarText = view.findViewById(R.id.seekBarText);
        submitButton = view.findViewById(R.id.submitButton);
        cancelButton = view.findViewById(R.id.cancelButton);

        seekBarChanged = 0;
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                seekBarChanged = 1;
                seekBarText.setText(progress + "");
                mood = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekBarChanged = 1;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBarChanged = 1;
            }
        });

        dobEditText.setFocusable(false);
        dobEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int month, int day) {

                                dobEditText.setText((month + 1) + "/" + day + "/" + year);

                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nameEditText.getText().toString().matches("") || !nameEditText.getText().toString().matches("[a-zA-Z\\s]*")) {
                    Toast.makeText(getContext(), R.string.valid_name, Toast.LENGTH_SHORT).show();
                }else if(dobEditText.getText().toString().matches("")){
                    Toast.makeText(getContext(), R.string.valid_dob, Toast.LENGTH_SHORT).show();
                }else if(phoneEditText.getText().toString().matches("") || !phoneEditText.getText().toString().matches("[0-9]*")){
                    Toast.makeText(getContext(), R.string.valid_phone, Toast.LENGTH_SHORT).show();
                }else if(seekBarChanged==0){
                    Toast.makeText(getContext(), R.string.rate, Toast.LENGTH_SHORT).show();
                }else {
                    name = nameEditText.getText().toString();
                    String temp = dobEditText.getText().toString();
                    String[] date = temp.split("/");
                    age = getAge(Integer.parseInt(date[2]), Integer.parseInt(date[0]), Integer.parseInt(date[1]));
                    phone = phoneEditText.getText().toString();
                    createHealthListener.setHealthRecord(new HealthRecord(name, age, phone, mood));
                    getFragmentManager().popBackStack();
                }
            }
        });


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });


        return view;
    }

    private int getAge(int DOByear, int DOBmonth, int DOBday){
        int age;

        final Calendar calenderToday = Calendar.getInstance();
        int currentYear = calenderToday.get(Calendar.YEAR);
        int currentMonth = 1 + calenderToday.get(Calendar.MONTH);
        int todayDay = calenderToday.get(Calendar.DAY_OF_MONTH);

        age = currentYear - DOByear;

        if(DOBmonth > currentMonth) {
            --age;
        } else if(DOBmonth == currentMonth) {
            if(DOBday > todayDay){
                --age;
            }
        }
        return age;
    }



    CreateHealthListener createHealthListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof CreateHealthListener) {
            createHealthListener = (CreateHealthListener) context;
        } else {
            throw new RuntimeException(context.toString() + "not running");
        }
    }

    public interface CreateHealthListener {

        public void setHealthRecord(HealthRecord healthRecord);
    }
}