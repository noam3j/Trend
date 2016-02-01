package com.trend.cisc325team3.trend;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.caverock.androidsvg.SVG;

import java.io.File;

/**
 * Created by NJacobson on 15-11-01.
 */
public class FragmentTrendLook extends Fragment {

    private TrendLook look;

    public static FragmentTrendLook newInstance(TrendLook look) {
        FragmentTrendLook fragmentTrendLook = new FragmentTrendLook();

        fragmentTrendLook.look = look;

        return fragmentTrendLook;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_trend_look, container, false);

        initLookView(rootView);

        return rootView;
    }

    private ImageView initLookView (View rootView) {

        File lookFile = FileManager.getLookFile(
                look.getYear(),
                look.getMonth(),
                look.getDay(),
                look.isDay()
        );

        ImageViewSVG imageView = (ImageViewSVG) rootView.findViewById(R.id.lookView);

        if(lookFile.exists()) {
            imageView.removeImage();
            ImageLoader.loadImage(imageView, lookFile);
        } else {
            imageView.updateImage(getContext(), "svg/doodle_black.svg");
        }

        //set text of date of picture
        Typeface font = Utility.getFont(Utility.Font.BOLD, getContext());
        final TextView calendarDateTextView = (TextView) rootView.findViewById(R.id.calendar_date);
        calendarDateTextView.setText(Utility.getShortMonthFromInt(look.getMonth()) + "\n" + look.getDay());
        calendarDateTextView.setTypeface(font);

        //hide or show more options
        final LinearLayout optionsLayout = (LinearLayout) rootView.findViewById(R.id.more_options);
        optionsLayout.setVisibility(View.INVISIBLE);
        ImageButtonSVG moreOptionsButton = (ImageButtonSVG) rootView.findViewById(R.id.more_options_button);
        moreOptionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButtonSVG button = (ImageButtonSVG) v;
                int newState = button.changeState();

                if (newState == 0) {
                    calendarDateTextView.setVisibility(View.VISIBLE);
                    optionsLayout.setVisibility(View.INVISIBLE);
                    Utility.log("options count: " + optionsLayout.getChildCount());
                } else {
                    calendarDateTextView.setVisibility(View.GONE);
                    optionsLayout.setVisibility(View.VISIBLE);
                    Utility.log("options count: " + optionsLayout.getChildCount());
                }
            }
        });

        //gem button
        ImageButtonSVG gemButton = (ImageButtonSVG) rootView.findViewById(R.id.gem_it_button);
        if (look.isGemmed()) {
            gemButton.changeState();
        }
        gemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButtonSVG button = (ImageButtonSVG) v;
                int newState = button.changeState();

                if (newState == 0) {
                    look.setGemmed(false);
                    DBHelper.updateGemmed(getContext(), look, false);
                } else {
                    look.setGemmed(true);
                    DBHelper.updateGemmed(getContext(), look, false);
                }

                DeleteMeDatabaseTestFiller.logLooks(TrendLookManager.loadedLooks);
            }
        });

        //delete button
        ImageButtonSVG deleteButton = (ImageButtonSVG) rootView.findViewById(R.id.trash_it_button);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //would like to do this asynchronously, and not reload looks, but shortcut for project
                DBHelper.deleteLook(getContext(), look, true);
                TrendLook [] trendLooks = DBHelper.loadLooks(getContext());
                TrendLookManager.setLooks(trendLooks);

                FileManager.deleteLookFile(look.getYear(), look.getMonth(), look.getDay(),
                        look.isDay());

                //DeleteMeDatabaseTestFiller.logLooks(TrendLookManager.loadedLooks);

                ((ActivityLooks)getActivity()).viewPagerItemDeleted();
            }
        });

        //share button
        ImageButtonSVG shareButton = (ImageButtonSVG) rootView.findViewById(R.id.share_it_button);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = FileManager.getLookUri(look.getYear(), look.getMonth(),
                        look.getDay(), look.isDay());
                String type = "image/*";

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType(type);
                shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                startActivity(Intent.createChooser(shareIntent, "Share to"));
            }
        });

        ImageButtonSVG emailButton = (ImageButtonSVG) rootView.findViewById(R.id.mail_it_button);

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = FileManager.getLookUri(look.getYear(), look.getMonth(),
                        look.getDay(), look.isDay());

                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.setType("application/image");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{});
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"I'm so #Trendy");
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Check out my newest Look!");
                emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            }
        });

        ImageButtonSVG copyButton = (ImageButtonSVG) rootView.findViewById(R.id.copy_it_button);

        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Oops. That feature isn't supported yet", Toast.LENGTH_SHORT).show();
            }
        });

        ImageButtonSVG downloadButton = (ImageButtonSVG) rootView.findViewById(R.id.download_it_button);

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Oops. That feature isn't supported yet", Toast.LENGTH_SHORT).show();
            }
        });

        return imageView;
    }
}
