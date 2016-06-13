package com.example.oris1991.anotherme.Model.ModelServer;

import android.os.AsyncTask;
import android.util.Log;

import com.example.oris1991.anotherme.Model.ModelServer.Task.ServerTask;
import com.example.oris1991.anotherme.Model.ModelServer.pictures.ServerPictures;
import com.example.oris1991.anotherme.Model.ModelServer.pictures.ServerSharePictures;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by eldar on 12/06/2016.
 */
public class PicturesModelServer {

    private String urlAddPicture = "/AddPicture";
    private String urlSharePictures = "/GetShareToDo";
    private String urlGetPictures = "/GetPictures";


    String result;

    public void addPictures(String pictureName, String personId, Date datePic){

        String[] params = new String[]{ModelServer.url+urlAddPicture, pictureName, personId,
                datePic.toString()};
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {
                StringBuffer buffer = new StringBuffer();
                try {
                    //System.out.println("URL ["+url+"] - Name ["+name+"]");

                    HttpURLConnection con = (HttpURLConnection) (new URL(params[0])).openConnection();
                    con.setRequestMethod("POST");
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    con.connect();
                    con.getOutputStream().write(("pictureId=" + params[1]).getBytes());
                    con.getOutputStream().write(("&personId=" + params[2]).getBytes());

                    // 1. get received JSON data from request
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    if (br != null) {
                        result = br.readLine();
                    } else
                        result = "Did not work!(add Picture)";
                    con.disconnect();
                } catch (Throwable t) {
                    t.printStackTrace();
                }

                return null;
            }


        }.execute(params);
    }


    public void addPicturesToShare(String pictureName, String personId, Date dateTime, String personToSend, String txt) {
        String[] params = new String[]{ModelServer.url+urlAddPicture, pictureName, personId,
                dateTime.toString(),personToSend,txt};
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {
                StringBuffer buffer = new StringBuffer();
                try {
                    //System.out.println("URL ["+url+"] - Name ["+name+"]");

                    HttpURLConnection con = (HttpURLConnection) (new URL(params[0])).openConnection();
                    con.setRequestMethod("POST");
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    con.connect();
                    con.getOutputStream().write(("pictureId=" + params[1]).getBytes());
                    con.getOutputStream().write(("&personId=" + params[2]).getBytes());
                    con.getOutputStream().write(("&dateTime=" + params[3]).getBytes());
                    con.getOutputStream().write(("&personToSend=" + params[4]).getBytes());
                    con.getOutputStream().write(("&txt=" + params[5]).getBytes());


                    // 1. get received JSON data from request
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    if (br != null) {
                        result = br.readLine();
                    } else
                        result = "Did not work!(addPicturesToShare)";
                    con.disconnect();
                } catch (Throwable t) {
                    t.printStackTrace();
                }

                return null;
            }


        }.execute(params);
    }

    public ArrayList<ServerSharePictures> getSharedPictures(String personId)  {
        String[] params = new String[]{ModelServer.url+urlSharePictures, personId};
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {
                StringBuffer buffer = new StringBuffer();
                try {
                    //System.out.println("URL ["+url+"] - Name ["+name+"]");

                    HttpURLConnection con = (HttpURLConnection) (new URL(params[0])).openConnection();
                    con.setRequestMethod("POST");
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    con.connect();
                    con.getOutputStream().write(("name=" + params[1]).getBytes());

                    // 1. get received JSON data from request
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    if (br != null) {
                        result = br.readLine();
                    } else
                        result = "Did not work!";
                    con.disconnect();

                } catch (Throwable t) {
                    t.printStackTrace();
                    return null;
                }

                return result;
            }

            // onPostExecute displays the results of the AsyncTask.
            @Override
            protected void onPostExecute(String result) {
                Log.d("SharePictureOrText", result);
                PicturesModelServer.this.result = result;
            }
        }.execute(params);


        // 2. initiate jackson mapper
        ObjectMapper mapper = new ObjectMapper();

        // 3. Convert received JSON to Article
        try {
            return mapper.readValue(result, new ArrayList<ServerSharePictures>().getClass());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
//        return mapper.readValue(result, new TypeReference<ArrayList<ServerTask>>(){});
    }



    public ArrayList<ServerPictures> getPictures(String personId){
        String[] params = new String[]{ModelServer.url+urlGetPictures, personId};

        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {
                StringBuffer buffer = new StringBuffer();
                try {
                    //System.out.println("URL ["+url+"] - Name ["+name+"]");

                    HttpURLConnection con = (HttpURLConnection) (new URL(params[0])).openConnection();
                    con.setRequestMethod("POST");
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    con.connect();
                    con.getOutputStream().write(("name=" + params[1]).getBytes());

                    // 1. get received JSON data from request
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    if (br != null) {
                        result = br.readLine();
                    } else
                        result = "Did not work!(getPictures)";
                    con.disconnect();

                } catch (Throwable t) {
                    t.printStackTrace();
                    return null;
                }

                return result;
            }

            // onPostExecute displays the results of the AsyncTask.
            @Override
            protected void onPostExecute(String result) {
                Log.d("s", result);
                PicturesModelServer.this.result = result;
            }
        }.execute(params);


        // 2. initiate jackson mapper
        ObjectMapper mapper = new ObjectMapper();

        // 3. Convert received JSON to Article
        try {
            return mapper.readValue(result, new ArrayList<ServerTask>().getClass());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return mapper.readValue(result, new TypeReference<ArrayList<ServerTask>>(){});
   return null;
    }
}
