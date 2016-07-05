package com.example.oris1991.anotherme.Model.sqlLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.oris1991.anotherme.Model.Entities.Gps;

/**
 * Created by oris1991 on 29/05/2016.
 */
public class GpsSql {

    private static final String LOCATION_TABLE = "gps_table";
    private static final String LON = "longitude";
    private static final String LAT = "latitude";
    private static final String GPS_TIME = "time";


    public static Gps getGps(SQLiteDatabase db) {

        Cursor cursor = db.query(LOCATION_TABLE, null, null, null, null, null, null);

        Gps gp = null;
        if (cursor.moveToFirst()) {
            int typeIndex = cursor.getColumnIndex(LON);
            int sentIndex = cursor.getColumnIndex(LAT);
            int timeIndex = cursor.getColumnIndex(GPS_TIME);
            String lon = cursor.getString(typeIndex);
            String lat = cursor.getString(sentIndex);
            String time = cursor.getString(timeIndex);
            gp = new Gps(time, lon, lat);
        }

        return gp;
    }

    public static void editGps(SQLiteDatabase db, Gps gps) {


        ContentValues values = new ContentValues();
        values.put(LON, gps.getLongitude());
        values.put(LAT, gps.getLatitude());
        values.put(GPS_TIME, gps.getTime());
        db.update(LOCATION_TABLE, values, null, null);

    }


    public static void create(SQLiteDatabase db) {
        db.execSQL("create table " +
                LOCATION_TABLE + " (" +
                LON + " TEXT," +
                LAT + " TEXT," +
                GPS_TIME + " TEXT);");
    }

    public static void drop(SQLiteDatabase db) {
        db.execSQL("drop table " + LOCATION_TABLE);
    }


}
