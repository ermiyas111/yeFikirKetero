package com.aden.yefikirketero.UI.profile.OrganizedInfo.AboutOther;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aden.yefikirketero.R;
import com.aden.yefikirketero.UI.profile.OrganizedInfo.AboutSelf.GenderQuestion;
import com.aden.yefikirketero.UI.profile.RecordAudio;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DateAgeQuestion extends AppCompatActivity {

    Button continueButton;
    EditText dateStartAgeValue;
    EditText dateTopAgeValue;

    Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_age_question);

        continueButton = findViewById(R.id.button_continue_date_age);
        dateStartAgeValue = findViewById(R.id.dateStartAge);
        dateTopAgeValue = findViewById(R.id.dateTopAge);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = dateStartAgeValue.getText().toString();
                if(!input.equals("")) {
                    try {
                        int num = Integer.parseInt(input);
                        Log.i("", num + " is a number");

                        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.putString("MyDateStartAge", dateStartAgeValue.getText().toString());
                        myEdit.commit();

                        SharedPreferences sharedPreferences2 = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor myEdit2 = sharedPreferences2.edit();
                        myEdit2.putString("MyDateTopAge", dateTopAgeValue.getText().toString());
                        myEdit2.commit();

                        Intent intent = new Intent(context, RecordAudio.class);
                        startActivity(intent);
                    } catch (NumberFormatException e) {
                        Log.i("",input+" is not a number");
                    }

                }
            }
        });
    }
}
