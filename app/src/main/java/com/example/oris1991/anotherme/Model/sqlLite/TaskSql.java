package com.example.oris1991.anotherme.Model.sqlLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.oris1991.anotherme.Model.Entities.SMSOrPopup;
import com.example.oris1991.anotherme.Model.Entities.Task;

import java.util.LinkedList;
import java.util.List;

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
        values.put(SOLUTION, sp.getSolution().getIdSolution());
        db.insert(TASK_TABLE,ID,values);
    }

    public static List<Task> getTasks(SQLiteDatabase db)
    {

        Cursor cursor = db.query(TASK_TABLE,null,null,null, null, null, null);

        List<Task> list = new LinkedList<Task>();
        if (cursor.moveToFirst()) {
            int id = cursor.getColumnIndex(ID);
            int titleIndex = cursor.getColumnIndex(TITLE);
            int startIndex = cursor.getColumnIndex(START);
            int endIndex = cursor.getColumnIndex(END);
            int locationIndex = cursor.getColumnIndex(LOCATION);
            int solution = cursor.getColumnIndex(SOLUTION);
            do {
                int idd =  Integer.parseInt(cursor.getString(id));
                String title = cursor.getString(titleIndex);
                String start = cursor.getString(startIndex);
                String end = cursor.getString(endIndex);
                String location = cursor.getString(locationIndex);
                //String solution = cursor.getString(textIndex);
                Task t = new Task(idd,title,Long.valueOf(start),Long.valueOf(end),location);
                list.add(t);
            } while (cursor.moveToNext());

        }
        return list;

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
