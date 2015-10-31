package com.eba.aislatech;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by c-mchennur on 10/27/2015.
 */
public class MyFragmentPageAdapter extends FragmentPagerAdapter{

    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "Upcoming", "Calendar" };
    private Context context;

        public MyFragmentPageAdapter(FragmentManager fm) {
            super(fm);

        }
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int pos) {
        switch(pos) {

            case 0: return FragmentEvents.newInstance(0);
            case 1: return  FragmentCalendar.newInstance(1);

            default: return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

    }

