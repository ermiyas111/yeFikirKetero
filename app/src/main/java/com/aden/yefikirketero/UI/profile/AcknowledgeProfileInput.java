package com.aden.yefikirketero.UI.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aden.yefikirketero.R;
import com.aden.yefikirketero.UI.LauncherActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AcknowledgeProfileInput extends AppCompatActivity {

    Button finishButton;
    Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acknowledge_profile_input);

        finishButton = findViewById(R.id.buttonFinishProfile);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LauncherActivity.class);
                startActivity(intent);
            }
        });
    }
}
