package com.trend.cisc325team3.trend;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by NJacobson on 15-11-01.
 */
public class ImageLoader {

    public static void loadImage(final ImageView imageView, final File imageFile) {

        new Thread(new Runnable() {

            public void run() {

                final Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());

                imageView.post(new Runnable() {

                    public void run() {
                        imageView.setImageBitmap(bitmap);
                    }

                });
            }
        }).start();
    }
}
