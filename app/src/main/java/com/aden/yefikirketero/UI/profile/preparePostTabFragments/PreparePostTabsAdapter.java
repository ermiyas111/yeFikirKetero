package com.aden.yefikirketero.UI.profile.preparePostTabFragments;

import android.content.Context;

import com.aden.yefikirketero.UI.tabFragments.byChoiceTab.ForYouTab;
import com.aden.yefikirketero.UI.tabFragments.postedTab.PostedProfilesTab;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PreparePostTabsAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> fragmentsTitle = new ArrayList<>();

    public PreparePostTabsAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AboutYouForm aboutYouForm = new AboutYouForm();
                return aboutYouForm;
            case 1:
                AboutDateForm aboutDateForm = new AboutDateForm();
                return aboutDateForm;
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
