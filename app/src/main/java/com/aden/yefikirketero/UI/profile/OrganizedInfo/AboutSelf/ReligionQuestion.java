package com.aden.yefikirketero.UI.profile.OrganizedInfo.AboutSelf;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aden.yefikirketero.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ReligionQuestion extends AppCompatActivity {

    Button orthodoxButton;
    Button muslimButton;
    Button protestantButton;

    Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_religion_question);

        orthodoxButton = findViewById(R.id.orthodox_religion);
        muslimButton = findViewById(R.id.muslim_religion);
        protestantButton = findViewById(R.id.protestant_religion);

        orthodoxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("MyReligion", orthodoxButton.getText().toString());
                myEdit.commit();

                Intent intent = new Intent(context, LocationQuestion.class);
                startActivity(intent);
            }
        });



        muslimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("MyReligion", muslimButton.getText().toString());
                myEdit.commit();

                Intent intent = new Intent(context, LocationQuestion.class);
                startActivity(intent);
            }
        });

        protestantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("MyReligion", protestantButton.getText().toString());
                myEdit.commit();

                Intent intent = new Intent(context, LocationQuestion.class);
                startActivity(intent);
            }
        });
    }
}
