package com.trend.cisc325team3.trend;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by NJacobson on 15-10-24.
 */
public class ViewTrendDay extends Button {

    private Paint borderPaint;
    private int day;
    private TrendDay trendDay;
    private int border;

    public ViewTrendDay(Context context, AttributeSet attrs) {
        super(context, attrs);

        initDrawingItems();
        initAttributes(context, attrs);
    }

    private void initAttributes(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ViewTrendDay,
                0, 0);

        try {
            int day = a.getInteger(R.styleable.ViewTrendDay_day, 0);

            setDay(day);
        } finally {
            a.recycle();
        }
    }

    public void setTrendDay (TrendDay trendDay) {
        this.trendDay = trendDay;
        adjustColors(trendDay);
    }

    private void initDrawingItems() {
        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setStrokeWidth(4);
    }

    public void adjustColors(TrendDay trendDay) {

        int background;
        int border;
        int text;

        Resources res = getResources();

        int [] colors = trendDay.getColorPaletteIDs(res, true);

        background = colors [0];
        text = colors [1];
        border = colors [2];

        setBackgroundColor(background);
        setBorder(border);
        setTextColor(text);
    }

    public void setDay (int day) {
        this.day = day;

        if (day == -1) {
            setText("");
        } else {
            setText(Html.fromHtml("<b>" + day + "</b>"));
        }
    }

    public void setBorder (int border) {
        this.border = border;
        borderPaint.setColor(border);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (border != 0) {
            int width = getWidth();
            int height = getHeight();

            canvas.drawLine(0, 0, width, 0, borderPaint);
            canvas.drawLine(width, 0, width, height, borderPaint);
            canvas.drawLine(0, height, width, height, borderPaint);
            canvas.drawLine(0, 0, 0, height, borderPaint);
        }
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();

        setMeasuredDimension(width, width);
    }
}
