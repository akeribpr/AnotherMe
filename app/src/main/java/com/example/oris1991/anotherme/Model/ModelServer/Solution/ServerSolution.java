package com.example.oris1991.anotherme.Model.ModelServer.Solution;

import com.example.oris1991.anotherme.Model.Entities.Solution;
import com.example.oris1991.anotherme.Model.ModelServer.PopUp.ServerPopUp;
import com.example.oris1991.anotherme.Model.ModelServer.Task.ServerTask;
import com.example.oris1991.anotherme.Model.ModelServer.person.ServerPerson;
import com.example.oris1991.anotherme.Model.ModelServer.sms.ServerSMS;

import java.io.Serializable;
import java.util.Date;


public class ServerSolution implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // //string-> int
    public final static int Nothing = 1;
    public final static int Ask = 2;
    public final static int popUP = 3;
    public final static int Sms = 4;
    public final static int Ticket = 5;//movie
    public final static int Shoping = 6;//Shopping
    public final static int BabySiter = 7;//Babysitter
    public final static int Meeting = 8;//meeting
    public final static int TIME = 15;

    // /////////////////////////////
    Double idSolution;
    ServerTask serverTask;
    ServerSMS serverSms;
    Date toDo;
    ServerPopUp serverPopUp;
    ServerPerson serverPerson;
    int action;
    int timeToArriving = TIME;


    public ServerSolution(Double idSolution, ServerPerson serverPerson, ServerTask serverTask, ServerSMS serverSms,
                          ServerPopUp serverPopUp, int action) {
        this.idSolution = idSolution;
        this.serverPerson = serverPerson;
        this.serverTask = serverTask;
        this.serverSms = serverSms;
        this.serverPopUp = serverPopUp;
        this.action = action;
    }

    public void setServerPerson(ServerPerson serverPerson) {
        this.serverPerson = serverPerson;
        serverPerson.addSolution(this);
    }

    public Double getIdSolution() {
        return idSolution;
    }

    public void setIdSolution(Double idSolution) {
        this.idSolution = idSolution;
    }

    public ServerTask getServerTask() {
        return serverTask;
    }

    public void setServerTask(ServerTask serverTask) {
        this.serverTask = serverTask;
    }

    public ServerSMS getServerSms() {
        return serverSms;
    }

    public void setServerSms(ServerSMS serverSms) {
        this.serverSms = serverSms;
    }

    public Date getToDo() {
        return toDo;
    }

    public void setToDo(Date toDo) {
        this.toDo = toDo;
    }

    public ServerPopUp getServerPopUp() {
        return serverPopUp;
    }

    public void setServerPopUp(ServerPopUp serverPopUp) {
        this.serverPopUp = serverPopUp;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public ServerPerson getServerPerson() {
        return serverPerson;
    }

    public int getTimeToArriving() {
        return timeToArriving;
    }

    public void setTimeToArriving(int timeToArriving) {
        this.timeToArriving = timeToArriving;
    }

    public Solution convertServerSolution() {
        Solution solution = new Solution(1, getServerSms().convertServerSms(), getServerPopUp().convertServerPopUp(), getAction());

        return solution;
    }
}
