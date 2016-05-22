package com.example.oris1991.anotherme.Model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by eliav.menachi on 16/03/2016.
 */
public class SmsOrPopupSql {
    private static final String SMSPOPUP_TABLE = "sms_popup";
    private static final String SP_TYPE = "type";
    private static final String SP_SENTTO = "sent_to";
    private static final String SP_TIME = "time";
    private static final String SP_TEXT = "text";

    public static void add(SQLiteDatabase db, SMSOrPopup sp) {
        ContentValues values = new ContentValues();
        values.put(SP_TYPE, sp.getType());
        values.put(SP_SENTTO, sp.getSendto());
        values.put(SP_TIME, sp.getTime());
        values.put(SP_TEXT, sp.getText());
        db.insert(SMSPOPUP_TABLE, SP_SENTTO, values);
    }

/*    public static SMSOrPopup getSmsOrPopup(SQLiteDatabase db, String sp) {
        String[] params = new String[1];
        params[0] = stid;
        Cursor cursor = db.query(STUDENTS_TABLE, null, STUDENTS_ID + " = ?", params, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(STUDENTS_ID);
            int fnameIndex = cursor.getColumnIndex(STUDENTS_FNAME);
            int lnameIndex = cursor.getColumnIndex(STUDENTS_LNAME);
            int phoneIndex = cursor.getColumnIndex(STUDENTS_PHONE);
            int addressIndex = cursor.getColumnIndex(STUDENTS_ADDRESS);
            int imageNameIndex = cursor.getColumnIndex(STUDENTS_IMAGE_NAME);
            int checkedIndex = cursor.getColumnIndex(STUDENTS_CHECKED);
            int birthDateIndex = cursor.getColumnIndex(STUDENTS_BIRTH_DATE);
            int birthTimeIndex = cursor.getColumnIndex(STUDENTS_BIRTH_TIME);

            String id = cursor.getString(idIndex);
            String fname = cursor.getString(fnameIndex);
            String lname = cursor.getString(lnameIndex);
            String phone = cursor.getString(phoneIndex);
            String address = cursor.getString(addressIndex);
            String imageName = cursor.getString(imageNameIndex);
            String checked = cursor.getString(checkedIndex);
            boolean isChecked = true;
            if (checked.equals("NO")) {
                isChecked = false;
            }
            String birthDate = cursor.getString(birthDateIndex);
            String birthTime = cursor.getString(birthTimeIndex);
            Student st = new Student(id, fname, lname, phone, address, imageName,isChecked,birthDate,birthTime);
            return st;

        }
        return null;
    }*/

    public static List<SMSOrPopup> getSmsPopups(SQLiteDatabase db) {
        Cursor cursor = db.query(SMSPOPUP_TABLE,null, null, null, null, null, null);

        List<SMSOrPopup> list = new LinkedList<SMSOrPopup>();
        if (cursor.moveToFirst()) {
            int typeIndex = cursor.getColumnIndex(SP_TYPE);
            int sentIndex = cursor.getColumnIndex(SP_SENTTO);
            int timeIndex = cursor.getColumnIndex(SP_TIME);
            int textIndex = cursor.getColumnIndex(SP_TEXT);
            do {
                String type = cursor.getString(typeIndex);
                String sent = cursor.getString(sentIndex);
                String time = cursor.getString(timeIndex);
                String text = cursor.getString(textIndex);
                SMSOrPopup sp = new SMSOrPopup(type,sent,time,text);
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
            int typeIndex = cursor.getColumnIndex(SP_TYPE);
            int sentIndex = cursor.getColumnIndex(SP_SENTTO);
            int timeIndex = cursor.getColumnIndex(SP_TIME);
            int textIndex = cursor.getColumnIndex(SP_TEXT);
            do {
                String type = cursor.getString(typeIndex);
                String sent = cursor.getString(sentIndex);
                String time = cursor.getString(timeIndex);
                String text = cursor.getString(textIndex);
                SMSOrPopup sp = new SMSOrPopup(type,sent,time,text);
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
            int typeIndex = cursor.getColumnIndex(SP_TYPE);
            int sentIndex = cursor.getColumnIndex(SP_SENTTO);
            int timeIndex = cursor.getColumnIndex(SP_TIME);
            int textIndex = cursor.getColumnIndex(SP_TEXT);
            do {
                String type = cursor.getString(typeIndex);
                String sent = cursor.getString(sentIndex);
                String time = cursor.getString(timeIndex);
                String text = cursor.getString(textIndex);
                SMSOrPopup sp = new SMSOrPopup(type,sent,time,text);
                list.add(sp);
            } while (cursor.moveToNext());

        }

        return list;
    }

   /* public static void edit(SQLiteDatabase db, Student st, String stId) {
        String[] params = new String[1];
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
        db.update(STUDENTS_TABLE, values, STUDENTS_ID + " = ?",params);
    }


    public static void delete(SQLiteDatabase db, Student st) {
        String[] params = new String[1];
        params[0] = st.getId();
        db.delete(STUDENTS_TABLE, STUDENTS_ID + " = ?", params);
    }
*/
    public static void create(SQLiteDatabase db) {
        db.execSQL("create table " +
                SMSPOPUP_TABLE + " (" +
                SP_TYPE + " TEXT," +
                SP_SENTTO + " TEXT," +
                SP_TIME + " TEXT," +
                SP_TEXT + " TEXT);");
    }

    public static void drop(SQLiteDatabase db) {
        db.execSQL("drop table " + SMSPOPUP_TABLE);
    }



}
