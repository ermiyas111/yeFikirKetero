package com.aden.yefikirketero.UI.profile.OrganizedInfo.AboutOther;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aden.yefikirketero.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DateReligionQuestion extends AppCompatActivity {
    Button orthodoxButton;
    Button muslimButton;
    Button protestantButton;
    Button noProblemButton;

    Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_religion_question);

        orthodoxButton = findViewById(R.id.orthodox_date_religion);
        muslimButton = findViewById(R.id.muslim_date_religion);
        protestantButton = findViewById(R.id.protestant_date_religion);
        noProblemButton = findViewById(R.id.no_problem_religion);

        orthodoxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("MyDateReligion", orthodoxButton.getText().toString());
                myEdit.commit();

                Intent intent = new Intent(context, DateAgeQuestion.class);
                startActivity(intent);
            }
        });



        muslimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("MyDateReligion", muslimButton.getText().toString());
                myEdit.commit();

                Intent intent = new Intent(context, DateAgeQuestion.class);
                startActivity(intent);
            }
        });

        protestantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("MyDateReligion", protestantButton.getText().toString());
                myEdit.commit();

                Intent intent = new Intent(context, DateAgeQuestion.class);
                startActivity(intent);
            }
        });

        noProblemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("MyDateReligion", noProblemButton.getText().toString());
                myEdit.commit();

                Intent intent = new Intent(context, DateAgeQuestion.class);
                startActivity(intent);
            }
        });
    }
}
