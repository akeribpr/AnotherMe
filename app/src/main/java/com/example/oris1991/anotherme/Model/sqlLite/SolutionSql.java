package com.example.oris1991.anotherme.Model.sqlLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.oris1991.anotherme.Model.Entities.Solution;


/**
 * Created by Itzik on 30/05/2016.
 */
public class SolutionSql {


    private static final String SOLUTION_TABLE = "Solution_Table";
    private static final String ID = "id";
    private static final String SMS_ID = "sms";
    private static final String POPUP_ID = "popUp";
    private static final String WHAT_TO_DO = "whatToDo";

    public static void addSolution(SQLiteDatabase db, Solution sol) {

        ContentValues values = new ContentValues();
        values.put(ID, sol.getIdSolution());
        values.put(SMS_ID, sol.getSms().getId());
        values.put(POPUP_ID, sol.getPopUp().getId());
        values.put(WHAT_TO_DO, sol.getWhatToDo());
        db.insert(SOLUTION_TABLE,ID,values);
    }


    public static Solution getSolution(SQLiteDatabase db) {

        Solution solution = null;

        Cursor cursor = db.query(SOLUTION_TABLE, null, null, null, null, null, null);


//        if (cursor.moveToFirst()) {
//            int personId = cursor.getColumnIndex(PERSON_ID);
//            int password = cursor.getColumnIndex(PASSWORD);
//
//            logIn = new LogIn(cursor.getString(personId),cursor.getString(password));
//            return logIn;
//        }
//        return logIn;
        return null;
    }


    public static void create(SQLiteDatabase db) {
        db.execSQL("create table " +
                SOLUTION_TABLE + " (" +
                ID + " INTEGER," +
                SMS_ID + " INTEGER," +
                POPUP_ID + " INTEGER," +
                WHAT_TO_DO + " INTEGER);");
    }

    public static void drop(SQLiteDatabase db) {
        db.execSQL("drop table " + SOLUTION_TABLE);
    }
}