package com.trend.cisc325team3.trend;

/**
 * Created by NJacobson on 15-10-26.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.TextView;

public class ViewTrendMonthName extends TextView {

    public ViewTrendMonthName(Context context, AttributeSet attrs) {
        super(context, attrs);

        initAttributes(context, attrs);
    }

    private void initAttributes(Context context, AttributeSet attrs) {

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ViewTrendDay,
                0, 0);

        try {
            int month = a.getInteger(R.styleable.ViewTrendMonthName_month, 0);
            int year = a.getInteger(R.styleable.ViewTrendMonthName_year, 0);
            setMonthAndYear(year, month);

        } catch (Exception e) {

        } finally {
            a.recycle();
        }
    }

    public void setMonthAndYear (int year, int month) {

        String monthString = Utility.getMonthFromInt(month);

        setText(Html.fromHtml("<b>" + year + "<br>" + monthString + "</b>"));
    }
}

