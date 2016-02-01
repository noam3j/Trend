package com.trend.cisc325team3.trend;

import java.util.Comparator;

/**
 * Created by NJacobson on 15-10-30.
 */
public class TrendLook implements Comparable <TrendLook> {

    private int year;
    private int month;
    private int day;
    private boolean isDay;
    private boolean isGemmed;

    public TrendLook(int year, int month, int day, boolean isDay, boolean isGemmed) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.isDay = isDay;
        this.isGemmed = isGemmed;
    }

    public String toString() {
        return "Year: " + year +
                ", Month: " + month +
                ", Day: " + day +
                ", isDay: " + isDay +
                ", isGemmed: " + isGemmed;
    }



    public boolean equals(Object otherObj) {

        TrendLook otherLook = (TrendLook) otherObj;

        if (this.year == otherLook.year &&
            this.month == otherLook.month &&
            this.day == otherLook.day &&
            this.isDay == otherLook.isDay) {

            return true;
        }

        return false;
    }

    public int getYear () {
        return this.year;
    }

    public int getMonth () {
        return this.month;
    }
    public int getDay () {
        return this.day;
    }

    public boolean isDay () {
        return this.isDay;
    }

    public boolean isGemmed () {
        return this.isGemmed;
    }

    public static int[] getDaysFromLooks(TrendLook[] looks) {

        int [] days = new int [looks.length];

        for (int i = 0; i < looks.length; i ++) {
            days [i] = looks [i].getDay();
        }

        return days;
    }

    public static boolean[] getIsDaysFromLooks(TrendLook [] looks) {

        boolean [] isDays = new boolean [looks.length];

        for (int i = 0; i < looks.length; i ++) {
            isDays [i] = looks [i].isDay();
        }

        return isDays;
    }

    public static boolean[] getIsGemmedFromLooks(TrendLook [] looks) {

        boolean [] isGemmed = new boolean [looks.length];

        for (int i = 0; i < looks.length; i ++) {
            isGemmed [i] = looks [i].isGemmed();
        }

        return isGemmed;
    }

    public void setGemmed(boolean isGemmed) {
        this.isGemmed = isGemmed;
    }

    @Override
    public int compareTo(TrendLook otherLook) {

        if (year < otherLook.year) {
            return -1;
        } else if (year > otherLook.year) {
            return 1;
        }

        //years are equal
        if (month < otherLook.month) {
            return -1;
        } else if (month > otherLook.month) {
            return 1;
        }

        //years and months are equal
        if (day < otherLook.day) {
            return -1;
        } else if (day > otherLook.day) {
            return 1;
        }

        //years months and days are equal
        return 0;
    }
}
