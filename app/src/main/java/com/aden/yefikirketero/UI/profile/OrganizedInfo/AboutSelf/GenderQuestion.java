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

public class GenderQuestion extends AppCompatActivity {

    Button maleButton;
    Button femaleButton;

    Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender_question);

        maleButton = findViewById(R.id.male_gender);
        femaleButton = findViewById(R.id.female_gender);

        maleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("MyGender", maleButton.getText().toString());
                myEdit.commit();

                Intent intent = new Intent(context, NameQuestion.class);
                startActivity(intent);
            }
        });



        femaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("MyGender", femaleButton.getText().toString());
                myEdit.commit();

                Intent intent = new Intent(context, NameQuestion.class);
                startActivity(intent);
            }
        });
    }
}
