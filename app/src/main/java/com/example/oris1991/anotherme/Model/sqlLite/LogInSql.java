package com.example.oris1991.anotherme.Model.sqlLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.oris1991.anotherme.Model.Entities.LogIn;

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

            Cursor cursor = db.query(LOGIN_TABLE, null, null, null, null, null, null);


        if (!(cursor.moveToFirst()) || cursor.getCount() ==0){
            return logIn = new LogIn("1","1");
        }

            if (cursor.moveToFirst()) {
                int personId = cursor.getColumnIndex(PERSON_ID);
                int password = cursor.getColumnIndex(PASSWORD);

                logIn = new LogIn(cursor.getString(personId), cursor.getString(password));
                return logIn;
            }

        return logIn;
    }

//    public static boolean tableExists(SQLiteDatabase db){
//        Cursor cursor = db.query("SELECT name FROM sqlite_master WHERE type='table' AND name='"+LOGIN_TABLE+"'");
//
//
//        if (cursor.moveToFirst()) {
//            int personId = cursor.getColumnIndex(PERSON_ID);
//            int password = cursor.getColumnIndex(PASSWORD);
//
//        return true
//
//    }

    public static boolean checkUser(SQLiteDatabase db, LogIn sp) {

        LogIn logData = getUser(db);
        Log.d("Log",logData.getPersonId() +"    "+logData.getPassword());
        Log.d("Log",sp.getPersonId() +"    "+sp.getPassword());

//        if(logData==null){
//            return false;
//        }
        if ((logData.getPersonId().equals(sp.getPersonId()))&&(logData.getPassword().equals(sp.getPassword()))) {
            return true;
        } else {
            return false;
        }
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
