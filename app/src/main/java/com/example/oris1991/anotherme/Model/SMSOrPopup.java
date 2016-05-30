package com.example.oris1991.anotherme.Model;

/**
 * Created by oris1991 on 19/05/2016.
 */
public class SMSOrPopup {
    String type;
    String sendto;
    String time;
    String text;

    public SMSOrPopup(String type, String sendto, String time, String text) {
        this.type = type;
        this.sendto = sendto;
        this.time = time;
        this.text = text;
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
}
