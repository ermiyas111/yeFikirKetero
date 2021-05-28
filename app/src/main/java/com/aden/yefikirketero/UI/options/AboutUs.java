package com.aden.yefikirketero.UI.options;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.aden.yefikirketero.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AboutUs extends AppCompatActivity {

    TextView telegramContact, phoneContact;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        telegramContact= findViewById(R.id.telegram_contact);
        phoneContact= findViewById(R.id.phone_contact);

        telegramContact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent telegram = new Intent(Intent.ACTION_VIEW , Uri.parse("https://telegram.me/yefikerketerohelperbot"));
                startActivity(telegram);
            }
        });

        phoneContact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + getResources().getString(R.string.phone_contact)));
                startActivity(intent);
            }
        });


    }
}
