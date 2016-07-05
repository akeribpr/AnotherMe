package com.example.oris1991.anotherme.Model.ModelServer.Task;

import com.example.oris1991.anotherme.Model.ModelServer.person.ServerPerson;

import java.io.Serializable;
import java.util.Date;


public class ServerPopUp implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Double idPopUp;
    String text;
    boolean popUpTamplates;
    Date DateTimeShow; // if it show if not ->null
    ServerPerson senderId;// send popUp to other user
    ServerPerson serverPersonId;

    public ServerPopUp(Double idPopUp, String text, boolean popUpTamplates, ServerPerson senderId,
                       ServerPerson serverPersonId) {
        this.idPopUp = idPopUp;
        this.text = text;
        this.popUpTamplates = popUpTamplates;
        this.serverPersonId = serverPersonId;
        this.senderId = senderId;
    }

    public void setPerson(ServerPerson serverPerson) {
        this.serverPersonId = serverPerson;
        serverPerson.addpopUp(this);
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

    public ServerPerson getServerPersonId() {
        return serverPersonId;
    }

    public void setServerPersonId(ServerPerson serverPersonId) {
        this.serverPersonId = serverPersonId;
    }

}
