package com.example.oris1991.anotherme.sqlLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.oris1991.anotherme.Model.LogIn;
import com.example.oris1991.anotherme.Model.Settings;

/**
 * Created by Itzik on 29/05/2016.
 */
public class LogInSql {


    private static final String  LOGIN_TABLE = "LogIn_table";
    private static final String PERSON_ID = "personId";
    private static final String PASSWORD = "password";

    public static void register(SQLiteDatabase db, LogIn sp) {
        ContentValues values = new ContentValues();
        values.put(PERSON_ID, sp.getPersonId());
        values.put(PASSWORD, sp.getPassword());
        db.insert(LOGIN_TABLE, PERSON_ID,values);
    }
    public static LogIn getUser(SQLiteDatabase db) {

        LogIn logIn = null;
        Cursor cursor = db.query(LOGIN_TABLE,null,null,null, null, null, null);

        if (cursor.moveToFirst()) {
            int personId = cursor.getColumnIndex(PERSON_ID);
            int password = cursor.getColumnIndex(PASSWORD);

            logIn = new LogIn(cursor.getString(personId),cursor.getString(password));
        }
        return logIn;
    }

    public static void create(SQLiteDatabase db) {
        db.execSQL("create table " +
                LOGIN_TABLE + " (" +
                PERSON_ID + " TEXT," +
                PASSWORD + " TEXT);");
    }

    public static void drop(SQLiteDatabase db) {
        db.execSQL("drop table " + LOGIN_TABLE);
    }


}
