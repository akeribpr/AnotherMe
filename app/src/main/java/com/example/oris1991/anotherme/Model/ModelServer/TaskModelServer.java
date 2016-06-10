package com.example.oris1991.anotherme.Model.ModelServer;

import android.os.AsyncTask;
import android.util.Log;

import com.example.oris1991.anotherme.Model.ModelServer.Task.Task;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by eldar on 06/06/2016.
 */
public class TaskModelServer {
    private String url = "http://192.168.2.111:8080/Another-Me/Task.jsp";
    String result;


    public void addNewTask(String personId, String taskText,
                           Date start, Date end, String address, int platform, String withPerson, Double popUp, Double sms, int action) {

        String[] params = new String[]{url, personId, taskText,
                start.toString(), end.toString(), address, String.valueOf(platform), withPerson, String.valueOf(popUp), String.valueOf(sms)
                , String.valueOf(action)};
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {
                StringBuffer buffer = new StringBuffer();
                try {
                    //System.out.println("URL ["+url+"] - Name ["+name+"]");

                    HttpURLConnection con = (HttpURLConnection) (new URL(url)).openConnection();
                    con.setRequestMethod("POST");
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    con.connect();
                    con.getOutputStream().write(("personId=" + params[1]).getBytes());
                    con.getOutputStream().write(("&taskText=" + params[2]).getBytes());
                    con.getOutputStream().write(("&start=" + params[3]).getBytes());
                    con.getOutputStream().write(("&end=" + params[4]).getBytes());
                    con.getOutputStream().write(("&address=" + params[5]).getBytes());
                    con.getOutputStream().write(("&platform=" + params[6]).getBytes());
                    con.getOutputStream().write(("&withPerson=" + params[7]).getBytes());
                    con.getOutputStream().write(("&popUp=" + params[8]).getBytes());
                    con.getOutputStream().write(("&sms=" + params[9]).getBytes());
                    con.getOutputStream().write(("&action=" + params[10]).getBytes());

//                  InputStream is = con.getInputStream();
//                  byte[] b = new byte[1024];
//
//                 while (is.read(b) != -1)
//                buffer.append(new String(b));

                    con.disconnect();
                } catch (Throwable t) {
                    t.printStackTrace();
                }

                return null;
            }


        }.execute(params);


    }

    public Task getTask(Date date, String personId) throws IOException {
        String[] params = new String[]{url, date.toString(), personId};
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {
                StringBuffer buffer = new StringBuffer();
                try {
                    //System.out.println("URL ["+url+"] - Name ["+name+"]");

                    HttpURLConnection con = (HttpURLConnection) (new URL(url)).openConnection();
                    con.setRequestMethod("POST");
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    con.connect();
                    con.getOutputStream().write(("date=" + params[1]).getBytes());
                    con.getOutputStream().write(("&personId=" + params[2]).getBytes());

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
                Log.d("s", result);
                TaskModelServer.this.result = result;
            }
        }.execute(params);


        // 2. initiate jackson mapper
        ObjectMapper mapper = new ObjectMapper();
        // 3. Convert received JSON to Article
        return mapper.readValue(result, Task.class);
    }

    public ArrayList<Task> getAllTaskFromView(String personId) throws IOException {
        String[] params = new String[]{url, personId};
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {
                StringBuffer buffer = new StringBuffer();
                try {
                    //System.out.println("URL ["+url+"] - Name ["+name+"]");

                    HttpURLConnection con = (HttpURLConnection) (new URL(url)).openConnection();
                    con.setRequestMethod("POST");
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    con.connect();
                    con.getOutputStream().write(("personId=" + params[1]).getBytes());

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
                Log.d("s", result);
                TaskModelServer.this.result = result;
            }
        }.execute(params);


        // 2. initiate jackson mapper
        ObjectMapper mapper = new ObjectMapper();

        // 3. Convert received JSON to Article
        return mapper.readValue(result, new ArrayList<Task>().getClass());
//        return mapper.readValue(result, new TypeReference<ArrayList<Task>>(){});
    }


    public void addPopUpToDefault(String text, boolean popUpTamplates, String senderId,String personId){
        String[] params = new String[]{url, text, String.valueOf(popUpTamplates),
                senderId, personId};
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {
                StringBuffer buffer = new StringBuffer();
                try {
                    //System.out.println("URL ["+url+"] - Name ["+name+"]");

                    HttpURLConnection con = (HttpURLConnection) (new URL(url)).openConnection();
                    con.setRequestMethod("POST");
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    con.connect();
                    con.getOutputStream().write(("text=" + params[1]).getBytes());
                    con.getOutputStream().write(("&popUpTamplates=" + params[2]).getBytes());
                    con.getOutputStream().write(("&senderId=" + params[3]).getBytes());
                    con.getOutputStream().write(("&personId=" + params[4]).getBytes());


                    con.disconnect();
                } catch (Throwable t) {
                    t.printStackTrace();
                }

                return null;
            }


        }.execute(params);

    }


   // GetTasksToDo

//    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
//        String line = "";
//        String result = "";
//        while((line = bufferedReader.readLine()) != null)
//            result += line;
//
//        inputStream.close();
//        return result;
//
//    }


}
