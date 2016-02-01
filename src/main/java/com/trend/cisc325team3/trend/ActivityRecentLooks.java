package com.trend.cisc325team3.trend;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.io.File;

public class ActivityRecentLooks extends ActivityTrendBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_looks);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TrendLook [] looks = TrendLookManager.loadedLooks;

        LinearLayout layout = (LinearLayout) findViewById(R.id.recent_looks_layout);
        setSupportActionBar(toolbar);

        if (looks != null) {

            int length = looks.length;
            int start = Math.max(0, length - 1);
            int end = Math.max(0, length - 6);

            for (int i = start; i >= end; i--) {
                TrendLook look = looks [i];

                Utility.log("start: " + start + ", end: " + end + ", i: " + i);

                File lookFile = FileManager.getLookFile(look.getYear(),
                        look.getMonth(), look.getDay(), look.isGemmed());

//                LinearLayout linearLayout =
//
//                ImageLoader.loadImage(imageView, lookFile);
//                layout.addView(imageView);

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return super.createOptions(menu, new boolean [] {true, true, true});

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.handleOptions(item);
    }


}
