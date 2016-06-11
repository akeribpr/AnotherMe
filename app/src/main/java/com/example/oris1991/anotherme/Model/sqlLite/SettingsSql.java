package com.example.oris1991.anotherme.Model.sqlLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.oris1991.anotherme.Model.Entities.Settings;

/**
 * Created by Itzik on 29/05/2016.
 */
public class SettingsSql {


    private static final String  SETTINGS_TABLE = "Settings_table";
    private static final String PERSON_ID = "personId";
    private static final String FULL_NAME = "fullName";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String AGE = "age";
    private static final String PASSWORD = "password";
    private static final String DATE_TIME_REGISTER = "DateTimeRegister";
    private static final String MAIL = "mail";
    private static final String POPUP = "PopUps";
    private static final String SMS = "Sms";
    private static final String SOLUTION = "ServerSolution";
    private static final String GPS = "gps";


    public static void add(SQLiteDatabase db, Settings sp) {
        ContentValues values = new ContentValues();
        values.put(PERSON_ID, sp.getPersonId());
        values.put(FULL_NAME, sp.getFullName());
        values.put(PHONE_NUMBER, sp.getPhoneNumber());
        values.put(AGE, sp.getAge());
        values.put(PASSWORD, sp.getPassword());
        values.put(DATE_TIME_REGISTER, sp.getDateTimeRegister().toString());
        values.put(MAIL, sp.getMail());
        values.put(POPUP, sp.getPopUps());
        values.put(SMS, sp.getSms());
        values.put(SOLUTION, sp.getSolution());
        values.put(GPS, sp.getGps());
        db.insert(SETTINGS_TABLE, AGE,values);
    }



    public static Settings getSettings(SQLiteDatabase db) {

        Settings settings = null;
        Cursor cursor = db.query(SETTINGS_TABLE,null,null,null, null, null, null);

            if (cursor.moveToFirst()) {
                int personId = cursor.getColumnIndex(PERSON_ID);
                int fullName = cursor.getColumnIndex(FULL_NAME);
                int phoneNumber = cursor.getColumnIndex(PHONE_NUMBER);
                int age = cursor.getColumnIndex(AGE);
                int password = cursor.getColumnIndex(PASSWORD);
                int DateTimeRegister = cursor.getColumnIndex(DATE_TIME_REGISTER);
                int mail = cursor.getColumnIndex(MAIL);
                int PopUps = cursor.getColumnIndex(POPUP);
                int Sms = cursor.getColumnIndex(SMS);
                int Solution = cursor.getColumnIndex(SOLUTION);
                int gps = cursor.getColumnIndex(GPS);

                settings = new Settings(cursor.getString(personId),cursor.getString(fullName),cursor.getString(phoneNumber),Integer.parseInt(cursor.getString(age)),
                        cursor.getString(password),cursor.getString(DateTimeRegister),cursor.getString(mail),Boolean.parseBoolean(cursor.getString(PopUps)),Boolean.parseBoolean(cursor.getString(Sms))
                        ,Boolean.parseBoolean(cursor.getString(Solution)),Boolean.parseBoolean(cursor.getString(gps)));
            }
        return settings;
    }

    public static void create(SQLiteDatabase db) {
        db.execSQL("create table " +
                SETTINGS_TABLE + " (" +
                PERSON_ID + " TEXT," +
                FULL_NAME + " TEXT," +
                PHONE_NUMBER + " TEXT," +
                AGE + " TEXT," +
                PASSWORD + " TEXT," +
                DATE_TIME_REGISTER + " TEXT," +
                MAIL + " TEXT," +
                POPUP + " TEXT," +
                SMS + " TEXT," +
                SOLUTION + " TEXT," +
                GPS + " TEXT);");
    }

    public static void drop(SQLiteDatabase db) {
        db.execSQL("drop table " + SETTINGS_TABLE);
    }

}
