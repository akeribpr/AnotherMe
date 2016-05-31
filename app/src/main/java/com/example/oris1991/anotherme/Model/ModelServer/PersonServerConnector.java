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

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;



import android.os.AsyncTask;

/**
 * Created by eldar on 31/05/2016.
 */
public class PersonServerConnector {

    private String url = "http://192.168.2.111:8080/Another-Me/Person.jsp";


    protected void onCreate() {
        SendHttpRequestTask t = new SendHttpRequestTask();

       // String[] params = new String[]{url, name};
        //t.execute(params);

    }
    private class SendHttpRequestTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            String name = params[1];

            String data = sendHttpRequest(url, name);
            System.out.println("Data ["+data+"]");
            return data;
        }

    }

    private String sendHttpRequest(String url, String name) {
        StringBuffer buffer = new StringBuffer();
        try {
            System.out.println("URL ["+url+"] - Name ["+name+"]");

            HttpURLConnection con = (HttpURLConnection) ( new URL(url)).openConnection();
            con.setRequestMethod("POST");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();
            con.getOutputStream().write( ("name=" + name).getBytes());

            InputStream is = con.getInputStream();
            byte[] b = new byte[1024];

            while ( is.read(b) != -1)
                buffer.append(new String(b));

            con.disconnect();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }

        return buffer.toString();
    }
}
