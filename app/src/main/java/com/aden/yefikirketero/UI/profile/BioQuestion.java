package com.aden.yefikirketero.UI.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aden.yefikirketero.R;
import com.aden.yefikirketero.UI.profile.OrganizedInfo.AboutSelf.GenderQuestion;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BioQuestion extends AppCompatActivity {

    Button continueButton;
    EditText bioText;

    Context context = this;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio_question);

        continueButton = findViewById(R.id.button_continue_bio);
        bioText = findViewById(R.id.bioEditText);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = bioText.getText().toString();
                if(!input.equals("")) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("MyBio", bioText.getText().toString());
                    myEdit.commit();

                    Intent intent = new Intent(context, GenderQuestion.class);
                    startActivity(intent);
                }
            }
        });
    }
}
