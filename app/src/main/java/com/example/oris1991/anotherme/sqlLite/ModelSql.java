package com.example.oris1991.anotherme.sqlLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.oris1991.anotherme.Model.Gps;
import com.example.oris1991.anotherme.MyApplication;

import java.util.List;

/**
 * Created by eliav.menachi on 16/03/2016.
 */
public class ModelSql {
    private static final int VERSION = 1;

    MyDBHelper dbHelper;
    MyDBHelperGps dbGpsHelper;

    public ModelSql() {
        dbHelper = new MyDBHelper(MyApplication.getAppContext());
        dbGpsHelper= new MyDBHelperGps(MyApplication.getAppContext());
    }

    public void add(SMSOrPopup sp) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        SmsOrPopupSql.add(db, sp);
    }

   /* public Student getStudent(String id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return StudentSql.getStudent(db, id);
    }*/

    public List<SMSOrPopup> getSmsForPerson(String person) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return SmsOrPopupSql.getSmsForPerson(db, person);
    }

    public List<SMSOrPopup> getSmsOrPopups() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return SmsOrPopupSql.getSmsPopups(db);
    }

    public List<SMSOrPopup> getPopupsTemplates() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return SmsOrPopupSql.getPopupsTemplates(db);
    }

    public List<SMSOrPopup> getSmsTemplates() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return SmsOrPopupSql.getSmsTemplates(db);
    }

    public Gps getGps() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return GpsSql.getGps(db);
    }

    public void editGps(Gps gps) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        GpsSql.editGps (db,gps);


    }
/*    public void delete(Student st) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        StudentSql.delete(db, st);
    }

    public void edit(String stId, Student st) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        StudentSql.edit(db, st, stId);
    }*/


    class MyDBHelper extends SQLiteOpenHelper {

        public MyDBHelper(Context context) {
            super(context, "database.db", null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //create the DB schema
            SmsOrPopupSql.create(db);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
           SmsOrPopupSql.drop(db);
            onCreate(db);
        }
    }

    class MyDBHelperGps extends SQLiteOpenHelper {

        public MyDBHelperGps(Context context) {
            super(context, "database.db", null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //create the DB schema
            GpsSql.create(db);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            GpsSql.drop(db);
            onCreate(db);
        }
    }
}
