package com.trend.cisc325team3.trend;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by NJacobson on 15-11-01.
 */
public class AdapterTrendLooks extends FragmentStatePagerAdapter {

    private int currentIndex;

    public AdapterTrendLooks(FragmentManager fragmentManager, int index) {
        super(fragmentManager);
        currentIndex = index;
    }

    public AdapterTrendLooks(FragmentManager fragmentManager, int year, int month, int day) {
        super(fragmentManager);
        currentIndex = TrendLookManager.getLookNumber(year, month, day);
    }

    @Override
    public int getCount() {
        //Utility.log("checking count: " + TrendLookManager.loadedLooks.length);
        return TrendLookManager.loadedLooks.length;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentTrendLook.newInstance(TrendLookManager.loadedLooks[position]);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Look " + (position + 1);
    }

    public int getCurrentItem () {
        return currentIndex;
    }

}