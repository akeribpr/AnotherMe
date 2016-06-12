package com.example.oris1991.anotherme.Model;

import com.example.oris1991.anotherme.Model.Entities.LogIn;
import com.example.oris1991.anotherme.Model.Entities.SMSOrPopup;
import com.example.oris1991.anotherme.Model.Entities.Solution;
import com.example.oris1991.anotherme.Model.Entities.SharePictureOrText;
import com.example.oris1991.anotherme.Model.Entities.Task;
import com.example.oris1991.anotherme.Model.Entities.Users;
import com.example.oris1991.anotherme.Model.ModelServer.GPS.ServerGps;
import com.example.oris1991.anotherme.Model.ModelServer.ModelServer;
import com.example.oris1991.anotherme.Model.sqlLite.*;

import java.util.List;

/**
 * Created by eliav.menachi on 16/03/2016.
 */
public class Model implements ModelInterface {
    private final static Model instance = new Model();
//ascacc
    ModelSql sqlModel;
    //ModelServer modelServe;

    private Model() {
        sqlModel = new ModelSql();
       // modelServe = new ModelServer();
    }


    public static Model instance() {
        return instance;
    }

    public Boolean checkIfUserExist(LogIn logIn){
        return sqlModel.checkIfUserExist(logIn);
    }

    public void addSmsOrPop(SMSOrPopup sp) {
        sqlModel.addSmsOrPop(sp);
    }

    public void loginUser(LogIn logIn) {
        sqlModel.register(logIn);
    }

    public  void register(LogIn login){
        sqlModel.register(login);
     //   modelServe.register(login);

    }

    public LogIn getUser(){
      return  sqlModel.getUser();
    }

    public List<SMSOrPopup> getSmsForPerson(String person) {

        return sqlModel.getSmsForPerson(person);
    }

    public List<SMSOrPopup> getSmsOrPopups() {

        return sqlModel.getSmsOrPopups();
    }

    public List<SMSOrPopup> getPopupsTemplates() {

        return sqlModel.getPopupsTemplates();
    }

    public List<SMSOrPopup> getSmsTemplates() {

        return sqlModel.getSmsTemplates();
    }

    @Override
    public List<SMSOrPopup> getSmsTemplatesWithoutPerson() {
        return sqlModel.getSmsTemplatesWithoutPerson();
    }

    @Override
    public SMSOrPopup getSmsOrPopupById(int id) {
        return sqlModel.getSmsOrPopupById(id);
    }

    public void addSolution(Solution sol) {

         sqlModel.addSolution(sol);
    }

    public List<Task> getTasks() {

        return sqlModel.getTasks();
    }

    @Override
    public List<Task> checkUpdateTask() {
        return null;
    }

    @Override
    public void addTask(Task task) {
        sqlModel.addTask(task);


    }

    @Override
    public void deleteTask(int id) {
        sqlModel.deleteTask(id);
    }

    @Override
    public void addNewGpsLocation(ServerGps gps) {

    }

    @Override
    public void addPic(SharePictureOrText sp) {
        sqlModel.addPic(sp);
    }

    @Override
    public void addUser(Users sp) {
        sqlModel.addUser(sp);
    }

    @Override
    public List<SharePictureOrText> getPicture() {
        return sqlModel.getPicture();
    }

    @Override
    public List<Users> getUsers() {
        return sqlModel.getUsers();
    }

    @Override
    public Boolean deleteUser(Users sp) {
        return sqlModel.deleteUser(sp);
    }

    @Override
    public Solution getSolution(int solid) {
        return sqlModel.getSolution(solid);
    }

    @Override
    public void deleteSolution(int id) {
        sqlModel.deleteSolution(id);
    }

    @Override
    public  List<SharePictureOrText>  checkUpdateShare() {
     return null;
    }


    @Override
    public void addTaskWithSolution(Task task) {
        sqlModel.addTaskWithSolution(task);

       // modelServe.addTask(task);

     //   modelServe.addTask(task);

    }

    public  int numberOfRow( ){

        return sqlModel.numberOfRow();
    }


   /* public void delete(Student st) {
        sqlModel.delete(st);
    }*/

    /*public void edit(String stId , Student st) {
        sqlModel.edit(stId,st);
    }*/
}
