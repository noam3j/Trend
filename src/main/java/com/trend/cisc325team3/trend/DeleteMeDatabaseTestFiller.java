package com.trend.cisc325team3.trend;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by NJacobson on 15-11-01.
 */
public class DeleteMeDatabaseTestFiller {

    public static void logLooks (Context context) {
        TrendLook [] testLooks = DBHelper.loadLooks(context);

        for (int i = 0; i < testLooks.length; i ++) {
            Utility.log(testLooks[i].toString());
        }
    }

    public static void logLooks (TrendLook [] testLooks) {
        for (int i = 0; i < testLooks.length; i ++) {
            Utility.log(testLooks[i].toString());
        }
    }

    public static void addNewLook (TrendLook look, Context context) {
        DBHelper.saveNewLook(context,
                look, true);
    }

    public static Intent addNewRandomLook (Context context) {
        int year = random(2015, 2015);
        int month = random(6, 12);
        int day = random(1, 28);
        boolean isDay = Utility.toBoolean(random(0, 1));
        boolean isGemmed = Utility.toBoolean(random(0, 1));

        TrendLook look = new TrendLook(year, month, day, isDay, isGemmed);

        DBHelper.saveNewLook(context,
                look, true);

        Utility.log("Saved new look: " + look.toString());

        Intent intent= new Intent(context, ActivityLooks.class);
        intent.putExtra("year", year);
        intent.putExtra("month", month);
        intent.putExtra("day", day);
        intent.putExtra("isday", isDay);
        return intent;
    }

    private static int random(int low, int high) {
        return (int)(Math.random() * (high + 1 - low) + low);
    }

}
