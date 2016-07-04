package com.example.oris1991.anotherme.Model.Services;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.telephony.SmsManager;
import android.util.Log;

import com.example.oris1991.anotherme.Model.Entities.SMSOrPopup;
import com.example.oris1991.anotherme.Model.Entities.SharePictureOrText;
import com.example.oris1991.anotherme.Model.Entities.Task;
import com.example.oris1991.anotherme.Model.Model;
import com.example.oris1991.anotherme.Model.ModelServer.ModelServer;
import com.example.oris1991.anotherme.R;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Itzik on 10/06/2016.
 */
public class CheckUpdateService extends Service {
    boolean running = true;
    ModelServer modelServer;



    public CheckUpdateService() {
        modelServer = new ModelServer();
    }
    @Override
    public void onCreate() {
        super.onCreate();

    }


    @Override
    public void onDestroy() {

        super.onDestroy();
        running = false;

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        super.onStartCommand(intent, flags, startId);
        ServiceUpdate serviceUpdate = new ServiceUpdate();
        serviceUpdate.start();

        return START_STICKY;
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    class ServiceUpdate extends Thread{

        public void run(){

            while(running){
                makeTasks(modelServer.checkUpdateTask());
                addShare(modelServer.checkUpdateShare());

                try {

                    sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                boolean checkGpsEnabeled = sharedPref.getBoolean("pref_gps", true);
                if (checkGpsEnabeled) {
                    Intent intent = new Intent(getApplicationContext(), GpsService.class);
                    startService(intent);
                }
            }
        }
    }

    public void makeTasks(List<Task> task){
        if(task==null){
            Log.d("Task","No Update!!!");
        }
        else{
            Log.d("Task"," Update!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            for(int i= 0; i<task.size();i++){
                if(task.get(i).getSolution().getPopUp()!=null &&task.get(i).getSolution().getSms()!=null){
                    NotificationUtils.displayNotification(getApplicationContext(),task.get(i).getSolution().getPopUp(),task.get(i).getSolution().getSms());
                }
            }
        }

    }
    public void addShare(List<SharePictureOrText> share){
        if(share==null){
            Log.d("Pic","no picture!!!!");
        }
        else{
            for(int i= 0; i<share.size();i++){
                Model.instance().addPictuerFromServer(share.get(i));
            }
        }


    }




    public static class NotificationUtils {



        public static final int NOTIFICATION_ID = 1;

        public static final String SEND = "send";
        public static final String CENCEL = "cencel";
        public static SMSOrPopup  smss;

        public static SMSOrPopup getSms() {
            return smss;
        }

        public static void  setSms(SMSOrPopup sms) {
            smss = sms;
        }

        public static void displayNotification(Context context,SMSOrPopup popup,SMSOrPopup sms) {

            setSms(sms);

            Intent sendIntent = new Intent(context, NotificationActionService.class)
                    .setAction(SEND);

            Intent cancelIntent = new Intent(context, NotificationActionService.class)
                    .setAction(CENCEL);

            PendingIntent sendPendingIntent = PendingIntent.getService(context, 0,
                    sendIntent, PendingIntent.FLAG_ONE_SHOT);

            PendingIntent cancelPendingIntent = PendingIntent.getService(context, 0,
                    cancelIntent, PendingIntent.FLAG_ONE_SHOT);

            NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.a_m_icon)
                            .setAutoCancel(true)
                            .setContentTitle(popup.getText())
                            .setContentText(sms.getSendtoName())
                            .addAction(new NotificationCompat.Action(R.drawable.send_icon,
                                    "send ", sendPendingIntent))
                            .addAction(new NotificationCompat.Action(R.drawable.cancel,
                                    "cancel ", cancelPendingIntent)) ;



            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
        }

        public static class NotificationActionService extends IntentService {


            public void sendSms(SMSOrPopup s){

                final String phoneNo = s.getSendto();
                final   String msg = s.getText();
                Thread d = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(phoneNo, null,msg, null, null);

                        } catch (Exception ex) {

                            ex.printStackTrace();
                        }
                    }
                });
                d.start();
            }

            public NotificationActionService() {
                super(NotificationActionService.class.getSimpleName());
            }

            @Override
            protected void onHandleIntent(Intent intent) {
                String action = intent.getAction();
                if (SEND.equals(action)) {
                    Log.d("tag", "success send");
                    sendSms(smss);
                    DateFormat df = new SimpleDateFormat("d MMM yyyy, HH:mm");
                    String date = df.format(Calendar.getInstance().getTime());
                    SMSOrPopup sp =new SMSOrPopup(1,"SMS",smss.getSendto(),smss.getSendtoName(),date,smss.getText());
                    Model.instance().addHistoryEvent(sp);

                    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    manager.cancel(NOTIFICATION_ID);
                }
                if (CENCEL.equals(action)) {
                    Log.d("tag", "success cancel");
                    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    manager.cancel(NOTIFICATION_ID);
                }
            }
        }
    }

}