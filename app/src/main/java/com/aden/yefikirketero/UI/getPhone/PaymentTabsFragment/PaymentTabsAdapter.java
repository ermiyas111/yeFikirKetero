package com.aden.yefikirketero.UI.getPhone.PaymentTabsFragment;

import android.content.Context;
import android.view.MotionEvent;

import com.aden.yefikirketero.UI.profile.preparePostTabFragments.AboutDateForm;
import com.aden.yefikirketero.UI.profile.preparePostTabFragments.AboutYouForm;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PaymentTabsAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> fragmentsTitle = new ArrayList<>();

    public PaymentTabsAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                InputPhone inputPhone = new InputPhone();
                return inputPhone;
            case 1:
                MakePayment makePayment = new MakePayment();
                return makePayment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    public void addFragment(Fragment fragment, String title){
        fragments.add(fragment);
        fragmentsTitle.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentsTitle.get(position);
    }
}
