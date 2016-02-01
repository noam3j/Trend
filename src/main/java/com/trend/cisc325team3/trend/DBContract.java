package com.trend.cisc325team3.trend;

import android.provider.BaseColumns;

/**
 * Created by NJacobson on 15-10-31.
 */
public final class DBContract {

    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INT";
    private static final String COMMA_SEP = ",";
    private static final String ASC = " ASC";
    private static final String DESC = " DESC";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + LooksTable.TABLE_NAME + " (" +
                    LooksTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                    LooksTable.COLUMN_NAME_LOOK_ID + TEXT_TYPE + COMMA_SEP +
                    LooksTable.COLUMN_NAME_YEAR + INT_TYPE + COMMA_SEP +
                    LooksTable.COLUMN_NAME_MONTH + INT_TYPE + COMMA_SEP +
                    LooksTable.COLUMN_NAME_DAY + INT_TYPE + COMMA_SEP +
                    LooksTable.COLUMN_NAME_ISDAYLOOK + INT_TYPE + COMMA_SEP +
                    LooksTable.COLUMN_NAME_ISGEMMED + INT_TYPE + COMMA_SEP +
                    "UNIQUE (" + LooksTable.COLUMN_NAME_LOOK_ID + ") ON CONFLICT REPLACE" +
            " )";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + LooksTable.TABLE_NAME;

    public static final String SQL_QUERY_ALL_SORT =
            LooksTable.COLUMN_NAME_YEAR + ASC + COMMA_SEP +
            LooksTable.COLUMN_NAME_MONTH + ASC + COMMA_SEP +
            LooksTable.COLUMN_NAME_DAY + ASC + COMMA_SEP +
            LooksTable.COLUMN_NAME_ISDAYLOOK + ASC;

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public DBContract() {}

    /* Inner class that defines the table contents */
    public static abstract class LooksTable implements BaseColumns {
        public static final String TABLE_NAME = "looks";
        public static final String COLUMN_NAME_LOOK_ID = "look_id";
        public static final String COLUMN_NAME_YEAR = "year";
        public static final String COLUMN_NAME_MONTH = "month";
        public static final String COLUMN_NAME_DAY = "day";
        public static final String COLUMN_NAME_ISDAYLOOK = "is_day_look";
        public static final String COLUMN_NAME_ISGEMMED = "is_gemmed";
        public static final String COLUMN_NAME_NULLABLE = null;
    }
}