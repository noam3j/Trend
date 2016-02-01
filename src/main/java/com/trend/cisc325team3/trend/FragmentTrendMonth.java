package com.trend.cisc325team3.trend;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

/**
 * Created by NJacobson on 15-10-26.
 */
public class FragmentTrendMonth extends Fragment {

    private int year;
    private int month;
    private Typeface fontRegular;
    private Typeface fontBold;

    public static FragmentTrendMonth newInstance(int year, int month) {
        FragmentTrendMonth fragmentTrendMonth = new FragmentTrendMonth();

        Bundle args = new Bundle();
        args.putInt("year", year);
        args.putInt("month", month);
        fragmentTrendMonth.setArguments(args);

        return fragmentTrendMonth;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();

        if (bundle != null) {
            year = bundle.getInt("year");
            month = bundle.getInt("month");
        }

        fontRegular = Utility.getFont(Utility.Font.REGULAR, getContext());
        fontBold = Utility.getFont(Utility.Font.BOLD, getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.fragment_trend_month, container, false);

        initMonthViews (rootView);

        return rootView;
    }

    private void initMonthViews (View rootView) {

        Utility.log("Fragment Trend Month is initialized");

        Context context = getContext();

        ViewTrendMonthName viewTrendMonthName = (ViewTrendMonthName) rootView.findViewById(R.id.trend_month_name_view);
        viewTrendMonthName.setMonthAndYear(year, month);
        viewTrendMonthName.setTypeface(getFont(Utility.Font.REGULAR));

        GridView daysOfWeekGrid = (GridView) rootView.findViewById(R.id.trend_days_of_week_grid);
        daysOfWeekGrid.setAdapter(new AdapterTrendDaysOfWeek(context, getFont(Utility.Font.REGULAR)));

        setDayGrid(rootView, context);
    }

    private void setDayGrid(View rootView, Context context) {
        TrendLook [] looks = TrendLookManager.getLooksForMonth(year, month);

        GridView calendarGrid = (GridView) rootView.findViewById(R.id.trend_month_grid);
        calendarGrid.setAdapter(new AdapterTrendMonth(
                context, year, month, getFont(Utility.Font.REGULAR), looks
        ));
    }

    private Typeface getFont(Utility.Font fontType) {
        if (fontType == Utility.Font.REGULAR) {
            return fontRegular;
        } else if (fontType == Utility.Font.BOLD) {
            return fontBold;
        } else {
            return null;
        }
    }
}
