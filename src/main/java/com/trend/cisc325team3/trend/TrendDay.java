package com.trend.cisc325team3.trend;

import android.content.res.Resources;

/**
 * Created by NJacobson on 15-10-24.
 */
public class TrendDay {

    private TrendLook look;
    private int day;

    public TrendDay(TrendLook look, int day) {
        this.look = look;
        this.day = day;
    }

    public int getDayOfMonth () {
        return day;
    }

    public int [] getColorPaletteIDs (Resources res, boolean isDayDisplay) {

        int background;
        int text;
        int border;

        if (day == -1) {
            background = res.getColor(R.color.blankTrendDay);
            text = res.getColor(R.color.blankTrendDayText);
            border = res.getColor(R.color.blankTrendDayBorder);
        } else if (look == null) {
            if (isDayDisplay) {
                background = res.getColor(R.color.lightTrendDay);
                text = res.getColor(R.color.darkTrendDayText);
                border = res.getColor(R.color.darkTrendDayBorder);
            } else {
                background = res.getColor(R.color.darkTrendDay);
                text = res.getColor(R.color.lightTrendDayText);
                border = res.getColor(R.color.lightTrendDayBorder);
            }
        } else {
            if (look.isGemmed()) {
                Utility.log("Setting colors: " + look.toString());
                Utility.log("gemmed look");
                background = res.getColor(R.color.gemmedTrendDay);
                text = res.getColor(R.color.gemmedTrendDayText);
                border = res.getColor(R.color.gemmedTrendDayBorder);
            } else if (look.isDay() && isDayDisplay) {
                Utility.log("Setting colors: " + look.toString());
                Utility.log("day look, day display");
                background = res.getColor(R.color.darkTrendDay);
                text = res.getColor(R.color.lightTrendDayText);
                border = res.getColor(R.color.darkTrendDayBorder);
            } else if (look.isDay() && !isDayDisplay) {
                Utility.log("Setting colors: " + look.toString());
                Utility.log("day look, night display");
                background = res.getColor(R.color.darkTrendDay);
                text = res.getColor(R.color.lightTrendDayText);
                border = res.getColor(R.color.lightTrendDayBorder);
            } else if (!look.isDay() && isDayDisplay) {
                Utility.log("Setting colors: " + look.toString());
                Utility.log("night look, day display");
                background = res.getColor(R.color.darkTrendDay);
                text = res.getColor(R.color.lightTrendDayText);
                border = res.getColor(R.color.darkTrendDayText);
            } else {
                Utility.log("Setting colors: " + look.toString());
                Utility.log("night look, night display");
                background = res.getColor(R.color.lightTrendDay);
                text = res.getColor(R.color.darkTrendDayText);
                border = res.getColor(R.color.lightTrendDayBorder);
            }
        }

        return new int [] {background, text, border};
    }

    public boolean hasLook() {
        if (look == null) {
            return false;
        } else {
            return true;
        }
    }
}
