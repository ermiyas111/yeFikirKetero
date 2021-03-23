package com.aden.yefikirketero.UI.profile.OrganizedInfo.AboutSelf;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aden.yefikirketero.R;
import com.aden.yefikirketero.UI.profile.BioQuestion;
import com.aden.yefikirketero.UI.profile.OrganizedInfo.AboutOther.DateAgeQuestion;
import com.aden.yefikirketero.UI.profile.OrganizedInfo.AboutOther.DateLocationQuestion;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LocationQuestion extends AppCompatActivity {

    Button continueButton;
    EditText locationText;

    Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_question);

        continueButton = findViewById(R.id.button_continue_location);
        locationText = findViewById(R.id.locationEditText);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = locationText.getText().toString();
                if(!input.equals("")) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("MyLocation", locationText.getText().toString());
                    myEdit.commit();

                    Intent intent = new Intent(context, DateLocationQuestion.class);
                    startActivity(intent);
                }
            }
        });
    }
}
