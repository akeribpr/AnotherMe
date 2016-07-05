package com.example.oris1991.anotherme.Model.ModelServer;

import android.os.AsyncTask;

import com.example.oris1991.anotherme.Model.ModelServer.GPS.ServerGps;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by itzik on 11/06/2016.
 */
public class GpsModelServer {

    private String urlGps = "/AddGps";
    String result;


    public void addNewGpsLocation(ServerGps gps) {
        String[] params = new String[]{ModelServer.url + urlGps, String.valueOf(gps.getX()), String.valueOf(gps.getY()),
                gps.getGpsDate().toString(), gps.getPerson().getPersonId()};
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {
                StringBuffer buffer = new StringBuffer();
                try {

                    HttpURLConnection con = (HttpURLConnection) (new URL(params[0])).openConnection();
                    con.setRequestProperty("connection", "close");
                    con.setRequestMethod("POST");
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    con.connect();

                    OutputStream os = con.getOutputStream();
                    os.write(("X=" + params[1]).getBytes("UTF-8"));
                    os.write(("&Y=" + params[2]).getBytes("UTF-8"));
                    os.write(("&gpsDate=" + params[3]).getBytes("UTF-8"));
                    os.write(("&personId=" + params[4]).getBytes("UTF-8"));
                    os.flush();
                    os.close();
                    // 1. get received JSON data from request
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    if (br != null) {
                        result = br.readLine();

                    } else
                        result = "return nothing";

                    con.disconnect();
                } catch (Throwable t) {
                    t.printStackTrace();
                }

                return result;
            }


        }.execute(params);

// 2. initiate jackson mapper
        // ObjectMapper mapper = new ObjectMapper();
        // 3. Convert received JSON to Article
        // System.out.println(mapper.readValue(result, Boolean.class));


    }


}
