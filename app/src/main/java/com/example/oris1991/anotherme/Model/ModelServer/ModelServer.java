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

import java.io.IOException;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by eldar on 31/05/2016.
 */
public class ModelServer implements ModelInterface {
    public static final String url= "http://192.168.0.113:8080/Another-Me";
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
//               if ((sp.getType()).equals("SMS"))
//                   taskModelServer.addPopUp(sp.getText(),false,sp.getSendto(),);
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

//        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//        Format format = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//        Date start = new Date(task.getStartTime());
//        Date end = new Date(task.getEndTime());
//
//        String i = format.format(start);
//        String j = format.format(end);
//
//
//        Date startDate = null;
//        Date endDate = null;
//        try {
//            startDate = df.parse(i);
//            endDate = df.parse(j);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        if(task.getSolution()==null)
        taskModelServer.addNewTask
                (Model.instance().getUser().getPersonId(),task.getTitle(),new Date(task.getStartTime()) ,new Date(task.getEndTime()),null,7,null,null,null,1);
else taskModelServer.addNewTask
                (Model.instance().getUser().getPersonId(),task.getTitle(),new Date(task.getStartTime()) ,new Date(task.getEndTime()),task.getSolution().getSms().getSendtoName(),7,String.valueOf(task.getSolution().getIdSolution()),(double)task.getSolution().getPopUp().getId(),(double)task.getSolution().getSms().getId(),1);


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
        return null;
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
}
