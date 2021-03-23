package com.aden.yefikirketero.UI.profile.OrganizedInfo.AboutSelf;

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

public class NameQuestion extends AppCompatActivity {

    Button continueButton;
    EditText nameText;

    Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_question);

        continueButton = findViewById(R.id.button_continue_name);
        nameText = findViewById(R.id.nameEditText);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = nameText.getText().toString();
                if(!input.equals("")) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("MyName", nameText.getText().toString());
                    myEdit.commit();

                    Intent intent = new Intent(context, AgeQuestion.class);
                    startActivity(intent);
                }
            }
        });
    }
}
