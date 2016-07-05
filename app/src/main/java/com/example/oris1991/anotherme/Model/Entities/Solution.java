package com.example.oris1991.anotherme.Model.Entities;

/**
 * Created by Itzik on 29/05/2016.
 */
public class Solution {

    int idSolution;
    SMSOrPopup sms;
    SMSOrPopup popUp;
    int whatToDo;

    public Solution(int idSolution, SMSOrPopup sms, SMSOrPopup popUp, int whatToDo) {
        this.idSolution = idSolution;
        this.sms = sms;
        this.popUp = popUp;
        this.whatToDo = whatToDo;

    }

    public int getIdSolution() {
        return idSolution;
    }

    public void setIdSolution(int idSolution) {
        this.idSolution = idSolution;
    }

    public SMSOrPopup getSms() {
        return sms;
    }

    public void setSms(SMSOrPopup sms) {
        this.sms = sms;
    }


    public int getWhatToDo() {
        return whatToDo;
    }

    public void setWhatToDo(int whatToDo) {
        this.whatToDo = whatToDo;
    }

    public int getTimeToArriving() {
        return timeToArriving;
    }

    public void setTimeToArriving(int timeToArriving) {
        this.timeToArriving = timeToArriving;
    }

    public SMSOrPopup getPopUp() {
        return popUp;
    }

    public void setPopup(SMSOrPopup popUp) {
        popUp = popUp;
    }

    int timeToArriving;
}
