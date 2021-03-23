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

public class DateBioQuestion extends AppCompatActivity {
    Button continueButton;
    EditText dateBioText;

    Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_bio_question);

        continueButton = findViewById(R.id.button_continue_date_bio);
        dateBioText = findViewById(R.id.bioDateEditText);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = dateBioText.getText().toString();
                if(!input.equals("")) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("MyDateBio", dateBioText.getText().toString());
                    myEdit.commit();

                    SharedPreferences sharedPreferences2 = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                    if (sharedPreferences2.contains("LoggedIn")) {
                        Boolean isAccountLoggedIn = sharedPreferences2.getBoolean("LoggedIn", false);
                        if (isAccountLoggedIn) {
                            Intent intent = new Intent(context, RecordAudio.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(context, GenderQuestion.class);
                            startActivity(intent);
                        }
                    }else{
                        Intent intent = new Intent(context, GenderQuestion.class);
                        startActivity(intent);
                    }

                }
            }
        });
    }
}
