package com.example.oris1991.anotherme;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class NotificationReceiver extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification_receiver);

		TextView tv = new TextView(this);
		tv.setText("Yo!");
		
		setContentView(tv);
	}
}