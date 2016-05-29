package com.example.oris1991.anotherme.sqlLite;

import com.example.oris1991.anotherme.Model.LogIn;
import com.example.oris1991.anotherme.Model.SMSOrPopup;

import java.util.List;

/**
 * Created by eliav.menachi on 16/03/2016.
 */
public class Model {
    private final static Model instance = new Model();

    ModelSql sqlModel;

    private Model() {
        sqlModel = new ModelSql();
    }

    public static Model instance() {
        return instance;
    }

    public void add(SMSOrPopup sp) {
        sqlModel.add(sp);
    }

    public void add(LogIn logIn) {
        sqlModel.register(logIn);
    }

    public LogIn getUser(){
      return  sqlModel.checkLogIn();
    }
    public List<SMSOrPopup> getSmsForPerson(String person) {

        return sqlModel.getSmsForPerson( person);
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

   /* public void delete(Student st) {
        sqlModel.delete(st);
    }*/

    /*public void edit(String stId , Student st) {
        sqlModel.edit(stId,st);
    }*/
}
