package com.example.oris1991.anotherme.Model;

/**
 * Created by Itzik on 29/05/2016.
 */
public class LogIn {
    String personId;
    String password;

    public  LogIn(String personId, String password){
        this.personId=personId;
        this.password=password;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}


