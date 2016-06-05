package com.example.oris1991.anotherme.Model.Entities;

/**
 * Created by Itzik on 31/05/2016.
 */
public class SharePictureOrText {

    int id;
    String picName;
    String shardWtith;
    String text;
    Boolean send;

    public SharePictureOrText( int id, String picName, String shardWtith, String text, Boolean send){

        this.id =id;
        this.picName=picName;
        this.shardWtith=shardWtith;
        this.text=text;
        this.send=send;
    }

    public String getShardWtith() {
        return shardWtith;
    }

    public void setShardWtith(String shardWtith) {
        this.shardWtith = shardWtith;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getSend() {
        return send;
    }

    public void setSend(Boolean send) {
        this.send = send;
    }




}
