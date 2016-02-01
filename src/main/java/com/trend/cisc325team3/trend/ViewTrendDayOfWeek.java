package com.trend.cisc325team3.trend;

/**
 * Created by NJacobson on 15-10-26.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by NJacobson on 15-10-24.
 */
public class ViewTrendDayOfWeek extends TextView {

    public ViewTrendDayOfWeek(Context context, AttributeSet attrs) {
        super(context, attrs);

        initAttributes(context, attrs);
    }

    private void initAttributes(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ViewTrendDay,
                0, 0);

        try {
            int day = a.getInteger(R.styleable.ViewTrendDayOfWeek_dayName, 0);
            setDay(day);
        } finally {
            a.recycle();
        }
    }

    public void setDay (int day) {
        String dayString = Utility.getDayOfWeekFromInt(day);
        setText(Html.fromHtml("<b>" + dayString + "</b>"));
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();

        setMeasuredDimension(width, width);
    }
}

