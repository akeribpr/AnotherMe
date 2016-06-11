package com.example.oris1991.anotherme;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.oris1991.anotherme.Model.Entities.Solution;
import com.example.oris1991.anotherme.Model.Model;

public class NotificationReceiver extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		//setContentView(R.layout.notification_receiver);

		Bundle b = getIntent().getExtras();
		int value = 1; // or other values
		if(b != null){
			value = b.getInt("key");

		}
		String s;
		Log.d("Log","the solution "+value);

		Solution solution = Model.instance().getSolution(3);
		if(solution.getPopUp()!=null){
			s = solution.getPopUp().getText();
		}
		else{
			 s = "send sms?";
		}
		Log.d("Lod",s);

		// define sound URI, the sound to be played when there's a notification
		Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

		// intent triggered, you can add other intent for other actions

		Intent intent = new Intent(this, ReturnFromNotification.class);
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

		// this is it, we'll build the notification!
		// in the addAction method, if you don't want any icon, just set the first param to 0
		Notification mNotification = new Notification.Builder(this)

				.setContentTitle("need to do something")
				.setContentText(s)
				.setSmallIcon(R.drawable.a_m_icon)
				.setContentIntent(pIntent)
				.setSound(soundUri)
				.setAutoCancel(true)
				.addAction(R.drawable.sms_icon, "Call",pIntent).build();

		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		// If you want to hide the notification after it was selected, do the code below
		mNotification.flags |= Notification.FLAG_AUTO_CANCEL;
		notificationManager.notify(0, mNotification);

		finish();
	}

}