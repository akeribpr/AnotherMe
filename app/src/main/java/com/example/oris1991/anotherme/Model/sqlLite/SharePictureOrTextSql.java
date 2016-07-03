package com.example.oris1991.anotherme.Model.sqlLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.oris1991.anotherme.Model.Entities.LogIn;
import com.example.oris1991.anotherme.Model.Entities.SMSOrPopup;
import com.example.oris1991.anotherme.Model.Entities.SharePictureOrText;
import com.example.oris1991.anotherme.Model.Entities.Users;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Itzik on 01/06/2016.
 */
public class SharePictureOrTextSql {


    private static final String  PICTURE_TABLE = "Picture_table";
    private static final String ID = "id";
    private static final String PIC_NAME = "picName";
    private static final String SHARD = "shardWtith";
    private static final String TEXT = "text";
    private static final String SEND = "send";

    public static void addPic(SQLiteDatabase db, SharePictureOrText sp) {

        ContentValues values = new ContentValues();
        values.put(ID, sp.getId());
        values.put(PIC_NAME, sp.getPicName());
        values.put(SHARD, sp.getShardWtith());
        values.put(TEXT, sp.getText());
        values.put(SEND, sp.getSend().toString());
        db.insert(PICTURE_TABLE, ID,values);
    }
    public static List<SharePictureOrText> getPictureOrText(SQLiteDatabase db) {

        List<SharePictureOrText> list = new LinkedList<SharePictureOrText>();
        Cursor cursor = db.query(PICTURE_TABLE, null, null, null, null, null, null);
        if (!(cursor.moveToFirst()) || cursor.getCount() ==0){
            return list;
        }

        if (cursor.moveToFirst()) {
            int id = cursor.getColumnIndex(ID);
            int picNameIndex = cursor.getColumnIndex(PIC_NAME);
            int shardWtithIndex = cursor.getColumnIndex(SHARD);
            int textIndex = cursor.getColumnIndex(TEXT);
            int sendIndex = cursor.getColumnIndex(SEND);
            do {
                int idd =  Integer.parseInt(cursor.getString(id));
                String picName = cursor.getString(picNameIndex);
                String shardWtith = cursor.getString(shardWtithIndex);
                String text = cursor.getString(textIndex);
                Boolean send = Boolean.parseBoolean(cursor.getString(sendIndex));
                SharePictureOrText sp = new SharePictureOrText(idd,picName,shardWtith,text,send);
                list.add(sp);
            } while (cursor.moveToNext());

        }

        return list;
    }
    public static Boolean deleteSharePictureOrText(SQLiteDatabase db, int sp) {

        db.delete(PICTURE_TABLE, ID + " = '" + sp+"'", null);
        return true;
    }
    public static void create(SQLiteDatabase db) {
        db.execSQL("create table " +
                PICTURE_TABLE + " (" +
                ID + " INTEGER," +
                PIC_NAME + " TEXT," +
                SHARD + " TEXT," +
                TEXT + " TEXT," +
                SEND + " TEXT);");
    }

    public static void drop(SQLiteDatabase db) {
        db.execSQL("drop table " + PICTURE_TABLE);
    }


}
