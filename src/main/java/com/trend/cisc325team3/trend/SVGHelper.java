package com.trend.cisc325team3.trend;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
import android.widget.ImageView;

import com.caverock.androidsvg.SVG;

/**
 * Created by NJacobson on 15-11-11.
 */
public class SVGHelper {

    public static Bitmap getBitmapFromSVG(SVG svg, int width, int height) {
        // Read an SVG from the assets folder
        try {

            Bitmap bitmap;

            bitmap = Bitmap.createBitmap(
                    width,
                    height,
                    Bitmap.Config.ARGB_8888);
            Canvas bmcanvas = new Canvas(bitmap);

            // Render our document onto our canvas
            svg.renderToCanvas(bmcanvas);

            return bitmap;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static SVG getInstance(Context context, String assetFileName) {
        try {
            return SVG.getFromAsset(context.getAssets(), assetFileName);
        } catch (Exception e) {
            return null;
        }
    }
}
