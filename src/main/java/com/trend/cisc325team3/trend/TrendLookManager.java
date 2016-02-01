package com.trend.cisc325team3.trend;

import java.util.GregorianCalendar;
import java.util.LinkedList;

/**
 * Created by NJacobson on 15-11-11.
 */
public class TrendLookManager {

    public static TrendLook [] loadedLooks;
    public static int [] years;
    public static int [] months;
    public static TrendLook [][] monthLooks;

    public static void setLooks (TrendLook [] looks) {
        loadedLooks = looks;
        initMonthLooks();
    }

    private static void initMonthLooks () {
        int firstYear;
        int firstMonth;
        int lastYear;
        int lastMonth;

        boolean noLooks = loadedLooks.length == 0;

        if (noLooks) {
            GregorianCalendar calendar = new GregorianCalendar();

            firstYear = calendar.get(GregorianCalendar.YEAR);
            firstMonth = calendar.get(GregorianCalendar.MONTH) + 1;
            lastYear = calendar.get(GregorianCalendar.YEAR);
            lastMonth = calendar.get(GregorianCalendar.MONTH) + 1;
        } else {
            TrendLook firstLook = loadedLooks [0];
            TrendLook lastLook = loadedLooks [loadedLooks.length - 1];

            firstYear = firstLook.getYear();
            firstMonth = firstLook.getMonth();
            lastYear = lastLook.getYear();
            lastMonth = lastLook.getMonth();
        }


        int yearDiff = lastYear - firstYear;
        int monthDiff = lastMonth - firstMonth;
        int numOfMonths = yearDiff * 12 + monthDiff + 1;

        years = new int [numOfMonths];
        months = new int [numOfMonths];
        monthLooks = new TrendLook [numOfMonths][];

        int lookIndex = 0;

        for (int i = 0; i < numOfMonths; i ++) {
            years [i] = firstYear + (i + firstMonth - 1) / 12;
            months [i] = (firstMonth - 1 + i) % 12 + 1;

            Utility.log("month: " + months [i] + " i: " + i);
            Utility.log("year: " + years [i] + " i: " + i);

            LinkedList<TrendLook> linkedLooks = new LinkedList<>();

            if (!noLooks) {

                TrendLook look = loadedLooks[lookIndex];
                while (look.getYear() == years [i] && look.getMonth() == months [i]) {

                    linkedLooks.add(look);
                    lookIndex++;

                    if (lookIndex >= loadedLooks.length) {
                        break;
                    }

                    look = loadedLooks[lookIndex];
                }
            }

            monthLooks [i] = linkedLooks.toArray(new TrendLook [0]);
        }
    }

    public static TrendLook[] getLooksForMonth(int year, int month) {

        if (monthLooks == null) {
            return null;
        }

        for (int i = 0; i < years.length; i ++) {
            if (years [i] == year && months [i] == month) {
                return monthLooks [i];
            }
        }

        return null;
    }

    public static int getLookNumber(int year, int month, int day) {
        for (int i = 0; i < loadedLooks.length; i ++) {
            if (    loadedLooks [i].getDay() == day &&
                    loadedLooks [i].getMonth() == month &&
                    loadedLooks [i].getYear() == year) {
                return i;
            }
        }

        return Math.min(loadedLooks.length - 1, 0) ;
    }
}
