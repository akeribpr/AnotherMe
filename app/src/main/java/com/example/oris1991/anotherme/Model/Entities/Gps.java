package com.example.oris1991.anotherme.Model.Entities;

/**
 * Created by oris1991 on 29/05/2016.
 */
public class Gps {
    String latitude;
    String longitude;
    String time;

    public Gps(String time, String longitude, String latitude) {
        this.time = time;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLatitude() {

        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
