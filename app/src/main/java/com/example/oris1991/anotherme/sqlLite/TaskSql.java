package com.example.oris1991.anotherme.sqlLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.oris1991.anotherme.Model.LogIn;
import com.example.oris1991.anotherme.Model.Solution;
import com.example.oris1991.anotherme.Model.Task;

/**
 * Created by Itzik on 30/05/2016.
 */
public class TaskSql {


    private static final String TASK_TABLE = "Task_Table";
    private static final String START = "startTime";
    private static final String END = "endTime";
    private static final String TITLE = "Title";
    private static final String LOCATION = "location";
    private static final String WHAT_TO_DO = "whatToDo";
    private static final String SOLUTION = "solution_Id";

    public static void addTask(SQLiteDatabase db, Task sp) {

        ContentValues values = new ContentValues();
        values.put(TITLE, sp.getTitle());
        values.put(START, sp.getStartTime());
        values.put(END, sp.getEndTime());
        values.put(LOCATION, sp.getLocation());
        values.put(WHAT_TO_DO, sp.getWhatToDo());
//        values.put(WHAT_TO_DO, sp.getSolution());
        db.insert(TASK_TABLE, START,values);
    }


//    public static LogIn getUser(SQLiteDatabase db) {
//
//        LogIn logIn = null;
//
//        Cursor cursor = db.query(TASK_TABLE, null, null, null, null, null, null);
//
//
//        if (cursor.moveToFirst()) {
//            int personId = cursor.getColumnIndex(PERSON_ID);
//            int password = cursor.getColumnIndex(PASSWORD);
//
//            logIn = new LogIn(cursor.getString(personId),cursor.getString(password));
//            return logIn;
//        }
//        return logIn;
//    }
//
//    public static boolean checkUser(SQLiteDatabase db, LogIn sp) {
//
//        LogIn logData = getUser(db);
//        Log.d("Log",logData.getPersonId() +"    "+logData.getPassword());
//        Log.d("Log",sp.getPersonId() +"    "+sp.getPassword());
//
//        if ((logData.getPersonId().equals(sp.getPersonId()))&&(logData.getPassword().equals(sp.getPassword()))) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//    public static void create(SQLiteDatabase db) {
//        db.execSQL("create table " +
//                LOGIN_TABLE + " (" +
//                PERSON_ID + " TEXT," +
//                PASSWORD + " TEXT);");
//    }
//
//    public static void drop(SQLiteDatabase db) {
//        db.execSQL("drop table " + LOGIN_TABLE);
//    }

}
