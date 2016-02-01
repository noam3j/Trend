package com.trend.cisc325team3.trend;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.caverock.androidsvg.SVG;

/**
 * Created by NJacobson on 15-11-23.
 */
public class ImageButtonSVG extends ImageButton {

    int state = 0;
    SVG svgIdle;
    SVG svgPressed;
    Bitmap bitmapIdle;
    Bitmap bitmapPressed;
    int savedWidth = 0;
    int savedHeight = 0;

//    public ImageButtonSVG (Context context, String idleName, String pressedName) {
//        super(context);
//        setBackgroundColor(Color.TRANSPARENT);
//        svgIdle = SVGHelper.getInstance(context, idleName);
//        svgPressed = SVGHelper.getInstance(context, pressedName);
//    }

    public ImageButtonSVG (Context context, AttributeSet attrs) {
        super(context, attrs);

        initAttributes(context, attrs);
    }

    private void initAttributes(Context context, AttributeSet attrs) {

        setBackgroundColor(Color.TRANSPARENT);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ImageButtonSVG,
                0, 0);

        try {
            String fileIdle = a.getString(R.styleable.ImageButtonSVG_file_idle);
            String filePressed = a.getString(R.styleable.ImageButtonSVG_file_pressed);

            //Utility.log(filePressed);
            //Utility.log("hey dog");

            svgIdle = SVGHelper.getInstance(context, fileIdle);
            svgPressed = SVGHelper.getInstance(context, filePressed);
        } finally {
            a.recycle();
        }
    }

    public int changeState() {
        if (state == 0) {
            state = 1;
        } else if (state == 1) {
            state = 0;
        }
        updateImage();
        Utility.log("updated");

        return state;
    }

    @Override
    protected void onLayout (boolean changed, int l, int t, int r, int b) {

        int width = getWidth();
        int height = getHeight();

        //Utility.log("Hey Noam. Here. Important. Measuring");
        //Utility.log("measuring view, width: " + getWidth());

        //Utility.log("measuring view, width: " + getWidth());

        //if we resized, then we must reset the bitmap
        if (width != 0 && height != 0 && width != savedWidth & height != savedHeight) {
            savedWidth = width;
            savedHeight = height;

            //Utility.log("Hey Noam. Here. Important. Measuring. WE maaade it + ");

            if (svgIdle != null){
                bitmapIdle = SVGHelper.getBitmapFromSVG(svgIdle, width, height);
            }

            if (svgPressed != null) {
                bitmapPressed = SVGHelper.getBitmapFromSVG(svgPressed, width, height);
            }
        }

        updateImage();
    }

    public boolean updateImage() {
        if (state == 0 && bitmapIdle != null) {
            setImageBitmap(bitmapIdle);
            return true;
        } else if (state == 1 && bitmapPressed != null) {
            setImageBitmap(bitmapPressed);
            return true;
        }

        return false;
    }
}
