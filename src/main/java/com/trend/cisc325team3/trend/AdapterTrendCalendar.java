package com.trend.cisc325team3.trend;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.GregorianCalendar;
import java.util.LinkedList;

/**
 * Created by NJacobson on 15-10-26.
 */
public class AdapterTrendCalendar extends FragmentStatePagerAdapter {

    public AdapterTrendCalendar(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return TrendLookManager.monthLooks.length;
    }

    @Override
    public Fragment getItem(int position) {

        Utility.log("Getting new adapter trend calendar instance::");
        return FragmentTrendMonth.newInstance(
                TrendLookManager.years[position],
                TrendLookManager.months[position]
        );
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "MONTH " + (position + 1);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}