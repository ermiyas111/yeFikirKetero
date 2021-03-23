package com.aden.yefikirketero.UI.profile;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aden.yefikirketero.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ShowTheWayToProfile extends AppCompatActivity {

    Button continueButton;
    Button cancelButton;

    Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_the_way_to_profile);

        continueButton = findViewById(R.id.button_continue_show_the_way);
        cancelButton = findViewById(R.id.button_cancel_show_the_way);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                if (sharedPreferences.contains("LoggedIn")) {
                    Boolean isAccountLoggedIn = sharedPreferences.getBoolean("LoggedIn", false);
                    if(isAccountLoggedIn){
                        Intent intent = new Intent(context, PhoneVerification.class);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(context, PhoneVerification.class);
                        startActivity(intent);
                    }
                }else{
                    Intent intent = new Intent(context, PhoneVerification.class);
                    startActivity(intent);
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LauncherActivity.class);
                startActivity(intent);
            }
        });
    }}
