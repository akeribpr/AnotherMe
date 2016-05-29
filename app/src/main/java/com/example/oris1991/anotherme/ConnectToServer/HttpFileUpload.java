package com.example.oris1991.anotherme.ConnectToServer;

import android.view.MenuItem;
import android.widget.EditText;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Itzik on 22/05/2016.
 */
public class HttpFileUpload {

    private MenuItem item;
    private String url = "http://192.168.1.5:8080/Another-Me/PostPersonServlet";
    private String user;
    private String password;
    public HttpFileUpload(String url) {
        this.url = url;
    }


//    public void getParameters(String user,String password){
//        String[] params = new String[]{url, user,password};
//        SendHttpRequestTask t = new SendHttpRequestTask();
//        t.execute(params);
//    }
//    public void connectForMultipart() throws Exception {
//        con = (HttpURLConnection) ( new URL(url)).openConnection();
//        con.setRequestMethod("POST");
//        con.setDoInput(true);
//        con.setDoOutput(true);
//        con.setRequestProperty("Connection", "Keep-Alive");
//        //con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
//        con.connect();
//        os = con.getOutputStream();
//    }

    private void writeParamData(String paramName, String value) throws Exception {


//        //os.write( (delimiter + boundary + "\r\n").getBytes());
//        os.write( "Content-Type: text/plain\r\n".getBytes());
//        os.write( ("Content-Disposition: form-data; name=\"" + paramName + "\"\r\n").getBytes());;
//        os.write( ("\r\n" + value + "\r\n").getBytes());


    }
}
