package com.aden.yefikirketero.UI.tabFragments;

import android.content.Context;

import com.aden.yefikirketero.UI.tabFragments.byChoiceTab.ForYouTab;
import com.aden.yefikirketero.UI.tabFragments.postedTab.PostedProfilesTab;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class LauncherTabsAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    public LauncherTabsAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                PostedProfilesTab postedProfilesTab = new PostedProfilesTab();
                return postedProfilesTab;
            case 1:
                ForYouTab forYouTab = new ForYouTab();
                return forYouTab;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return totalTabs;
    }
}
