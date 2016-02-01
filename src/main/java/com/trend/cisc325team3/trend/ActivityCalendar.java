package com.trend.cisc325team3.trend;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Icon;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.caverock.androidsvg.SVG;

public class ActivityCalendar extends ActivityTrendBase {

    AdapterTrendCalendar adapterTrendCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        adapterTrendCalendar = new AdapterTrendCalendar(
                getSupportFragmentManager());

        ViewPager viewPager = (ViewPager) findViewById(R.id.calendar_pager);
        viewPager.setAdapter(adapterTrendCalendar);
        viewPager.setCurrentItem(adapterTrendCalendar.getCount() - 1);

        //for the parent class
        this.baseViewId = R.id.calendar_layout;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return super.createOptions(menu, new boolean [] {false, true, true});

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.handleOptions(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Utility.log("there was an activity result.\n request code: " + requestCode + "\nthis activity: ActivityCalendar");
        
    }

    @Override
    public void onRestart() {
        super.onRestart();  // Always call the superclass method first

        Utility.log("resuming calendar");
        adapterTrendCalendar.notifyDataSetChanged();
    }
}
