package com.trend.cisc325team3.trend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.LinkedList;

/**
 * Created by NJacobson on 15-10-30.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TrendLooks.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Utility.log("Creating looks database");
        db.execSQL(DBContract.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Utility.log("Deleting looks database");
        db.execSQL(DBContract.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public static TrendLook [] loadLooks (Context context) {
        Utility.log("Loading looks from database");
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String tableName = DBContract.LooksTable.TABLE_NAME;

        String[] columns = {
                DBContract.LooksTable.COLUMN_NAME_YEAR,
                DBContract.LooksTable.COLUMN_NAME_MONTH,
                DBContract.LooksTable.COLUMN_NAME_DAY,
                DBContract.LooksTable.COLUMN_NAME_ISDAYLOOK,
                DBContract.LooksTable.COLUMN_NAME_ISGEMMED,
        };

        String selection = null;//null for all rows
        String [] selectionArgs = null;//null for all rows
        String groupBy = null;//null for no grouping
        String having = null;//null for no grouping

        String sortOrder = DBContract.SQL_QUERY_ALL_SORT;

        Cursor cursor = db.query(
                tableName,  // The table to query
                columns,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                groupBy,                                     // don't group the rows
                having,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        LinkedList <TrendLook> looks = new LinkedList<>();

        if (cursor.moveToFirst()) {
            do  {

                int year = cursor.getInt(cursor.getColumnIndex(
                        DBContract.LooksTable.COLUMN_NAME_YEAR));
                int month = cursor.getInt(cursor.getColumnIndex(
                        DBContract.LooksTable.COLUMN_NAME_MONTH));
                int day = cursor.getInt(cursor.getColumnIndex(
                        DBContract.LooksTable.COLUMN_NAME_DAY));
                boolean isDay = Utility.toBoolean(cursor.getInt(cursor.getColumnIndex(
                        DBContract.LooksTable.COLUMN_NAME_ISDAYLOOK)));
                boolean isGemmed = Utility.toBoolean(cursor.getInt(cursor.getColumnIndex(
                        DBContract.LooksTable.COLUMN_NAME_ISGEMMED)));

                looks.add(new TrendLook(year, month, day, isDay, isGemmed));

            } while (cursor.moveToNext());
        }

        db.close();

        return looks.toArray(new TrendLook[0]);
    }

    public static void saveNewLook (final Context context, final TrendLook newLook, boolean immediately) {

        if (immediately) {
            doInsert(context, newLook);
        } else {
            new Thread(new Runnable() {

                public void run() {

                    doInsert(context, newLook);
                }
            }).start();
        }
    }

    private static void doInsert(Context context, TrendLook newLook) {
        Utility.log("Saving new look to database");
        DBHelper dbHelper = new DBHelper(context);
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int year = newLook.getYear();
        int month = newLook.getMonth();
        int day = newLook.getDay();
        int isDay = Utility.toInt(newLook.isDay());
        int isGemmed = Utility.toInt(newLook.isGemmed());
        String lookID = "" + year + month + day + isDay;

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DBContract.LooksTable.COLUMN_NAME_LOOK_ID, lookID);
        values.put(DBContract.LooksTable.COLUMN_NAME_YEAR, year);
        values.put(DBContract.LooksTable.COLUMN_NAME_MONTH, month);
        values.put(DBContract.LooksTable.COLUMN_NAME_DAY, day);
        values.put(DBContract.LooksTable.COLUMN_NAME_ISDAYLOOK, isDay);
        values.put(DBContract.LooksTable.COLUMN_NAME_ISGEMMED, isGemmed);

        // Insert the new row, replacing old images if there are any
        db.insertWithOnConflict(
                DBContract.LooksTable.TABLE_NAME,
                DBContract.LooksTable.COLUMN_NAME_NULLABLE,
                values,
                SQLiteDatabase.CONFLICT_REPLACE
        );

        db.close();

    }

    public static void deleteLook (final Context context, final TrendLook look, boolean immediately) {

        if (immediately) {
            doDelete(context, look);
        } else {
            new Thread(new Runnable() {

                public void run() {

                    doDelete(context, look);
                }

            }).start();
        }
    }

    private static void doDelete(Context context, TrendLook look) {
        Utility.log("Deleting look from database");
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int year = look.getYear();
        int month = look.getMonth();
        int day = look.getDay();
        int isDay = Utility.toInt(look.isDay());
        int isGemmed = Utility.toInt(look.isGemmed());

        String tableName = DBContract.LooksTable.TABLE_NAME;
        String whereClause =
                DBContract.LooksTable.COLUMN_NAME_YEAR + " = ? AND " +
                        DBContract.LooksTable.COLUMN_NAME_MONTH + " = ? AND " +
                        DBContract.LooksTable.COLUMN_NAME_DAY + " = ? AND " +
                        DBContract.LooksTable.COLUMN_NAME_ISDAYLOOK + " = ? AND " +
                        DBContract.LooksTable.COLUMN_NAME_ISGEMMED + " = ?";

        String [] whereArgs = new String [] {
                year + "",
                month + "",
                day + "",
                isDay + "",
                isGemmed + "",
        };

        db.delete(tableName, whereClause, whereArgs);

        db.close();
    }

    public static void updateGemmed (final Context context, final TrendLook look, boolean immediately) {
        if (immediately) {
            doUpdateGemmed(context, look);
        } else {
            new Thread(new Runnable() {

                public void run() {

                    doUpdateGemmed(context, look);
                }

            }).start();
        }
    }

    public static void doUpdateGemmed (Context context, final TrendLook look) {
        Utility.log("Toggling the gemmed attribute of a look in database");
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int year = look.getYear();
        int month = look.getMonth();
        int day = look.getDay();
        int isDay = Utility.toInt(look.isDay());
        int isGemmed = Utility.toInt(look.isGemmed());

        String tableName = DBContract.LooksTable.TABLE_NAME;

        ContentValues values = new ContentValues();
        values.put(DBContract.LooksTable.COLUMN_NAME_ISGEMMED, isGemmed);

        Utility.log("The boolean value: " + Utility.toInt(!look.isGemmed()));

        String whereClause =
                DBContract.LooksTable.COLUMN_NAME_YEAR + " = ? AND " +
                        DBContract.LooksTable.COLUMN_NAME_MONTH + " = ? AND " +
                        DBContract.LooksTable.COLUMN_NAME_DAY + " = ? AND " +
                        DBContract.LooksTable.COLUMN_NAME_ISDAYLOOK + " = ?";

        String [] whereArgs = new String [] {
                year + "",
                month + "",
                day + "",
                isDay + "",
        };

        db.update(tableName, values, whereClause, whereArgs);

        db.close();

    }

    public static void clearTable (Context context) {
        Utility.log("Deleting all looks from database");
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String tableName = DBContract.LooksTable.TABLE_NAME;
        String whereClause = null;//null for all rows
        String [] whereArgs = null;//null for all rows

        db.delete(tableName, whereClause, whereArgs);

        db.close();
    }

    public static void recreateTable(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.onUpgrade(db, 1, 1);
    }
}
