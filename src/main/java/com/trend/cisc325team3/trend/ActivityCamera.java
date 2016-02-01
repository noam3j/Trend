package com.trend.cisc325team3.trend;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ActivityCamera extends AppCompatActivity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int DAY_START = 5;
    private static final int DAY_END = 18;

    private int year;
    private int month;
    private int day;
    private boolean isDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GregorianCalendar calendar = new GregorianCalendar();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour > DAY_START && hour < DAY_END) {
            isDay = true;
        } else {
            //isDay = false;
            isDay = true;
        }

        //create new Intent
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri fileUri = FileManager.getLookUri(year, month, day, isDay);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the Video Capture Intent
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                Utility.log("this is the month: " + month);

                //would like to do this asynchronously, but shortcut for project
                DBHelper.saveNewLook(
                        getApplicationContext(),
                        new TrendLook(year, month, day, isDay, false),
                        true);

                //not very efficient, but shortcut for project
                TrendLook [] trendLooks = DBHelper.loadLooks(getApplicationContext());
                TrendLookManager.setLooks(trendLooks);

                //Delete this
                //DeleteMeDatabaseTestFiller.logLooks(getApplicationContext());

                Intent intent = new Intent(getApplicationContext(), ActivityLooks.class);
                intent.putExtra("year", year);
                intent.putExtra("month", month);
                intent.putExtra("day", day);
                intent.putExtra("isday", isDay);
                startActivity(intent);
                finish();

            } else if (resultCode == RESULT_CANCELED) {
                finish();
            } else {
                Log.e(Utility.LOG_TAG, "The camera app returned a result that wasn't ok or canceled");
            }
        }
    }

}
