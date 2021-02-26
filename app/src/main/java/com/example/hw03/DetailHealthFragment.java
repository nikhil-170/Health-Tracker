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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailHealthFragment extends Fragment {

    TextView nameTV, ageTV, phoneTV, moodValueTV, moodText;
    ImageView moodImage;
    Button buttonCancel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_health, container, false);

        nameTV = view.findViewById(R.id.nameTV);
        ageTV = view.findViewById(R.id.ageTV);
        phoneTV = view.findViewById(R.id.phoneTV);
        moodValueTV = view.findViewById(R.id.moodValueTV);
        moodText = view.findViewById(R.id.moodText);
        moodImage = view.findViewById(R.id.moodImage);
        buttonCancel = view.findViewById(R.id.buttonCancel);

        HealthRecord displayData = detailHealthListener.getDisplayRecord();
        nameTV.setText(displayData.name);
        ageTV.setText(displayData.age+"");
        phoneTV.setText(displayData.phone+"");
        moodValueTV.setText(displayData.mood+" "+getResources().getString(R.string.out_of_10));

        int moodVal = displayData.mood;
        if (moodVal >= 0 && moodVal < 2) {
            moodText.setText(getResources().getString(R.string.not_well));
            moodImage.setImageResource(R.drawable.not_well);
        } else if (moodVal >= 2 && moodVal < 4) {
            moodText.setText(getResources().getString(R.string.sad));
            moodImage.setImageResource(R.drawable.sad);

        } else if (moodVal >= 4 && moodVal < 6) {
            moodText.setText(getResources().getString(R.string.ok));
            moodImage.setImageResource(R.drawable.ok);

        } else if (moodVal >= 6 && moodVal < 8) {
            moodText.setText(getResources().getString(R.string.good));
            moodImage.setImageResource(R.drawable.good);

        } else if (moodVal >= 8 && moodVal <= 10) {
            moodText.setText(getResources().getString(R.string.very_good));
            moodImage.setImageResource(R.drawable.very_good);
        }


        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });

        return view;
    }

    DetailHealthListener detailHealthListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof DetailHealthListener){
            detailHealthListener = (DetailHealthListener) context;
        }else{
            throw new RuntimeException(context.toString()+ "not running");
        }
    }

    public interface DetailHealthListener{
        HealthRecord getDisplayRecord();
    }
}