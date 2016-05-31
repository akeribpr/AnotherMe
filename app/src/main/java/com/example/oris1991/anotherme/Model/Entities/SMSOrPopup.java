package com.example.oris1991.anotherme.Model.Entities;

/**
 * Created by oris1991 on 19/05/2016.
 */
public class SMSOrPopup {


    int id;
    String type;
    String sendto;
    String sendtoName;
    String time;
    String text;

    public String getSendtoName() {
        return sendtoName;
    }

    public void setSendtoName(String sendtoName) {
        this.sendtoName = sendtoName;
    }

    public SMSOrPopup(int id,String type, String sendto, String sendtoName, String time, String text) {
        this.id = id;
        this.type = type;
        this.sendto = sendto;
        this.time = time;
        this.text = text;
        this.sendtoName=sendtoName;


    }

    public void setText(String text) {

        this.text = text;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSendto(String sendto) {
        this.sendto = sendto;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {

        return type;
    }

    public String getSendto() {
        return sendto;
    }

    public String getTime() {
        return time;
    }

    public String getText() {
        return text;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
