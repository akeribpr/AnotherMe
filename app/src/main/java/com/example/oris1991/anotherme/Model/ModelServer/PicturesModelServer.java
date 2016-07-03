package com.example.oris1991.anotherme.Model.ModelServer;

import android.os.AsyncTask;
import android.util.Log;

import com.example.oris1991.anotherme.Model.Entities.SMSOrPopup;
import com.example.oris1991.anotherme.Model.Entities.SharePictureOrText;
import com.example.oris1991.anotherme.Model.Entities.Solution;
import com.example.oris1991.anotherme.Model.Entities.Task;
import com.example.oris1991.anotherme.Model.ModelServer.Task.ServerTask;
import com.example.oris1991.anotherme.Model.ModelServer.person.ServerPerson;
import com.example.oris1991.anotherme.Model.ModelServer.pictures.ServerPictures;
import com.example.oris1991.anotherme.Model.ModelServer.pictures.ServerSharePictures;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by itzik on 12/06/2016.
 */
public class PicturesModelServer {

    private String urlAddPicture = "/AddPicture";
    private String urlAddSharePictures = "/AddPictureToShare";
    private String urlGetPictures = "/GetPictures";
    private String urlGetUpdate = "/GetShareToDo";



    String result;

    public void addPictures(String pictureName, String personId, Date datePic) {

        String[] params = new String[]{ModelServer.url + urlAddPicture, pictureName, personId,
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


    public void addPicturesToShare(String pictureName, String personId, String personToSend, String txt) {
        String[] params = new String[]{ModelServer.url + urlAddSharePictures, pictureName, personId
                , personToSend, txt};
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
//                    con.getOutputStream().write(("&dateTime=" + params[3]).getBytes());
                    con.getOutputStream().write(("&sendTo=" + params[3]).getBytes());
                    con.getOutputStream().write(("&txt=" + params[4]).getBytes());


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


    public ArrayList<SharePictureOrText> getSharedPictures(String personId) {
        String[] params = new String[]{ModelServer.url + urlGetUpdate, personId};
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

                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                            sb.append(line);
                        }
                        result = sb.toString();
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
                if (result!=null&&result.isEmpty()) {
                    Log.d("SharePictureOrText", result);
                    PicturesModelServer.this.result = result;
                }
            }
        }.execute(params);


        // 2. initiate jackson mapper
        ObjectMapper mapper = new ObjectMapper();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 3. Convert received JSON to Article
        try {
            if (result.equals("null")) {
                return null;
            } else {
                List<ClientShare> myObjects =  mapper.readValue(result, new TypeReference<List<ClientShare>>(){});
                ArrayList<SharePictureOrText> share = new ArrayList<SharePictureOrText>();
                for (int i = 0;i<myObjects.size();i++){
                 SharePictureOrText s = new SharePictureOrText(1,myObjects.get(i).pictureName,myObjects.get(i).getWithPerson(),myObjects.get(i).getTxt(),true);
                    share.add(s);
                }

                Log.d("Get", "Array list");
                return share;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

        public static class ClientShare{
            public String pictureName;
            public String person;
            public String withPerson;
            public String txt;

            public ClientShare() {
                // TODO Auto-generated constructor stub
            }
            public String getPictureName() {
                return pictureName;
            }
            public void setPictureName(String pictureName) {
                this.pictureName = pictureName;
            }
            public String getPerson() {
                return person;
            }
            public void setPerson(String person) {
                this.person = person;
            }

            public String getWithPerson() {
                return withPerson;
            }
            public void setWithPerson(String withPerson) {
                this.withPerson = withPerson;
            }
            public String getTxt() {
                return txt;
            }
            public void setTxt(String txt) {
                this.txt = txt;
            }

        }
//    public ArrayList<ServerPictures> getPictures(String personId){
//        String[] params = new String[]{ModelServer.url+urlGetPictures, personId};
//
//        new AsyncTask<String, Void, String>() {
//
//            @Override
//            protected String doInBackground(String... params) {
//                StringBuffer buffer = new StringBuffer();
//                try {
//                    //System.out.println("URL ["+url+"] - Name ["+name+"]");
//
//                    HttpURLConnection con = (HttpURLConnection) (new URL(params[0])).openConnection();
//                    con.setRequestMethod("POST");
//                    con.setDoInput(true);
//                    con.setDoOutput(true);
//                    con.connect();
//                    con.getOutputStream().write(("name=" + params[1]).getBytes());
//
//                    // 1. get received JSON data from request
//                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                    if (br != null) {
//                        result = br.readLine();
//                    } else
//                        result = "Did not work!(getPictures)";
//                    con.disconnect();
//
//                } catch (Throwable t) {
//                    t.printStackTrace();
//                    return null;
//                }
//
//                return result;
//            }
//
//            // onPostExecute displays the results of the AsyncTask.
//            @Override
//            protected void onPostExecute(String result) {
//                Log.d("s", result);
//                PicturesModelServer.this.result = result;
//            }
//        }.execute(params);
//
//
//        // 2. initiate jackson mapper
//        ObjectMapper mapper = new ObjectMapper();
//
//        // 3. Convert received JSON to Article
//        try {
//            return mapper.readValue(result, new ArrayList<ServerTask>().getClass());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
////        return mapper.readValue(result, new TypeReference<ArrayList<ServerTask>>(){});
//   return null;
//    }
}
