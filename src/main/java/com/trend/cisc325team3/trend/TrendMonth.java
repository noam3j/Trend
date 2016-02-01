package com.trend.cisc325team3.trend;

import android.util.Log;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by NJacobson on 15-10-24.
 */
public class TrendMonth {

    private int year;
    private int month;
    private int startIndex;
    private TrendDay [] days;

    public TrendMonth (int year, int month, TrendLook [] monthLooks) {
        this.year = year;
        this.month = month;
        initTrendDays(year, month, monthLooks);
    }

    private void initTrendDays(int year, int month, TrendLook [] looks) {
        Utility.log("init trend days min month stuff");
        DeleteMeDatabaseTestFiller.logLooks(looks);

        GregorianCalendar calendar = new GregorianCalendar(year, month - 1, 1);
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int startDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        startIndex = startDayOfWeek - 1;

        int arrayLength;

        if ((startDayOfWeek >= 6 && daysInMonth == 31) || (startDayOfWeek == 7 && daysInMonth == 30)) {
            arrayLength = 42;
        } else if (startDayOfWeek >= 1 && daysInMonth == 28) {
            arrayLength = 28;
        } else {
            arrayLength = 35;
        }
        //extra days are just null
        days = new TrendDay [arrayLength];

        for (int i = 0; i < startIndex; i ++) {
            days [i] = new TrendDay(null, -1);
        }

        //find the start point of the looks for this month
        int lookIndex = 0;

        for (int i = 0; i < daysInMonth; i ++) {

            TrendLook look = null;

            if (lookIndex < looks.length && looks [lookIndex].getDay() == (i + 1)) {
                look = looks [lookIndex];
                lookIndex ++;
            }

            days [i + startIndex] = new TrendDay(look, i + 1);
        }

        for (int i = startIndex + daysInMonth; i < arrayLength; i ++) {
            days [i] = new TrendDay(null, -1);
        }
    }

    public int daysInMonth () {
        return days.length;
    }

    public TrendDay getTrendDay(int position, boolean includeBlanks) {
        if (includeBlanks) {
            return days [position];
        } else {
            return days [startIndex + position];
        }
    }

    public int getYear () {
        return year;
    }

    public int getMonth () {
        return month;
    }
}
