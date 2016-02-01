package com.trend.cisc325team3.trend;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by NJacobson on 15-10-24.
 */

public class AdapterTrendDaysOfWeek extends BaseAdapter {

    private Context context;
    private int [] daysOfWeek = new int [] {1, 2, 3, 4, 5, 6, 7};
    private Typeface font;

    public AdapterTrendDaysOfWeek(Context context, Typeface font) {

        this.context = context;
        this.font = font;
    }

    @Override
    public int getCount() {
        return daysOfWeek.length;
    }

    @Override
    public Object getItem(int position) {
        return daysOfWeek [position];
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

            gridView = inflater.inflate(R.layout.day_of_week_view, null);

            ViewTrendDayOfWeek viewTrendDayOfWeek = (ViewTrendDayOfWeek) gridView
                    .findViewById(R.id.trend_day_of_week);
            viewTrendDayOfWeek.setDay(position + 1);
            viewTrendDayOfWeek.setTypeface(font);

        } else {
            gridView = convertView;
        }

        return gridView;
    }
}
