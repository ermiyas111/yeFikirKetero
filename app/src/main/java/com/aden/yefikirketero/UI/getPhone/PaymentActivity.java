package com.aden.yefikirketero.UI.getPhone;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.aden.yefikirketero.R;
import com.aden.yefikirketero.UI.getPhone.PaymentTabsFragment.ConfirmPayment;
import com.aden.yefikirketero.UI.getPhone.PaymentTabsFragment.TurnOnAccessibility;
import com.aden.yefikirketero.UI.getPhone.PaymentTabsFragment.MakePayment;
import com.aden.yefikirketero.UI.getPhone.PaymentTabsFragment.PaymentTabsAdapter;
import com.google.android.material.tabs.TabLayout;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class PaymentActivity extends AppCompatActivity {

    Context context = this;
    TabLayout tabLayout;
    ViewPager viewPager;
    TurnOnAccessibility turnOnAccessibility;
    MakePayment makePayment;
    ConfirmPayment confirmPayment;
    String userPhoneNumber;
    EditText phoneInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        phoneInput = findViewById(R.id.phone_edit_text);

        turnOnAccessibility = new TurnOnAccessibility();
        makePayment = new MakePayment();
        confirmPayment = new ConfirmPayment();

        tabLayout.setupWithViewPager(viewPager);

        final PaymentTabsAdapter adapter = new PaymentTabsAdapter(this,getSupportFragmentManager(),
                tabLayout.getTabCount());
        adapter.addFragment(turnOnAccessibility,"አንድ");
        adapter.addFragment(makePayment, "ሁለት");
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        LinearLayout tabStrip = ((LinearLayout)tabLayout.getChildAt(0));
        for(int i = 0; i < tabStrip.getChildCount(); i++) {
            tabStrip.getChildAt(i).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
        }
    }


    public String getUserPhoneNumber(){
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

}
