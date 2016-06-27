package com.example.oris1991.anotherme.Model.ModelServer.PopUp;

import com.example.oris1991.anotherme.Model.ModelServer.person.ServerPerson;

import java.util.Date;

/**
 * Created by Itzik on 27/06/2016.
 */

public class ServerPopUp {

    Double idPopUp;
    String text;
    boolean popUpTamplates;
    Date DateTimeShow; // if it show if not ->null
    ServerPerson senderId;// send popUp to other user
    ServerPerson personId;

    public ServerPopUp(Double idPopUp, String text, boolean popUpTamplates, ServerPerson senderId,
                       ServerPerson personId) {
        this.idPopUp = idPopUp;
        this.text = text;
        this.popUpTamplates = popUpTamplates;
        this.personId = personId;
        this.senderId=senderId;
    }

    public void setPerson(ServerPerson person) {
        this.personId = person;
    }

    public Double getIdPopUp() {
        return idPopUp;
    }

    public void setIdPopUp(Double idPopUp) {
        this.idPopUp = idPopUp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isPopUpTamplates() {
        return popUpTamplates;
    }

    public void setPopUpTamplates(boolean popUpTamplates) {
        this.popUpTamplates = popUpTamplates;
    }

    public Date getDateTimeShow() {
        return DateTimeShow;
    }

    public void setDateTimeShow(Date dateTimeShow) {
        DateTimeShow = dateTimeShow;
    }

    public ServerPerson getSenderId() {
        return senderId;
    }

    public void setSenderId(ServerPerson senderId) {
        this.senderId = senderId;
    }

    public ServerPerson getPersonId() {
        return personId;
    }

    public void setPersonId(ServerPerson personId) {
        this.personId = personId;
    }

}
