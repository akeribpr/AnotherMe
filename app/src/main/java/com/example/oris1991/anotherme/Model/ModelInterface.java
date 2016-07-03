package com.example.oris1991.anotherme.Model;


import android.database.sqlite.SQLiteDatabase;

import com.example.oris1991.anotherme.Model.Entities.Gps;
import com.example.oris1991.anotherme.Model.Entities.LogIn;
import com.example.oris1991.anotherme.Model.Entities.SMSOrPopup;
import com.example.oris1991.anotherme.Model.Entities.SharePictureOrText;
import com.example.oris1991.anotherme.Model.Entities.Solution;
import com.example.oris1991.anotherme.Model.Entities.Task;
import com.example.oris1991.anotherme.Model.Entities.Users;
import com.example.oris1991.anotherme.Model.ModelServer.GPS.ServerGps;

import java.util.List;

/**
 * Created by eldar on 31/05/2016.
 */
public interface ModelInterface {
    public Boolean checkIfUserExist(LogIn logIn);
    public void addSmsOrPop(SMSOrPopup sp);
    public List<SMSOrPopup> getSmsForPerson(String person);
    public List<SMSOrPopup> getSmsOrPopups();
    public List<SMSOrPopup> getPopupsTemplates();
    public List<SMSOrPopup> getSmsTemplates();
    public List<SMSOrPopup> getSmsTemplatesWithoutPerson();
    public SMSOrPopup getSmsOrPopupById(int id);
    public void addTask(Task task);
    public void deleteTask(int id);
    void addNewGpsLocation(ServerGps gps);
    public Boolean register(LogIn sp);
    public void addPic(SharePictureOrText sp);
    public void addUser(Users sp);
    public List<SharePictureOrText> getPicture();
    public List<Users> getUsers();
    public Boolean deleteUser(Users sp);
    public Solution getSolution(int solid);
    public  void deleteSolution(int id);
    public  List<SharePictureOrText>  checkUpdateShare();
    public void addTaskWithSolution(Task task);
    public List<Task> getTasks();
    public  List<Task>  checkUpdateTask();
    public int numberOfRowe(String table);
    public Task getTask(int taskId);
    public  void deleteSmsOrPopup(int id);
    public void editTimeBefore(int id, int time);
    public String getPhoneNumber(String personId);
    public void deleteHistoryById(int id);
    public List<SMSOrPopup> getHistory();
    public void addHistoryEvent(SMSOrPopup sp);
    public void addPictuerFromServer(SharePictureOrText sp);
    public  Boolean deleteSharePictureOrText(int sp);




}
