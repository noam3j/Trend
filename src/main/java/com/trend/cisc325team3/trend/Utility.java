package com.trend.cisc325team3.trend;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * Created by NJacobson on 15-10-24.
 */

public class Utility {

    public final static boolean DEV_MODE = false;
    public final static boolean DEBUG_MODE = false;

    public final static String LOG_TAG = "Trend App";

    public static int pxToDp(int px, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    public static int dpToPx(int dp, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public static String getMonthFromInt(int month) {
        String monthString;

        switch (month){
            case 1:
                monthString = "January";
                break;
            case 2:
                monthString = "February";
                break;
            case 3:
                monthString = "March";
                break;
            case 4:
                monthString = "April";
                break;
            case 5:
                monthString = "May";
                break;
            case 6:
                monthString = "June";
                break;
            case 7:
                monthString = "July";
                break;
            case 8:
                monthString = "August";
                break;
            case 9:
                monthString = "September";
                break;
            case 10:
                monthString = "October";
                break;
            case 11:
                monthString = "November";
                break;
            case 12:
                monthString = "December";
                break;
            default:
                monthString = "problem";
                break;
        }

        return monthString;
    }

    public static String getShortMonthFromInt(int month) {
        String monthString;

        switch (month){
            case 1:
                monthString = "JAN";
                break;
            case 2:
                monthString = "FEB";
                break;
            case 3:
                monthString = "MAR";
                break;
            case 4:
                monthString = "APR";
                break;
            case 5:
                monthString = "MAY";
                break;
            case 6:
                monthString = "JUNE";
                break;
            case 7:
                monthString = "JULY";
                break;
            case 8:
                monthString = "AUG";
                break;
            case 9:
                monthString = "SEPT";
                break;
            case 10:
                monthString = "OCT";
                break;
            case 11:
                monthString = "NOV";
                break;
            case 12:
                monthString = "DEC";
                break;
            default:
                monthString = "problem";
                break;
        }

        return monthString;
    }

    public static String getDayOfWeekFromInt (int day) {

        String dayString;

        switch (day) {

            case 1:
                dayString = "S";
                break;
            case 2:
                dayString = "M";
                break;
            case 3:
                dayString = "T";
                break;
            case 4:
                dayString = "W";
                break;
            case 5:
                dayString = "T";
                break;
            case 6:
                dayString = "F";
                break;
            case 7:
                dayString = "S";
                break;
            default:
                dayString = "problem";
                break;
        }

        return dayString;
    }

    public enum Font {
        BOLD ("OpenSans-Bold.ttf"),
        BOLD_ITALIC ("OpenSans-BoldItalic.ttf"),
        EXTRA_BOLD ("OpenSans-ExtraBold.ttf"),
        EXTRA_BOLD_ITALIC ("OpenSans-ExtraBoldItalic.ttf"),
        ITALIC ("OpenSans-Italic.ttf"),
        LIGHT ("OpenSans-Light.ttf"),
        LIGHT_ITALIC ("OpenSans-LightItalic.ttf"),
        REGULAR ("OpenSans-Regular.ttf"),
        SEMI_BOLD ("OpenSans-Semibold.ttf"),
        SEMI_BOLD_ITALIC ("OpenSans-SemiboldItalic.ttf");

        private String filename;

        Font(String filename) {
            this.filename = filename;
        }

        public String toString() {
            return filename;
        }
    }

    public static Typeface getFont(Font fontType, Context context) {
        Typeface font = Typeface.createFromAsset(context.getAssets(),  "fonts/" + fontType.toString());
        return font;
    }

    public static boolean toBoolean(int x) {
        return x != 0;
    }

    public static int toInt(boolean bool) {
        if (bool)
            return 1;
        else
            return 0;
    }

    public static int random(int low, int high) {
        return (int)(Math.random() * (high + 1 - low) + low);
    }

    public static void log (String message) {

        if (DEBUG_MODE) {
            Log.d(LOG_TAG, message);
        }
    }

}
