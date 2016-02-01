package com.trend.cisc325team3.trend;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.caverock.androidsvg.SVG;

/**
 * Created by NJacobson on 15-11-26.
 */
public class ActivityTrendBase extends AppCompatActivity {

    protected int baseViewId;
    private FragmentInfo infoFragment;

    public boolean createOptions(Menu menu, boolean [] includeItems) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_trend_calendar, menu);

        //the following is code to replace the icons of the action bar with svg images

        //get the height of the action bar
        final TypedArray styledAttributes = getApplicationContext().getTheme().obtainStyledAttributes(
                new int[] { android.R.attr.actionBarSize });
        int actionBarSize = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        Utility.log("" + actionBarSize);

        Context context = getApplicationContext();
        Resources resources = getResources();

        MenuItem calendarIcon = menu.findItem(R.id.calendar);
        MenuItem newPicIcon = menu.findItem(R.id.new_pic);
        MenuItem aboutIcon = menu.findItem(R.id.info);

        String calendarFileName = resources.getString(R.string.calendar_icon_file_name);
        String addFileName = resources.getString(R.string.add_icon_file_name);
        String aboutFileName = resources.getString(R.string.info_icon_file_name);

        setUpMenuItem(context, resources, calendarIcon,
                calendarFileName, actionBarSize, includeItems [0]);
        setUpMenuItem(context, resources, newPicIcon,
                addFileName, actionBarSize, includeItems [1]);
        setUpMenuItem(context, resources, aboutIcon,
                aboutFileName, actionBarSize, includeItems[2]);

        return true;
    }

    private void setUpMenuItem(Context context, Resources resources, MenuItem menuItem,
                               String svgFileName, int actionBarSize, boolean show) {

        SVG svg = SVGHelper.getInstance(context, svgFileName);

        menuItem.setIcon(new BitmapDrawable(resources,
                SVGHelper.getBitmapFromSVG(svg, actionBarSize, actionBarSize)));

        menuItem.setVisible(show);
    }

    public boolean handleOptions(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Intent intent;

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.new_pic:
                Utility.log("Take new pic");
                intent = new Intent(getApplicationContext(), ActivityCamera.class);
                startActivity(intent);
                finish();
                //startActivityForResult(intent, CAMERA_ACTIVITY_REQUEST_CODE);
                return true;
            case R.id.calendar:
                Utility.log("View Calendar");
                intent = new Intent(getApplicationContext(), ActivityCalendar.class);
                startActivity(intent);
                finish();
                //startActivityForResult(intent, CALENDAR_ACTIVITY_REQUEST_CODE);
                return true;
            case R.id.info:
                Utility.log("View Info");
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

                if (infoFragment == null) {
                    infoFragment = FragmentInfo.newInstance();
                }

                if (!infoFragment.isAdded()) {
                    fragmentTransaction.add(baseViewId, infoFragment);
                    fragmentTransaction.attach(infoFragment);//
                    Utility.log("1");
                } else if (!infoFragment.isDetached()) {
                    fragmentTransaction.detach(infoFragment);//
                    Utility.log("2");
                } else {

                    Utility.log("3");
                }
                fragmentTransaction.commit();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
