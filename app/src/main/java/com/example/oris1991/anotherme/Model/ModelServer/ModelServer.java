package com.example.oris1991.anotherme.Model.ModelServer;

import com.example.oris1991.anotherme.Model.ModelInterface;
import com.example.oris1991.anotherme.Model.Entities.SMSOrPopup;

import java.util.Date;
import java.util.List;

/**
 * Created by eldar on 31/05/2016.
 */
public class ModelServer implements ModelInterface {

    PersonModelServer personModelServer;

    public void addNewUser(String personId, String password,
                           Date DateTimeRegister, String mail, String phoneNumber){
    personModelServer.addNewUser(personId,password,DateTimeRegister,mail,phoneNumber);


    }
    @Override
    public void add(SMSOrPopup sp) {

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
}
