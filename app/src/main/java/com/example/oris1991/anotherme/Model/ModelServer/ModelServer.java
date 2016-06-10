package com.example.oris1991.anotherme.Model.ModelServer;

import com.example.oris1991.anotherme.Model.Entities.SharePictureOrText;
import com.example.oris1991.anotherme.Model.Entities.Solution;
import com.example.oris1991.anotherme.Model.Entities.Task;
import com.example.oris1991.anotherme.Model.Entities.Users;
import com.example.oris1991.anotherme.Model.Model;
import com.example.oris1991.anotherme.Model.ModelInterface;
import com.example.oris1991.anotherme.Model.Entities.SMSOrPopup;

import java.util.Date;
import java.util.List;

/**
 * Created by eldar on 31/05/2016.
 */
public class ModelServer implements ModelInterface {

    PersonModelServer personModelServer;
    TaskModelServer taskModelServer;

    public  ModelServer(){
        taskModelServer = new TaskModelServer();
        personModelServer = new PersonModelServer();
    }

//    String personId = Model.instance().getUser().getPersonId();
    public void addNewUser(String personId, String password,
                           Date DateTimeRegister, String mail, String phoneNumber){
    personModelServer.addNewUser(personId,password,DateTimeRegister,mail,phoneNumber);

    }

    @Override
    public void addSmsOrPop(SMSOrPopup sp) {

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
        Date start=new Date(task.getStartTime());
        Date end=new Date(task.getEndTime());

        taskModelServer.addNewTask("itzik",task.getTitle(),start,end,"ראשון לציון ",7,"nivi",1.0,1.0,2);

    }

    @Override
    public void deleteTask(int id) {

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
    public void addTaskWithSolution(Task task) {

    }

    @Override
    public List<Task> getTasks() {
        return null;
    }
}
