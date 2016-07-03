package com.example.oris1991.anotherme.Model.ModelServer;

import android.os.AsyncTask;
import android.util.Log;

import com.example.oris1991.anotherme.Model.Entities.SMSOrPopup;
import com.example.oris1991.anotherme.Model.Entities.SharePictureOrText;
import com.example.oris1991.anotherme.Model.Entities.Solution;
import com.example.oris1991.anotherme.Model.Entities.Task;
import com.example.oris1991.anotherme.Model.ModelServer.Solution.ServerSolution;
import com.example.oris1991.anotherme.Model.ModelServer.Task.ServerPopUp;
import com.example.oris1991.anotherme.Model.ModelServer.Task.ServerTask;
import com.example.oris1991.anotherme.Model.ModelServer.person.ServerPerson;
import com.example.oris1991.anotherme.Model.ModelServer.pictures.ServerSharePictures;
import com.example.oris1991.anotherme.Model.ModelServer.sms.ServerSMS;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.ObjectArraySerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
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

        String[] params = new String[]{ModelServer.url + urlTask, personId, taskText,
                String.valueOf(start.getTime()), String.valueOf(end.getTime()), address, String.valueOf(platform), withPerson, String.valueOf(popUp), String.valueOf(sms)
                , String.valueOf(action)};
        AsyncTask t = new AsyncTask<String, Void, String>() {

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
                    if (br != null) {
                        result = br.readLine();
                    } else
                        result = "Did not work!(add task)";

                    con.disconnect();
                } catch (Throwable t) {
                    t.printStackTrace();
                }
                Log.d("Log", "success");
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.d("Log", "dasd");

            }
        };
        t.execute(params);


    }

    public void addPopUp(String text, boolean popUpTamplates, String senderId, String personId) {
        String[] params = new String[]{ModelServer.url + urlAddPopUp, text, String.valueOf(popUpTamplates),
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
        String[] params = new String[]{ModelServer.url + urlAddSms, personId, txt, senderId};
        AsyncTask t = new AsyncTask<String, Void, String>() {

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
                    if (br != null) {
                        result = br.readLine();
                    } else
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
                Log.d("Log", "dasd");

            }
        };
        t.execute(params);


    }

    public List<Task> getTasksToDO(String personId) {
        String[] params = new String[]{ModelServer.url + urlTasksToDo, personId};
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
                if (result!=null) {
                    Log.d("getTasksToDO", result);
                    TaskModelServer.this.result = result;
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
                //List<itzik> myObjects =  mapper.readValue(result, new TypeReference<List<itzik>>(){});
                List<ClientTask> myObjects =  mapper.readValue(result, new TypeReference<List<ClientTask>>(){});
                ArrayList<Task> task = new ArrayList<Task>();
                for (int i = 0;i<myObjects.size();i++){
                    Task t = new Task(1,myObjects.get(i).getTaskText(),myObjects.get(i).getStart().getTime(),myObjects.get(i).getEnd().getTime(),myObjects.get(i).getAddress());
                    SMSOrPopup sms = new SMSOrPopup(1,"Sms template",null,myObjects.get(i).getWithPerson(),myObjects.get(i).getDateTimeSend().toString(),myObjects.get(i).getSms());
                    SMSOrPopup popup = new SMSOrPopup(1,"",null,null,null,myObjects.get(i).getPopup());

                    t.setSolution(new Solution(1,sms,popup,myObjects.get(i).getWhatToDo()));
                    task.add(t);
                }

                Log.d("Get", "Array list");
                return task;
//                return mapper.readValue(result, new ArrayList<ServerTask>().getClass());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }


  public static class   itzik{

      public String it;
      public String cc;
      public n tt;
      public n getTt() {
          return tt;
      }

      public void setTt(n tt) {
          this.tt = tt;
      }


      public    itzik(){}
        public String getIt() {
            return it;
        }
        public void setIt(String it) {
            this.it = it;
        }
        public String getCc() {
            return cc;
        }
        public void setCc(String cc) {
            this.cc = cc;
        }
    }
    public static class   n{


        public String it;
        public String cc;

        public   n(){}
        public String getIt() {
            return it;
        }
        public void setIt(String it) {
            this.it = it;
        }
        public String getCc() {
            return cc;
        }
        public void setCc(String cc) {
            this.cc = cc;
        }
    }

    //getter and setter methods needed

    public  static class  ClientTask{
        public  String taskText;
        public  Date start;
        public Date end;
        public  String address;
        public int whatToDo;
        public String withPerson;
        public  int timeToArriving;
        public String Sms;
        public Date DateTimeSend;// if it send if not ->null
        public String Popup;
        public Date DateTimeShow; // if it show if not ->null

        public ClientTask() {
            // TODO Auto-generated constructor stub
        }
        public String getTaskText() {
            return taskText;
        }
        public void setTaskText(String taskText) {
            this.taskText = taskText;
        }
        public Date getStart() {
            return start;
        }
        public void setStart(Date start) {
            this.start = start;
        }
        public Date getEnd() {
            return end;
        }
        public void setEnd(Date end) {
            this.end = end;
        }
        public String getAddress() {
            return address;
        }
        public void setAddress(String address) {
            this.address = address;
        }
        public int getWhatToDo() {
            return whatToDo;
        }
        public void setWhatToDo(int whatToDo) {
            this.whatToDo = whatToDo;
        }
        public String getWithPerson() {
            return withPerson;
        }
        public void setWithPerson(String withPerson) {
            this.withPerson = withPerson;
        }

        public int getTimeToArriving() {
            return timeToArriving;
        }
        public void setTimeToArriving(int timeToArriving) {
            this.timeToArriving = timeToArriving;
        }
        public String getSms() {
            return Sms;
        }
        public void setSms(String sms) {
            Sms = sms;
        }
        public Date getDateTimeSend() {
            return DateTimeSend;
        }
        public void setDateTimeSend(Date dateTimeSend) {
            DateTimeSend = dateTimeSend;
        }
        public String getPopup() {
            return Popup;
        }
        public void setPopup(String popup) {
            Popup = popup;
        }
        public Date getDateTimeShow() {
            return DateTimeShow;
        }
        public void setDateTimeShow(Date dateTimeShow) {
            DateTimeShow = dateTimeShow;
        }

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
