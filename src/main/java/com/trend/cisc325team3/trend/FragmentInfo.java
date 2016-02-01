package com.trend.cisc325team3.trend;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TextView;

import com.caverock.androidsvg.SVG;

public class FragmentInfo extends Fragment {

    public static FragmentInfo newInstance() {
        FragmentInfo fragment = new FragmentInfo();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_info, container, false);

        initView(rootView);

        return rootView;
    }

    private void initView (View rootView) {

        TextView textView = (TextView)rootView.findViewById(R.id.info_help_p1);
        int size = (int)textView.getTextSize();
        Utility.log("the size of text: " + size);
        Context context = getActivity();

        SVG gemSvg = SVGHelper.getInstance(context, "svg/gem_white.svg");
        SVG addSvg = SVGHelper.getInstance(context, "svg/add_white.svg");
        SVG calSvg = SVGHelper.getInstance(context, "svg/cal_white.svg");
        SVG isLookSvg = SVGHelper.getInstance(context, "svg/icon_shaded.svg");
        SVG noLookSvg = SVGHelper.getInstance(context, "svg/icon_white.svg");
        SVG gemmedLookSvg = SVGHelper.getInstance(context, "svg/icon_gemmed.svg");

        Bitmap gemBitmap = SVGHelper.getBitmapFromSVG(gemSvg, size, size);
        Bitmap plusBitmap = SVGHelper.getBitmapFromSVG(addSvg, size, size);
        Bitmap calBitmap = SVGHelper.getBitmapFromSVG(calSvg, size, size);
        Bitmap isLookBitmap = SVGHelper.getBitmapFromSVG(isLookSvg, size, size);
        Bitmap noLookBitmap = SVGHelper.getBitmapFromSVG(noLookSvg, size, size);
        Bitmap gemmedLookBitmap = SVGHelper.getBitmapFromSVG(gemmedLookSvg, size, size);

        SpannableStringBuilder ssb = new SpannableStringBuilder(textView.getText());
        ssb.setSpan( new ImageSpan(context, gemBitmap),
                145, 146, Spannable.SPAN_INCLUSIVE_INCLUSIVE );
        ssb.setSpan(new ImageSpan(context, plusBitmap),
                165, 166, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        ssb.setSpan(new ImageSpan(context, calBitmap),
                237, 238, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        ssb.setSpan(new ImageSpan(context, isLookBitmap),
                310, 311, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        ssb.setSpan(new ImageSpan(context, noLookBitmap),
                359, 360, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        ssb.setSpan(new ImageSpan(context, gemmedLookBitmap),
                401, 402, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textView.setText(ssb, TextView.BufferType.SPANNABLE);

        //facebook link
        ImageButton facebookButton = (ImageButton) rootView.findViewById(R.id.info_facebook_button);
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
                startActivity(intent);
            }
        });
    }



}
