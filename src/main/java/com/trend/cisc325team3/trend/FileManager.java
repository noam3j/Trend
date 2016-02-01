package com.trend.cisc325team3.trend;

import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by NJacobson on 15-10-31.
 */
public class FileManager {

    public static Uri getLookUri (int year, int month, int day, boolean isDay) {
        String dirName = getTrendDirectoryName(year, month, day, isDay);
        return Uri.fromFile(getOutputMediaFile(dirName));
    }

    public static File getLookFile (int year, int month, int day, boolean isDay) {
        String dirName = getTrendDirectoryName(year, month, day, isDay);
        return getOutputMediaFile(dirName);
    }

    public static void deleteLookFile (int year, int month, int day, boolean isDay) {
        String dirName = getTrendDirectoryName(year, month, day, isDay);
        getOutputMediaFile(dirName).delete();
    }

    private static File getOutputMediaFile(String dirName){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), dirName);

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Utility.log("failed to create directory: " +
                        mediaStorageDir.getAbsolutePath());
                return null;
            }
        }

        File mediaFile = new File(mediaStorageDir.getPath() + File.separator
                + "Look.jpg");

        return mediaFile;
    }

    private static String getTrendDirectoryName(int year, int month, int day, boolean isDay) {
        String dayOrNight;
        if (isDay) {
            dayOrNight = "day";
        } else {
            dayOrNight = "night";
        }

        String dirName =
                "Trend" + File.separator +
                year + File.separator +
                month + File.separator +
                day + File.separator +
                dayOrNight;

        return dirName;
    };
}
