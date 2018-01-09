package com.whyshoudi.translation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Saksham Raj Shokeen on 1/3/2018.
 */

class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[]{"Tab1", "Tab2", "Tab3","hjds"};

    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new numberFragment();
        } else if (position == 1) {
            return new familyFragment();
        } else if (position == 2) {
            return new colorFragment();
        } else {
            return new phrasesFragment();

        }
    }

    @Override
    public int getCount() {
        return 4;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
