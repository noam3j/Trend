package com.trend.cisc325team3.trend;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.caverock.androidsvg.SVG;

import java.io.File;

public class ActivityLooks extends ActivityTrendBase {

    private ViewPager viewPager;
    private AdapterTrendLooks adapterTrendLooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looks);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Bundle bundle = null;
        int year = 0;
        int month = 0;
        int day = 0;

        if (intent != null) {
            bundle = intent.getExtras();
        }
        if (bundle != null) {
            year = bundle.getInt("year");
            month = bundle.getInt("month");
            day = bundle.getInt("day");
        }

        adapterTrendLooks = new AdapterTrendLooks(
                getSupportFragmentManager(), year, month, day);

        viewPager = (ViewPager) findViewById(R.id.look_pager);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(adapterTrendLooks);
        viewPager.setCurrentItem(adapterTrendLooks.getCurrentItem());
        Utility.log("off screen limit" + viewPager.getOffscreenPageLimit());

        //for the parent class
        this.baseViewId = R.id.looks_layout;
    }

    public void viewPagerItemDeleted() {
        int currentItem = viewPager.getCurrentItem();
        if (currentItem == 0 && TrendLookManager.loadedLooks.length == 0) {
            Utility.log("View Calendar");
            Intent intent = new Intent(getApplicationContext(), ActivityCalendar.class);
            startActivity(intent);
            finish();
        } else {
            currentItem = Math.max(currentItem - 1, 0);
            adapterTrendLooks = new AdapterTrendLooks(
                    getSupportFragmentManager(), currentItem);
            viewPager.setAdapter(adapterTrendLooks);
            viewPager.setCurrentItem(currentItem);
        }

        Toast.makeText(getApplicationContext(), "Your Look has been deleted.", Toast.LENGTH_LONG).show();
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
