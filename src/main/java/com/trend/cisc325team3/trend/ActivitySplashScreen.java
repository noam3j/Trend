package com.trend.cisc325team3.trend;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.GregorianCalendar;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ActivitySplashScreen extends AppCompatActivity {

    int SPLASH_TIME_OUT = 3000;
    boolean doneSetup = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {

                TrendLook[] looks = DBHelper.loadLooks(getApplicationContext());

                //if there are no looks and we are running in developer mode
                if (looks.length == 0 && Utility.DEV_MODE) {
                    //prepopulate looks from the devLooks folder in assets
                    devFillPictures();
                    looks = DBHelper.loadLooks(getApplicationContext());
                }

                Arrays.sort(looks);

                TrendLookManager.setLooks(looks);

                doneSetup = true;
            }
        }, 0);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {

                Intent intent = new Intent(getApplicationContext(), ActivityCalendar.class);
                startActivity(intent);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private void devFillPictures () {
        AssetManager assetManager = getAssets();
        String assetsDirectory = "devLooks";
        String[] files = null;
        try {
            files = assetManager.list(assetsDirectory);

        } catch (IOException e) {
            Utility.log("Failed to get asset file list." + e.getMessage());
        }

        GregorianCalendar calendar = new GregorianCalendar();
        int randYear = calendar.get(GregorianCalendar.YEAR);
        int randMonth = calendar.get(GregorianCalendar.MONTH) + 1;
        int randDay = calendar.get(GregorianCalendar.DAY_OF_MONTH);

        if (files != null) for (String filename : files) {
            InputStream in = null;
            OutputStream out = null;
            try {
                int randDayDiff = Utility.random(1, 10);

                if (randDay - randDayDiff < 0) {
                    randMonth --;
                    randDay = randDay - randDayDiff + 30;
                } else {
                    randDay = randDay - randDayDiff;
                }

                boolean randIsDay = true;//Utility.toBoolean(Utility.random(0, 1));
                boolean randIsGemmed = Utility.toBoolean(Utility.random(0, 1));

                TrendLook look = new TrendLook(randYear, randMonth, randDay, randIsDay, randIsGemmed);

                File outFile = FileManager.getLookFile(randYear, randMonth, randDay, randIsDay);
                DBHelper.saveNewLook(getApplicationContext(), look, false);

                in = assetManager.open(assetsDirectory + "/" + filename);
                out = new FileOutputStream(outFile);

                byte[] buffer = new byte[1024];
                int read;
                while ((read = in.read(buffer)) != -1) {
                    out.write(buffer, 0, read);
                }
            } catch (IOException e) {
                Utility.log("Failed to copy asset file: " + filename);
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        // NOOP
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        // NOOP
                    }
                }
            }
        }
    }
}
