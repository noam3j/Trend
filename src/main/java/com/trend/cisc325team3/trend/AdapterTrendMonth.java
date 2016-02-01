package com.trend.cisc325team3.trend;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by NJacobson on 15-10-24.
 */

public class AdapterTrendMonth extends BaseAdapter {

    private Context context;
    private TrendMonth trendMonth;
    private Typeface font;

    public AdapterTrendMonth(Context context, int year, int month, Typeface font, TrendLook [] monthLooks) {
        this.context = context;
        this.font = font;
        initTrendMonth(year, month, monthLooks);
    }

    private void initTrendMonth (int year, int month, TrendLook [] looks) {
        trendMonth = new TrendMonth(year, month, looks);
    }

    @Override
    public int getCount() {
        return trendMonth.daysInMonth();
    }

    @Override
    public Object getItem(int position) {
        return trendMonth.getTrendDay(position, true);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = inflater.inflate(R.layout.trend_day_view, null);

            // get the trendDay that corresponds to the trendDayView
            TrendDay trendDay = trendMonth.getTrendDay(position, true);

            // set the day and background color of the trendDayView
            ViewTrendDay viewTrendDay = (ViewTrendDay) gridView
                    .findViewById(R.id.trend_day_view);
            viewTrendDay.setDay(trendDay.getDayOfMonth());
            viewTrendDay.setTrendDay(trendDay);
            viewTrendDay.setTypeface(font);
            if (trendDay.hasLook()) {
                viewTrendDay.setOnClickListener(new OnDayClickListener(
                        trendMonth.getYear(), trendMonth.getMonth(), trendDay.getDayOfMonth()));
            }

        } else {
            gridView = convertView;
        }

        return gridView;
    }

    private class OnDayClickListener implements View.OnClickListener {

        int year;
        int month;
        int day;

        public OnDayClickListener(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        public void onClick(View v) {
            Intent intent= new Intent(context, ActivityLooks.class);
            intent.putExtra("day", day);
            intent.putExtra("month", month);
            intent.putExtra("year", year);
            context.startActivity(intent);
        }
    }

}
