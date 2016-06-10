package com.example.oris1991.anotherme.Model.sqlLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.oris1991.anotherme.Model.Entities.SMSOrPopup;
import com.example.oris1991.anotherme.Model.Entities.Solution;
import com.example.oris1991.anotherme.Model.Model;


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
        if(sol.getSms()!=null){
            values.put(SMS_ID, sol.getSms().getId());

        }
        if(sol.getPopUp()!=null){
            values.put(POPUP_ID, sol.getPopUp().getId());
        }
        values.put(WHAT_TO_DO, sol.getWhatToDo());
        db.insert(SOLUTION_TABLE,ID,values);
    }


    public static Solution getSolution(SQLiteDatabase db, int solid ) {

        String[] params = new String[1];
        params[0] = String.valueOf(solid);

        Cursor cursor = db.query(SOLUTION_TABLE, null, ID+ "=?", params, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(ID);
            int smsIndex = cursor.getColumnIndex(SMS_ID);
            int popupIndex = cursor.getColumnIndex(POPUP_ID);
            int whatIndex = cursor.getColumnIndex(WHAT_TO_DO);

            String id = cursor.getString(idIndex);
            String sms = cursor.getString(smsIndex);
            String popup = cursor.getString(popupIndex);
            String what = cursor.getString(whatIndex);

            SMSOrPopup smsNew = Model.instance().getSmsOrPopupById(Integer.valueOf(sms));
            SMSOrPopup popupNew = Model.instance().getSmsOrPopupById(Integer.valueOf(popup));

            Solution sol = new Solution(Integer.valueOf(id),smsNew,popupNew,Integer.valueOf(what));

            //Log.d("dd","dd");
            return sol;


        }
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