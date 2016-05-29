package com.example.oris1991.anotherme.Model;

/**
 * Created by oris1991 on 29/05/2016.
 */
public class Gps {
    String latitude;
    String longitude;

    public Gps(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
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
