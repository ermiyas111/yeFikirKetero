package com.aden.yefikirketero.UI.profile.OrganizedInfo.AboutSelf;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aden.yefikirketero.R;
import com.aden.yefikirketero.UI.profile.DateBioQuestion;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AgeQuestion extends AppCompatActivity {

    Button continueButton;
    EditText ageValue;

    Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_question);

        continueButton = findViewById(R.id.button_continue_age);
        ageValue = findViewById(R.id.ageEditText);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = ageValue.getText().toString();
                if(!input.equals("")) {
                    try {
                        int num = Integer.parseInt(input);
                        Log.i("",num+" is a number");

                        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.putString("MyAge", ageValue.getText().toString());
                        myEdit.commit();

                        Intent intent = new Intent(context, ReligionQuestion.class);
                        startActivity(intent);
                    } catch (NumberFormatException e) {
                        Log.i("",input+" is not a number");
                    }

                }
            }
        });
    }
}
