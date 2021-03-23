package com.aden.yefikirketero.UI.profile;

import android.content.Context;
import android.os.Bundle;

import com.aden.yefikirketero.R;
import com.aden.yefikirketero.UI.profile.preparePostTabFragments.AboutDateForm;
import com.aden.yefikirketero.UI.profile.preparePostTabFragments.AboutYouForm;
import com.aden.yefikirketero.UI.profile.preparePostTabFragments.PreparePostTabsAdapter;
import com.aden.yefikirketero.UI.tabFragments.postedTab.PostedProfilesTab;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class PrepareMyPost extends AppCompatActivity {
    Context context = this;
    TabLayout tabLayout;
    ViewPager viewPager;
    AboutYouForm aboutYouForm;
    AboutDateForm aboutDateForm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare_my_post);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        aboutYouForm = new AboutYouForm();
        aboutDateForm = new AboutDateForm();

        tabLayout.setupWithViewPager(viewPager);

        final PreparePostTabsAdapter adapter = new PreparePostTabsAdapter(this,getSupportFragmentManager(),
                tabLayout.getTabCount());
        adapter.addFragment(aboutYouForm,getResources().getString(R.string.about_you));
        adapter.addFragment(aboutDateForm, getResources().getString(R.string.about_your_date));
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
    }

}
