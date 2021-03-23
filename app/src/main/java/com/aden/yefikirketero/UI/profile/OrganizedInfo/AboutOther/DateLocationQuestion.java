package com.aden.yefikirketero.UI.profile.OrganizedInfo.AboutOther;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aden.yefikirketero.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DateLocationQuestion extends AppCompatActivity {

    Button continueButton;
    EditText dateLocationText;

    Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_location_question);

        continueButton = findViewById(R.id.button_continue_location);
        dateLocationText = findViewById(R.id.dateLocationEditText);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = dateLocationText.getText().toString();
                if(!input.equals("")) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("MyDateLocation", dateLocationText.getText().toString());
                    myEdit.commit();

                    Intent intent = new Intent(context, DateReligionQuestion.class);
                    startActivity(intent);
                }
            }
        });

    }
}
