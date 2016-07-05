package com.example.oris1991.anotherme.Model.sqlLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.oris1991.anotherme.Model.Entities.Users;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Itzik on 02/06/2016.
 */
public class UsersSql {

    private static final String USERS_TABLE = "Users_table";
    private static final String NAME = "Name";

    public static void addUser(SQLiteDatabase db, Users sp) {

        ContentValues values = new ContentValues();
        values.put(NAME, sp.getname());
        db.insert(USERS_TABLE, null, values);
    }

    public static List<Users> getUsers(SQLiteDatabase db) {

        List<Users> list = new LinkedList<Users>();
        Cursor cursor = db.query(USERS_TABLE, null, null, null, null, null, null);
        if (!(cursor.moveToFirst()) || cursor.getCount() == 0) {
            return list;
        }
        if (cursor.moveToFirst()) {
            int nameId = cursor.getColumnIndex(NAME);
            do {
                Users user = new Users(cursor.getString(nameId));
                list.add(user);
            } while (cursor.moveToNext());
        }

        return list;
    }

    public static Boolean deleteUser(SQLiteDatabase db, Users sp) {

        db.delete(USERS_TABLE, NAME + " = '" + sp.getname() + "'", null);
        return true;
    }

    public static void create(SQLiteDatabase db) {
        db.execSQL("create table " +
                USERS_TABLE + " (" +
                NAME + " TEXT);");
    }

    public static void drop(SQLiteDatabase db) {
        db.execSQL("drop table " + USERS_TABLE);
    }


}
