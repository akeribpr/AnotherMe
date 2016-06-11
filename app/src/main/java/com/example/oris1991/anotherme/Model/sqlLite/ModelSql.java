package com.example.oris1991.anotherme.Model.sqlLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.oris1991.anotherme.Model.Entities.LogIn;
import com.example.oris1991.anotherme.Model.Entities.SMSOrPopup;
import com.example.oris1991.anotherme.Model.Entities.Solution;
import com.example.oris1991.anotherme.Model.Entities.SharePictureOrText;
import com.example.oris1991.anotherme.Model.Entities.Task;
import com.example.oris1991.anotherme.Model.Entities.Users;
import com.example.oris1991.anotherme.Model.ModelInterface;
import com.example.oris1991.anotherme.MyApplication;

import java.util.List;

/**
 * Created by eliav.menachi on 16/03/2016.
 */
public class ModelSql implements ModelInterface {
    private static final int VERSION = 2;

    MyDBHelper dbHelper;
    private static final String TASK_TABLE = "Task_Table";
    private static final String SMSPOPUP_TABLE = "sms_popup";
    private static final String  PICTURE_TABLE = "Picture_table";
    public ModelSql() {
        dbHelper = new MyDBHelper(MyApplication.getAppContext());
    }

    public void register(LogIn logIn){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        LogInSql.register(db, logIn);
    }

    public LogIn getUser(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
       return LogInSql.getUser(db);
    }
    public boolean checkIfUserExist(LogIn logIn){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return LogInSql.checkUser(db,logIn);
    }

    public int numberOfRow(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return SmsOrPopupSql.numberOfRow(db);
    }
    public int numberOfRowe(String table){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String countQuery = "SELECT  * FROM " + table;
        try {
            Cursor cursor = db.rawQuery(countQuery, null);
        }catch (Exception e){
            return 1;
        }
        Cursor cursor = db.rawQuery(countQuery, null);
        if (!(cursor.moveToFirst()) || cursor.getCount() ==0){
            return 1;
        }
        int cnt = cursor.getCount();
        //  cursor.close();
        return cnt+1;

    }
    public void addSmsOrPop(SMSOrPopup sp) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        sp.setId(numberOfRowe(SMSPOPUP_TABLE));
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

    @Override
    public List<SMSOrPopup> getSmsTemplatesWithoutPerson() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return SmsOrPopupSql.getSmsTemplatesWithoutPerson(db);

    }

    public SMSOrPopup getSmsOrPopupById(int id)
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return SmsOrPopupSql.getSmsOrPopupById(db, id);
    }

    @Override
    public void deleteTask(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        TaskSql.deleteTask(db,id);
    }

    public void addSolution(Solution sol) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        SolutionSql.addSolution(db, sol);
    }

    @Override
    public void addTask(Task task) {

        task.setId( numberOfRowe(TASK_TABLE));
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        TaskSql.addTask(db,task);
    }

    @Override
    public void addPic(SharePictureOrText sp) {
        sp.setId(numberOfRowe(PICTURE_TABLE));
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        SharePictureOrTextSql.addPic(db,sp);
    }

    @Override
    public void addUser(Users sp) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        UsersSql.addUser(db,sp);
    }

    @Override
    public List<SharePictureOrText> getPicture() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return SharePictureOrTextSql.getPictureOrText(db);
    }

    @Override
    public List<Users> getUsers() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return UsersSql.getUsers(db);
    }

    @Override
    public Boolean deleteUser(Users sp) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return UsersSql.deleteUser(db,sp);
    }

    @Override
    public Solution getSolution(int solid) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        return SolutionSql.getSolution(db,solid);
    }

    @Override
    public void deleteSolution(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        SolutionSql.deleteSolution(db,id);
    }

    @Override
    public List<SharePictureOrText> checkUpdateShare() {
        return null;
    }


    @Override
    public void addTaskWithSolution(Task task) {
        Task newTask = task;
        if (task.getSolution()!=null){
            newTask.getSolution().setIdSolution(numberOfRowe(TASK_TABLE));
            addSolution(newTask.getSolution());}
        addTask(newTask);

    }

    @Override
    public List<Task> getTasks() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return TaskSql.getTasks(db);
    }

    @Override
    public List<Task> checkUpdateTask() {
        return null;
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
            LogInSql.create(db);
            TaskSql.create(db);
            SharePictureOrTextSql.create(db);
            UsersSql.create(db);
            SolutionSql.create(db);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
           SmsOrPopupSql.drop(db);
            LogInSql.drop(db);
            SharePictureOrTextSql.drop(db);
            UsersSql.drop(db);
            SolutionSql.drop(db);
            onCreate(db);
        }
    }
}
