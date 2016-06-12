package com.example.oris1991.anotherme;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;

/**
 * Created by Itzik on 11/06/2016.
 */
/*public class ReturnFromNotification extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String phoneNo = "0525541676";
    String msg = "massage";
    try {

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNo, null, msg, null, null);

    } catch (Exception ex) {

        ex.printStackTrace();
    }


    }*/

    public class ReturnFromNotification extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        String phoneNo = "0547297791";
        String msg = "massage";
        try {

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        finish();


    }

    }
       /* String action = intent.getAction();
       Log.d("Log","notification");

        if("Call".equals(action)) {
            Log.d("shuffTest","Pressed YES");
        } else if("MAYBE_ACTION".equals(action)) {
            Log.v("shuffTest","Pressed NO");
        } else if("NO_ACTION".equals(action)) {
            Log.v("shuffTest","Pressed MAYBE");
        }*/

