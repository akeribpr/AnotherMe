package com.example.oris1991.anotherme.ConnectToServer;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by Itzik on 29/05/2016.
 */

public class LogInServer extends Fragment {

    private String url = "http://192.168.1.5:8080/Another-Me/Login";
    private String user = "itzik";
    private String password = "123456";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


                SendHttpRequestTask t = new SendHttpRequestTask();

                String[] params = new String[]{url, user,password};
                t.execute(params);

        };

    private class SendHttpRequestTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            String personId = params[1];
            String password=params[2];
            String data = sendHttpRequest(url, new String[]{personId,password});
            //System.out.println("Data ["+data+"]");
            return data;
        }
    }

    private String sendHttpRequest(String url, String ... data) {
        StringBuffer buffer = new StringBuffer();
        try {
            //System.out.println("URL ["+url+"] - Name ["+name+"]");

            HttpURLConnection con = (HttpURLConnection) ( new URL(url)).openConnection();
            con.setRequestMethod("POST");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();
            con.getOutputStream().write( ("name="+ data[0]).getBytes());
            con.getOutputStream().write(("&password="+ data[1]).getBytes());

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
