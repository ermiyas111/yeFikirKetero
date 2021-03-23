package com.aden.yefikirketero.UI.getPhone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aden.yefikirketero.R;
import com.aden.yefikirketero.UI.LauncherActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TurnOnAccessibilityService extends AppCompatActivity {

    Button buttonGoToSettings;
    Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_describe_payment_options);

        buttonGoToSettings = findViewById(R.id.buttonInsideCountry);
        buttonGoToSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LauncherActivity.class);
                startActivity(intent);
            }
        });
    }
}
