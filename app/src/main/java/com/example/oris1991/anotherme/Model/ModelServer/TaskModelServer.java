package com.example.oris1991.anotherme.Model.ModelServer;

import android.os.AsyncTask;
import android.util.Log;

import com.example.oris1991.anotherme.Model.Entities.SMSOrPopup;
import com.example.oris1991.anotherme.Model.Entities.SharePictureOrText;
import com.example.oris1991.anotherme.Model.ModelServer.Task.ServerPopUp;
import com.example.oris1991.anotherme.Model.ModelServer.Task.ServerTask;
import com.example.oris1991.anotherme.Model.ModelServer.pictures.ServerSharePictures;
import com.example.oris1991.anotherme.Model.ModelServer.sms.ServerSMS;
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
 * Created by itzik on 06/06/2016.
 */
public class TaskModelServer {
    private String urlTask = "/Task";
    private String urlTasksToDo = "/GetTasksToDo";
    private String urlGetTask = "/GetTask";
    private String urlAddSms = "/AddSms";
    private String urlAddPopUp = "/AddPopUp";

    String result;

    public void addNewTask(String personId, String taskText,
          Date start, Date end, String address, int platform, String withPerson, Double popUp, Double sms, int action) {

        String[] params = new String[]{ModelServer.url+urlTask, personId, taskText,
               String.valueOf(start.getTime()), String.valueOf(end.getTime()), address, String.valueOf(platform), withPerson, String.valueOf(popUp), String.valueOf(sms)
                , String.valueOf(action)};
        AsyncTask t=new AsyncTask<String, Void, String>() {

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
                    con.getOutputStream().write(("personId=" + params[1]).getBytes());
                    con.getOutputStream().write(("&txt=" + params[2]).getBytes());
                    con.getOutputStream().write(("&start=" + params[3]).getBytes());
                    con.getOutputStream().write(("&end=" + params[4]).getBytes());
                    con.getOutputStream().write(("&address=" + params[5]).getBytes());
                    con.getOutputStream().write(("&platform=" + params[6]).getBytes());
                    con.getOutputStream().write(("&withPerson=" + params[7]).getBytes());
                    con.getOutputStream().write(("&popUp=" + params[8]).getBytes());
                    con.getOutputStream().write(("&sms=" + params[9]).getBytes());
                    con.getOutputStream().write(("&action=" + params[10]).getBytes());

                    // 1. get received JSON data from request
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    if(br != null){
                        result = br.readLine();
                    }
                    else
                        result = "Did not work!(add task)";

                    con.disconnect();
                } catch (Throwable t) {
                    t.printStackTrace();
                }
                Log.d("Log","success");
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.d("Log","dasd");

            }
        };
        t.execute(params);


    }
    public void addPopUp(String text, boolean popUpTamplates, String senderId,String personId){
        String[] params = new String[]{ModelServer.url+urlAddPopUp, text, String.valueOf(popUpTamplates),
                senderId, personId};
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
                    con.getOutputStream().write(("txt=" + params[1]).getBytes());
                    con.getOutputStream().write(("&popUpTamplates=" + params[2]).getBytes());
                    con.getOutputStream().write(("&sendTo=" + params[3]).getBytes());
                    con.getOutputStream().write(("&personId=" + params[4]).getBytes());

                    con.disconnect();
                } catch (Throwable t) {
                    t.printStackTrace();
                }

                return null;
            }


        }.execute(params);

    }


    public void addSms(boolean SmsTamplates, String txt,
                       String personId, String senderId) {
        String[] params = new String[]{ModelServer.url+urlAddSms, personId,txt, senderId};
        AsyncTask t=new AsyncTask<String, Void, String>() {

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
                    con.getOutputStream().write(("personId=" + params[1]).getBytes());
                    con.getOutputStream().write(("&txt=" + params[2]).getBytes());
                    con.getOutputStream().write(("&sendTo=" + params[3]).getBytes());

                    // 1. get received JSON data from request
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    if(br != null){
                        result = br.readLine();
                    }
                    else
                        result = "Did not work!(add Sms)";

                    con.disconnect();
                } catch (Throwable t) {
                    t.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.d("Log","dasd");

            }
        };
        t.execute(params);


    }

    public List<ServerTask> getTasksToDO(String personId)  {
        String[] params = new String[]{ModelServer.url+urlTasksToDo, personId};
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
                Log.d("getTasksToDO", result);
                TaskModelServer.this.result = result;
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
            if(result.equals("null")){
                return null;
            }
            else{
                return mapper.readValue(result, new ArrayList<ServerTask>().getClass());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
//        return mapper.readValue(result, new TypeReference<ArrayList<ServerTask>>(){});
    }


//    public ServerTask getTask(Date date, String personId) throws IOException {
//        String[] params = new String[]{ModelServer.url+urlTask, date.toString(), personId};
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
//                    con.getOutputStream().write(("date=" + params[1]).getBytes());
//                    con.getOutputStream().write(("&personId=" + params[2]).getBytes());
//
//                    // 1. get received JSON data from request
//                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                    if (br != null) {
//                        result = br.readLine();
//                    } else
//                        result = "Did not work!";
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
//                TaskModelServer.this.result = result;
//            }
//        }.execute(params);
//
//
//        // 2. initiate jackson mapper
//        ObjectMapper mapper = new ObjectMapper();
//        // 3. Convert received JSON to Article
//        return mapper.readValue(result, ServerTask.class);
//    }
//
//    public ArrayList<ServerTask> getAllTaskFromView(String personId) throws IOException {
//        String[] params = new String[]{ModelServer.url+urlGetTask, personId};
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
//                        result = "Did not work!";
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
//                TaskModelServer.this.result = result;
//            }
//        }.execute(params);
//
//
//        // 2. initiate jackson mapper
//        ObjectMapper mapper = new ObjectMapper();
//
//        // 3. Convert received JSON to Article
//        return mapper.readValue(result, new ArrayList<ServerTask>().getClass());
////        return mapper.readValue(result, new TypeReference<ArrayList<ServerTask>>(){});
//    }





//    ArrayList<ServerPopUp> getPopUps(String personId, String sendId, Boolean Default) throws IOException {
//        String[] params = new String[]{ModelServer.url+urlTask, personId};
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
//                        result = "Did not work!";
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
//                Log.d("getPopUps", result);
//                TaskModelServer.this.result = result;
//            }
//        }.execute(params);
//
//
//        // 2. initiate jackson mapper
//        ObjectMapper mapper = new ObjectMapper();
//
//        // 3. Convert received JSON to Article
//        return mapper.readValue(result, new ArrayList<ServerPopUp>().getClass());
////        return mapper.readValue(result, new TypeReference<ArrayList<ServerTask>>(){});
//    }



//    public ArrayList<ServerSMS> getSms(String personId, String sendId, Boolean Default) {
//        String[] params = new String[]{ModelServer.url+urlTask, personId};
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
//                    con.getOutputStream().write(("&sendId=" + params[2]).getBytes());
//                    con.getOutputStream().write(("&default=" + params[3]).getBytes());
//
//                    // 1. get received JSON data from request
//                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                    if (br != null) {
//                        result = br.readLine();
//                    } else
//                        result = "Did not work!";
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
//                Log.d("getSms", result);
//                TaskModelServer.this.result = result;
//            }
//        }.execute(params);
//
//
//        // 2. initiate jackson mapper
//        ObjectMapper mapper = new ObjectMapper();
//
//        // 3. Convert received JSON to Article
//        try {
//            return mapper.readValue(result, new ArrayList<ServerSMS>().getClass());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
////        return mapper.readValue(result, new TypeReference<ArrayList<ServerTask>>(){});
//    return null;
//    }
//
//
//
//



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
