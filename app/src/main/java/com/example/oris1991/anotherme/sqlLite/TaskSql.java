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
    private static final String ID = "id";
    private static final String START = "startTime";
    private static final String END = "endTime";
    private static final String TITLE = "Title";
    private static final String LOCATION = "location";
    private static final String WHAT_TO_DO = "whatToDo";
    private static final String SOLUTION = "solution_Id";

    public static void addTask(SQLiteDatabase db, Task sp) {

        ContentValues values = new ContentValues();
        values.put(ID, sp.getId());
        values.put(TITLE, sp.getTitle());
        values.put(START, sp.getStartTime());
        values.put(END, sp.getEndTime());
        values.put(LOCATION, sp.getLocation());
        values.put(WHAT_TO_DO, sp.getWhatToDo());
        values.put(SOLUTION, sp.getSolution().getIdSolution());
        db.insert(TASK_TABLE,ID,values);
    }


    public static Task getTask(SQLiteDatabase db) {

        Task task = null;

        Cursor cursor = db.query(TASK_TABLE, null, null, null, null, null, null);


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
                TASK_TABLE + " (" +
                ID + " INTEGER," +
                START + " TEXT," +
                END + " TEXT," +
                TITLE + " TEXT," +
                LOCATION + " TEXT," +
                WHAT_TO_DO + " INTEGER," +
                SOLUTION + " INTEGER);");
    }

    public static void drop(SQLiteDatabase db) {
        db.execSQL("drop table " + TASK_TABLE);
    }


}
