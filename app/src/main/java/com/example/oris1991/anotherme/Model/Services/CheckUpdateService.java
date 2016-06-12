package com.example.oris1991.anotherme.Model.Services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.example.oris1991.anotherme.MainActivity;
import com.example.oris1991.anotherme.Model.Entities.SharePictureOrText;
import com.example.oris1991.anotherme.Model.Entities.Solution;
import com.example.oris1991.anotherme.Model.Entities.Task;
import com.example.oris1991.anotherme.Model.Model;
import com.example.oris1991.anotherme.Model.ModelServer.ModelServer;
import com.example.oris1991.anotherme.NotificationReceiver;
import com.example.oris1991.anotherme.R;
import com.example.oris1991.anotherme.ReturnFromNotification;

import java.util.ArrayList;
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
        //Log.d("TAG","service on create");
    }


    @Override
    public void onDestroy() {

        super.onDestroy();
        running = false;
        //Log.d("TAG","service on destroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       // Log.d("TAG","service onStartCommand");
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
            NotificationUtils.displayNotification(getApplicationContext());
           // notification(2);
            while(running){
                //makeTasks(modelServer.checkUpdateTask());
                //  addShare(modelServer.checkUpdateShare());

                try {
                    sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void makeTasks(List<Task> task){
        for(int i= 0; i<task.size();i++){
            if(task.get(i).getSolution().getPopUp()!=null &&task.get(i).getSolution().getSms()!=null){
                notification(task.get(i).getSolution().getIdSolution());
            }

        }
    }
    public void addShare(List<SharePictureOrText> share){
        for(int i= 0; i<share.size();i++){

            Model.instance().addPic(share.get(i));
        }

    }

    public void notification(int solutionId){
        String s = "itzik";
//        Solution solution = Model.instance().getSolution(solutionId);
//        if(solution.getPopUp()!=null){
//            s = solution.getPopUp().getText();
//        }
//        else{
//            s = "need to do!";
//        }

        Intent resultIntent = new Intent(this, ReturnFromNotification.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.a_m_icon)
                .setContentTitle(s)
                .setContentText("send sms?")
                .setAutoCancel(true)
                .addAction(0, "send to",resultPendingIntent);


        int mNotificationId = 001;
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //  Log.d("Log","notification");
        mNotifyMgr.notify(mNotificationId, mBuilder.build());



        BroadcastReceiver call_method = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action_name = intent.getAction();
                if (action_name.equals("send to")) {
                    Log.d("Log","notification");
                }
            };
        };
        registerReceiver(call_method, new IntentFilter("call_method"));



       // .addAction(0, "send to "+solution.getSms().getSendtoName(),resultPendingIntent);

//        Intent dialogIntent = new Intent(this, NotificationReceiver.class);
//        Bundle b = new Bundle();
//        b.putInt("key", solution); //Your id
//        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        dialogIntent.putExtras(b); //Put your id to your next Intent
//        startActivity(dialogIntent);

    }

    public static class NotificationUtils {
        public static final int NOTIFICATION_ID = 1;

        public static final String ACTION_1 = "action_1";

        public static void displayNotification(Context context) {

            Intent action1Intent = new Intent(context, NotificationActionService.class)
                    .setAction(ACTION_1);

            PendingIntent action1PendingIntent = PendingIntent.getService(context, 0,
                    action1Intent, PendingIntent.FLAG_ONE_SHOT);

            NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.a_m_icon)
                            .setContentTitle("Sample Notification")
                            .setContentText("Notification text goes here")
                            .addAction(new NotificationCompat.Action(R.drawable.a_m_icon,
                                    "send sms?", action1PendingIntent));

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
        }

        public static class NotificationActionService extends IntentService {
            public NotificationActionService() {
                super(NotificationActionService.class.getSimpleName());
            }

            @Override
            protected void onHandleIntent(Intent intent) {
                String action = intent.getAction();
                if (ACTION_1.equals(action)) {
                    // TODO: handle action 1.
                    Log.d("tag","success");
                    // If you want to cancel the notification: NotificationManagerCompat.from(this).cancel(NOTIFICATION_ID);
                }
            }
        }
    }
//    String phoneNo = "0525541676";
//    String msg = "massage";
//    try {
//
//        SmsManager smsManager = SmsManager.getDefault();
//        smsManager.sendTextMessage(phoneNo, null, msg, null, null);
//
//    } catch (Exception ex) {
//
//        ex.printStackTrace();
//    }
}