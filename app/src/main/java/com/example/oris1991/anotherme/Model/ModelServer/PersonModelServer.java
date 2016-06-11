package com.example.oris1991.anotherme.Model.ModelServer;
/*
 * Copyright (C) 2013 Surviving with Android (http://www.survivingwithandroid.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;


import android.os.AsyncTask;
import android.util.Log;

import com.example.oris1991.anotherme.Model.ModelServer.person.ServerPerson;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by eldar on 31/05/2016.
 */
public class PersonModelServer {

    private String urlPerson = "/PersonServlet";
    String result;


    public Boolean register(String personId, String password,
                           Date DateTimeRegister, String mail, String phoneNumber) throws IOException {
//        (DateTimeRegister.toString())==null?null:DateTimeRegister.toString()
        String[] params = new String[]{ModelServer.url+urlPerson,  personId,  password,
                null,  mail,  phoneNumber};
        new AsyncTask<String, Void, String>(){

            @Override
            protected String doInBackground(String... params) {
                StringBuffer buffer = new StringBuffer();
                try {
                    //System.out.println("URL ["+url+"] - Name ["+name+"]");

                    HttpURLConnection con = (HttpURLConnection) ( new URL(params[0])).openConnection();
                    con.setRequestProperty("connection", "close");
                    con.setRequestMethod("POST");
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    con.connect();

                    OutputStream os= con.getOutputStream();
                    os.write( ("personId=" + params[1]).getBytes("UTF-8"));
                    os.write( ("&password=" + params[2]).getBytes("UTF-8"));
                    os.write( ("&DateTimeRegister=" + params[3]).getBytes("UTF-8"));
                    os.write( ("&mail=" + params[4]).getBytes("UTF-8"));
                    os.write( ("&phoneNumber=" + params[5]).getBytes("UTF-8"));
                    os.flush();
                   os.close();
                    // 1. get received JSON data from request
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    if(br != null){
                        result = br.readLine();

                    }
                    else
                        result = "Did not work!(get person)";

                    //InputStream is = con.getInputStream();
//                  byte[] b = new byte[1024];
//
//                 while (is.read(b) != -1)
//                buffer.append(new String(b));

                    con.disconnect();
                }
                catch(Throwable t) {
                    t.printStackTrace();
                }

                return result;
            }


        }.execute(params);

// 2. initiate jackson mapper
       // ObjectMapper mapper = new ObjectMapper();
        // 3. Convert received JSON to Article
        System.out.println(result);
       // System.out.println(mapper.readValue(result, Boolean.class));
        return Boolean.valueOf(result);
    }

    public ServerPerson getUser(String personId) throws IOException {
        String[] params = new String[]{ModelServer.url+urlPerson,personId};
        new AsyncTask<String, Void, String>(){

            @Override
            protected String doInBackground(String... params) {
                StringBuffer buffer = new StringBuffer();
                try {
                    //System.out.println("URL ["+url+"] - Name ["+name+"]");

                    HttpURLConnection con = (HttpURLConnection) ( new URL(params[0])).openConnection();
                    con.setRequestMethod("POST");
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    con.connect();
                    con.getOutputStream().write( ("personId=" + params[1]).getBytes());

                    // 1. get received JSON data from request
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    if(br != null){
                        result = br.readLine();
                    }
                    else
                        result = "Did not work!(get person)";
                    con.disconnect();

                }
                catch(Throwable t) {
                    t.printStackTrace();
                    return null;
                }

                return result;
            }

            // onPostExecute displays the results of the AsyncTask.
            @Override
            protected void onPostExecute(String result) {
                Log.d("s",result);
                PersonModelServer.this.result=result;
            }
        }.execute(params);


        // 2. initiate jackson mapper
        ObjectMapper mapper = new ObjectMapper();
        // 3. Convert received JSON to Article
        return mapper.readValue(result, ServerPerson.class);

    }

    public Boolean checkIfPersonExists(String personId) throws IOException {
        String[] params = new String[]{ModelServer.url+urlPerson,personId};
        new AsyncTask<String, Void, String>(){

            @Override
            protected String doInBackground(String... params) {
                StringBuffer buffer = new StringBuffer();
                try {
                    //System.out.println("URL ["+url+"] - Name ["+name+"]");

                    HttpURLConnection con = (HttpURLConnection) ( new URL(params[0])).openConnection();
                    con.setRequestMethod("POST");
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    con.connect();
                    con.getOutputStream().write(("personId=" + params[1]).getBytes());

                    // 1. get received JSON data from request
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    if(br != null){
                        result = br.readLine();
                    }
                    else
                        result = "Did not work!(get person)";
                    con.disconnect();

                }
                catch(Throwable t) {
                    t.printStackTrace();
                    return null;
                }

                return result;
            }

            // onPostExecute displays the results of the AsyncTask.
            @Override
            protected void onPostExecute(String result) {
                Log.d("s",result);
                PersonModelServer.this.result=result;
            }
        }.execute(params);


        // 2. initiate jackson mapper
        ObjectMapper mapper = new ObjectMapper();
        // 3. Convert received JSON to Article
        return mapper.readValue(result, Boolean.class);
    }


}

