package com.example.oris1991.anotherme.Model.ModelServer;

import com.example.oris1991.anotherme.Model.Entities.LogIn;
import com.example.oris1991.anotherme.Model.Entities.SharePictureOrText;
import com.example.oris1991.anotherme.Model.Entities.Solution;
import com.example.oris1991.anotherme.Model.Entities.Task;
import com.example.oris1991.anotherme.Model.Entities.Users;
import com.example.oris1991.anotherme.Model.Model;
import com.example.oris1991.anotherme.Model.ModelInterface;
import com.example.oris1991.anotherme.Model.Entities.SMSOrPopup;
import com.example.oris1991.anotherme.Model.ModelServer.GPS.ServerGps;
import com.example.oris1991.anotherme.Model.ModelServer.Task.ServerTask;

import java.io.IOException;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by itzik on 31/05/2016.
 */
public class ModelServer implements ModelInterface {
    public static final String url= "http://192.168.43.35:8080/Another-Me";
    PersonModelServer personModelServer;
    TaskModelServer taskModelServer;
     GpsModelServer gpsModelServer;
    PicturesModelServer picturesModelServer;
    public  ModelServer(){
        taskModelServer = new TaskModelServer();
        personModelServer = new PersonModelServer();
        gpsModelServer=new GpsModelServer();
        picturesModelServer=new PicturesModelServer();
    }

//    String personId = Model.instance().getUser().getServerPersonId();
    public Boolean register(LogIn login){
        Boolean bool = false;
        try {
         bool  = personModelServer.register(login.getPersonId(),login.getPassword(),null,"05555555555","default@gmail.com");

           return bool;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  bool;
    }

    @Override
    public void addSmsOrPop(SMSOrPopup sp) {
               if ((sp.getType()).equals("Sms template")){

                   taskModelServer.addSms(true,sp.getText(),Model.instance().getUser().getPersonId(),sp.getSendtoName());

               }
        else{

                   taskModelServer.addPopUp(sp.getText(),false,"",Model.instance().getUser().getPersonId());

               }

    }

    @Override
    public List<SMSOrPopup> getSmsForPerson(String person) {
        return null;
    }

    @Override
    public List<SMSOrPopup> getSmsOrPopups() {
        return null;
    }

    @Override
    public List<SMSOrPopup> getPopupsTemplates() {
        return null;
    }

    @Override
    public List<SMSOrPopup> getSmsTemplates() {
        return null;
    }

    @Override
    public List<SMSOrPopup> getSmsTemplatesWithoutPerson() {
        return null;
    }

    @Override
    public SMSOrPopup getSmsOrPopupById(int id) {
        return null;
    }


    @Override
    public void addTask(Task task) {

        if(task.getSolution()==null){
            taskModelServer.addNewTask
                    (Model.instance().getUser().getPersonId(),task.getTitle(),new Date(task.getStartTime()) ,new Date(task.getEndTime()),null,7,null,null,null,1);
        }

else {
//            taskModelServer.addNewTask(Model.instance().getUser().getPersonId(),task.getTitle(),new Date(task.getStartTime()) ,new Date(task.getEndTime()),task.getSolution().getSms().getSendtoName(),7,String.valueOf(task.getSolution().getIdSolution()),(double)task.getSolution().getPopUp().getId(),(double)task.getSolution().getSms().getId(),2);
            taskModelServer.addNewTask(Model.instance().getUser().getPersonId(),task.getTitle(),new Date(task.getStartTime()) ,new Date(task.getEndTime()),task.getLocation(),7,task.getSolution().getSms().getSendtoName(),(double)task.getSolution().getPopUp().getId(),(double)task.getSolution().getSms().getId(),task.getSolution().getWhatToDo());

        }


//        taskModelServer.getSharedPictures(Model.instance().getUser().getPersonId());
//        taskModelServer.getTasksToDO(Model.instance().getUser().getPersonId());
    }

    @Override
    public void deleteTask(int id) {

    }

    @Override
    public void addNewGpsLocation(ServerGps gps){
        gpsModelServer.addNewGpsLocation(gps);
    }

    @Override
    public void addPic(SharePictureOrText sp) {
        picturesModelServer.addPicturesToShare(sp.getPicName(),Model.instance().getUser().getPersonId(),sp.getShardWtith(),sp.getText());
    }

    @Override
    public void addUser(Users sp) {

    }

    @Override
    public List<SharePictureOrText> getPicture() {
        return null;
    }

    @Override
    public List<Users> getUsers() {
        return null;
    }

    @Override
    public Boolean deleteUser(Users sp) {
        return null;
    }

    @Override
    public Solution getSolution(int solid) {
        return null;
    }

    @Override
    public void deleteSolution(int id) {

    }

    @Override
    public List<SharePictureOrText> checkUpdateShare() {
        return null;
    }

    @Override
    public void addTaskWithSolution(Task task) {

    }

    @Override
    public List<Task> getTasks() {
        return null;
    }

    @Override
    public List<Task> checkUpdateTask() {
        List<ServerTask> taskServer = taskModelServer.getTasksToDO(Model.instance().getUser().getPersonId());
        if(taskServer==null){
            return null;
        }
        else{
            List<Task> tasks = new ArrayList<Task>();
            for (int i= 0;i<taskServer.size();i++){

                ServerTask s = taskServer.get(i);
      //          Task t = taskServer.get(i).convertServerTask();
            //    tasks.add(t);
            }
            return tasks;
        }

    }

    @Override
    public int numberOfRowe(String table) {
        return 0;
    }

    @Override
    public Task getTask(int taskId) {
        return null;
    }

    @Override
    public void deleteSmsOrPopup(int id) {

    }

    @Override
    public void editTimeBefore(int id, int time) {

    }

    @Override
    public String getPhoneNumber(String personId) {
        return null;
    }
}
