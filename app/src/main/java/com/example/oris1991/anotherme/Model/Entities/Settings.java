package com.example.oris1991.anotherme.Model.Entities;

import java.util.Date;

/**
 * Created by Itzik on 29/05/2016.
 */
public class Settings {
    String personId;
    String fullName;
    String phoneNumber;
    int age;
    String password;
    String DateTimeRegister;
    String mail;
    Boolean PopUps = true;
    Boolean Sms = false;
    Boolean Solution = true;
    Boolean gps = true;

    public Settings(String personId, String fullName, String phoneNumber, int age, String password, String DateTimeRegister, String mail, Boolean PopUps, Boolean Sms,
                    Boolean Solution,Boolean gps) {
        this.personId=personId;
        this.fullName=fullName;
        this.phoneNumber=phoneNumber;
        this.age=age;
        this.password=password;
        this.DateTimeRegister = DateTimeRegister;
        this.mail=mail;
        this.PopUps=PopUps;
        this.Sms = Sms;
        this.Solution=Solution;
        this.gps = gps;
    }
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateTimeRegister() {
        return DateTimeRegister;
    }

    public void setDateTimeRegister(String dateTimeRegister) {
        DateTimeRegister = dateTimeRegister;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Boolean getSms() {
        return Sms;
    }

    public void setSms(Boolean sms) {
        Sms = sms;
    }

    public Boolean getGps() {
        return gps;
    }

    public void setGps(Boolean gps) {
        this.gps = gps;
    }

    public Boolean getSolution() {
        return Solution;
    }

    public void setSolution(Boolean solution) {
        Solution = solution;
    }

    public Boolean getPopUps() {
        return PopUps;
    }

    public void setPopUps(Boolean popUps) {
        PopUps = popUps;
    }


}
