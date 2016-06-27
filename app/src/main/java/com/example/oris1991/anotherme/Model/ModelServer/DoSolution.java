package com.example.oris1991.anotherme.Model.ModelServer;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
import android.net.Uri;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.example.oris1991.anotherme.Model.Entities.Task;
import com.example.oris1991.anotherme.NotificationReceiver;
import com.example.oris1991.anotherme.R;

import java.util.ArrayList;

/**
 * Created by itzik on 11/06/2016.
 */
public class DoSolution{

Boolean bool = false;
    public DoSolution(){

    }

    public void sendSms() {

        Thread d = new Thread(new Runnable() {
            @Override
            public void run() {

                if(bool) {
                    String phoneNo = "0525541676";
                    String msg = "massage";
                    try {

                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(phoneNo, null, msg, null, null);

                    } catch (Exception ex) {

                        ex.printStackTrace();
                    }
                }
            }
        });
        d.start();

    }



}
