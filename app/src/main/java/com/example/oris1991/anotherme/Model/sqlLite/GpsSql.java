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

        Cursor cursor = db.query(LOCATION_TABLE,null, null,null, null, null, null);

        Gps gp=null;
        if (cursor.moveToFirst()) {
            int typeIndex = cursor.getColumnIndex(LON);
            int sentIndex = cursor.getColumnIndex(LAT);
            int timeIndex = cursor.getColumnIndex(GPS_TIME);
                String lon = cursor.getString(typeIndex);
                String lat = cursor.getString(sentIndex);
                String time = cursor.getString(timeIndex);
               gp = new Gps(time,lon,lat);
        }

        return gp;
    }

    public static void editGps(SQLiteDatabase db, Gps gps) {


        ContentValues values = new ContentValues();
        values.put(LON, gps.getLongitude());
        values.put(LAT,gps.getLatitude());
        values.put(GPS_TIME, gps.getTime());
        db.update(LOCATION_TABLE,values,null,null);
  /*      String[] params = new String[1];
        params[0] = stId;
        ContentValues values = new ContentValues();
        values.put(STUDENTS_ID, st.getId());
        values.put(STUDENTS_FNAME, st.getFname());
        values.put(STUDENTS_LNAME, st.getLname());
        values.put(STUDENTS_PHONE, st.getPhone());
        values.put(STUDENTS_ADDRESS, st.getAddress());
        values.put(STUDENTS_IMAGE_NAME, st.getImageName());
        if (st.isChecked()) {
            values.put(STUDENTS_CHECKED, "YES");
        } else {
            values.put(STUDENTS_CHECKED, "NO");
        }
        values.put(STUDENTS_BIRTH_DATE, st.getBirthDate());
        values.put(STUDENTS_BIRTH_TIME, st.getBirthTime());
        db.update(STUDENTS_TABLE, values, STUDENTS_ID + " = ?",params);*/


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
