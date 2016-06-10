package com.example.oris1991.anotherme.Model.sqlLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.oris1991.anotherme.Model.Entities.SMSOrPopup;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by eliav.menachi on 16/03/2016.
 */
public class SmsOrPopupSql {
    private static final String SMSPOPUP_TABLE = "sms_popup";
    private static final String ID = "id";
    private static final String SP_TYPE = "type";
    private static final String SP_SENTTO = "sent_to";
    private static final String SP_SENTTO_NAME = "sent_to_name";
    private static final String SP_TIME = "time";
    private static final String SP_TEXT = "text";

    public static void add(SQLiteDatabase db, SMSOrPopup sp) {
        ContentValues values = new ContentValues();
        values.put(ID, sp.getId());
        values.put(SP_TYPE, sp.getType());
        values.put(SP_SENTTO, sp.getSendto());
        values.put(SP_SENTTO_NAME, sp.getSendtoName());
        values.put(SP_TIME, sp.getTime());
        values.put(SP_TEXT, sp.getText());
        db.insert(SMSPOPUP_TABLE, SP_SENTTO, values);
    }

    public static int numberOfRow(SQLiteDatabase db){
        String countQuery = "SELECT  * FROM " + SMSPOPUP_TABLE;
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
      //  cursor.close();
        return cnt;
    }

    public static List<SMSOrPopup> getSmsForPerson(SQLiteDatabase db,String person) {
        String [] selectionArgs ={"Sms template",person};
        Cursor cursor = db.query(SMSPOPUP_TABLE, null, SP_TYPE + " = ?" + "and " + SP_SENTTO + " = ?", selectionArgs, null, null, null);

        List<SMSOrPopup> list = new LinkedList<SMSOrPopup>();
        if (cursor.moveToFirst()) {
            int id = cursor.getColumnIndex(ID);
            int typeIndex = cursor.getColumnIndex(SP_TYPE);
            int sentIndex = cursor.getColumnIndex(SP_SENTTO);
            int sentNameIndex = cursor.getColumnIndex(SP_SENTTO_NAME);
            int timeIndex = cursor.getColumnIndex(SP_TIME);
            int textIndex = cursor.getColumnIndex(SP_TEXT);
            do {
                int idd =  Integer.parseInt(cursor.getString(id));
                String type = cursor.getString(typeIndex);
                String sent = cursor.getString(sentIndex);
                String sentName = cursor.getString(sentNameIndex);
                String time = cursor.getString(timeIndex);
                String text = cursor.getString(textIndex);
                SMSOrPopup sp = new SMSOrPopup(idd,type,sent,sentName,time,text);
                list.add(sp);
            } while (cursor.moveToNext());

        }
       // cursor.close();
        return list;
    }

    public static List<SMSOrPopup> getSmsPopups(SQLiteDatabase db) {
        String [] selectionArgs ={"SMS","Popup"};
        Cursor cursor = db.query(SMSPOPUP_TABLE,null, SP_TYPE + " = ?" +"or " + SP_TYPE + " = ?",selectionArgs, null, null, null);

        List<SMSOrPopup> list = new LinkedList<SMSOrPopup>();
        if (cursor.moveToFirst()) {
            int id = cursor.getColumnIndex(ID);
            int typeIndex = cursor.getColumnIndex(SP_TYPE);
            int sentIndex = cursor.getColumnIndex(SP_SENTTO);
            int sentNameIndex = cursor.getColumnIndex(SP_SENTTO_NAME);
            int timeIndex = cursor.getColumnIndex(SP_TIME);
            int textIndex = cursor.getColumnIndex(SP_TEXT);
            do {
                int idd =  Integer.parseInt(cursor.getString(id));
                String type = cursor.getString(typeIndex);
                String sent = cursor.getString(sentIndex);
                String sentName = cursor.getString(sentNameIndex);
                String time = cursor.getString(timeIndex);
                String text = cursor.getString(textIndex);
                SMSOrPopup sp = new SMSOrPopup(idd,type,sent,sentName,time,text);
                list.add(sp);
            } while (cursor.moveToNext());

        }

        return list;
    }

    public static List<SMSOrPopup> getPopupsTemplates(SQLiteDatabase db) {
        String [] selectionArgs ={"Popup template"};
        Cursor cursor = db.query(SMSPOPUP_TABLE,null,SP_TYPE + " = ?",selectionArgs, null, null, null);

        List<SMSOrPopup> list = new LinkedList<SMSOrPopup>();
        if (cursor.moveToFirst()) {
            int id = cursor.getColumnIndex(ID);
            int typeIndex = cursor.getColumnIndex(SP_TYPE);
            int sentIndex = cursor.getColumnIndex(SP_SENTTO);
            int sentNameIndex = cursor.getColumnIndex(SP_SENTTO_NAME);
            int timeIndex = cursor.getColumnIndex(SP_TIME);
            int textIndex = cursor.getColumnIndex(SP_TEXT);
            do {
                int idd =  Integer.parseInt(cursor.getString(id));
                String type = cursor.getString(typeIndex);
                String sent = cursor.getString(sentIndex);
                String sentName = cursor.getString(sentNameIndex);
                String time = cursor.getString(timeIndex);
                String text = cursor.getString(textIndex);
                SMSOrPopup sp = new SMSOrPopup(idd,type,sent,sentName,time,text);
                list.add(sp);
            } while (cursor.moveToNext());

        }

        return list;
    }

    public static List<SMSOrPopup> getLocation(SQLiteDatabase db) {
        String [] selectionArgs ={"Location"};
        Cursor cursor = db.query(SMSPOPUP_TABLE,null,SP_TYPE + " = ?",selectionArgs, null, null, null);

        List<SMSOrPopup> list = new LinkedList<SMSOrPopup>();
        if (cursor.moveToFirst()) {
            int id = cursor.getColumnIndex(ID);
            int typeIndex = cursor.getColumnIndex(SP_TYPE);
            int sentIndex = cursor.getColumnIndex(SP_SENTTO);
            int timeIndex = cursor.getColumnIndex(SP_TIME);
            int sentNameIndex = cursor.getColumnIndex(SP_SENTTO_NAME);
            int textIndex = cursor.getColumnIndex(SP_TEXT);
            do {
                int idd =  Integer.parseInt(cursor.getString(id));
                String type = cursor.getString(typeIndex);
                String sent = cursor.getString(sentIndex);
                String sentName = cursor.getString(sentNameIndex);
                String time = cursor.getString(timeIndex);
                String text = cursor.getString(textIndex);
                SMSOrPopup sp = new SMSOrPopup(idd,type,sent,sentName,time,text);
                list.add(sp);
            } while (cursor.moveToNext());

        }

        return list;
    }

    public static List<SMSOrPopup> getSmsTemplates(SQLiteDatabase db) {
        String [] selectionArgs ={"Sms template"};
        Cursor cursor = db.query(SMSPOPUP_TABLE,null,SP_TYPE + " = ?",selectionArgs, null, null, null);

        List<SMSOrPopup> list = new LinkedList<SMSOrPopup>();
        if (cursor.moveToFirst()) {
            int id = cursor.getColumnIndex(ID);
            int typeIndex = cursor.getColumnIndex(SP_TYPE);
            int sentIndex = cursor.getColumnIndex(SP_SENTTO);
            int sentNameIndex = cursor.getColumnIndex(SP_SENTTO_NAME);
            int timeIndex = cursor.getColumnIndex(SP_TIME);
            int textIndex = cursor.getColumnIndex(SP_TEXT);
            do {
                int idd =  Integer.parseInt(cursor.getString(id));
                String type = cursor.getString(typeIndex);
                String sent = cursor.getString(sentIndex);
                String sentName = cursor.getString(sentNameIndex);
                String time = cursor.getString(timeIndex);
                String text = cursor.getString(textIndex);
                SMSOrPopup sp = new SMSOrPopup(idd,type,sent,sentName,time,text);
                list.add(sp);
            } while (cursor.moveToNext());

        }


        return list;
    }

    public static SMSOrPopup getSmsOrPopupById(SQLiteDatabase db, int id) {

        String [] selectionArgs ={String.valueOf(id)};
        Cursor cursor = db.query(SMSPOPUP_TABLE,null,ID + " = ?",selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(ID);
            int typeIndex = cursor.getColumnIndex(SP_TYPE);
            int sentIndex = cursor.getColumnIndex(SP_SENTTO);
            int sentNameIndex = cursor.getColumnIndex(SP_SENTTO_NAME);
            int timeIndex = cursor.getColumnIndex(SP_TIME);
            int textIndex = cursor.getColumnIndex(SP_TEXT);
                int idd =  Integer.parseInt(cursor.getString(idIndex));
                String type = cursor.getString(typeIndex);
                String sent = cursor.getString(sentIndex);
                String sentName = cursor.getString(sentNameIndex);
                String time = cursor.getString(timeIndex);
                String text = cursor.getString(textIndex);
                SMSOrPopup sp = new SMSOrPopup(idd,type,sent,sentName,time,text);
                return sp;
        }
        return null;
    }



    public static void create(SQLiteDatabase db) {
        db.execSQL("create table " +
                SMSPOPUP_TABLE + " (" +
                ID + " TEXT," +
                SP_TYPE + " TEXT," +
                SP_SENTTO + " TEXT," +
                SP_SENTTO_NAME + " TEXT," +
                SP_TIME + " TEXT," +
                SP_TEXT + " TEXT);");
    }

    public static void drop(SQLiteDatabase db) {
        db.execSQL("drop table " + SMSPOPUP_TABLE);
    }



}
