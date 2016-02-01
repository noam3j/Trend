package com.trend.cisc325team3.trend;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.caverock.androidsvg.SVG;
import com.trend.cisc325team3.trend.Utility;

/**
 * Created by NJacobson on 15-11-11.
 */
public class ImageViewSVG extends ImageView {

    private SVG svg;
    private int savedWidth = 0;
    private int savedHeight = 0;

    public ImageViewSVG(Context context, String fileName) {
        super(context);
        svg = SVGHelper.getInstance(context, fileName);
    }

    public ImageViewSVG(Context context, AttributeSet attrs) {
        super(context, attrs);

        initAttributes(context, attrs);
    }

    private void initAttributes(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ImageViewSVG,
                0, 0);

        try {
            String fileName = a.getString(R.styleable.ImageViewSVG_file);

            Utility.log("filename " + fileName);

            svg = SVGHelper.getInstance(context, fileName);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onLayout (boolean changed, int l, int t, int r, int b) {

        if (svg != null) {

            int width = getWidth();
            int height = getHeight();

            //Utility.log("measuring view, width: " + getWidth());

            //if we resized, then we must reset the bitmap
            if (width != 0 && height != 0 && width != savedWidth & height != savedHeight) {
                savedWidth = width;
                savedHeight = height;

                Bitmap bitmap = SVGHelper.getBitmapFromSVG(svg, width, height);
                setImageBitmap(bitmap);
            }
        }

    }

    public void updateImage(Context context, String fileName) {
        svg = SVGHelper.getInstance(context, fileName);
    }

    public void removeImage() {
        svg = null;
    }
}
