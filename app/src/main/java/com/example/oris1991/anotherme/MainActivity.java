package com.example.oris1991.anotherme;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.oris1991.anotherme.Model.Entities.Solution;
import com.example.oris1991.anotherme.Model.Entities.Users;
import com.example.oris1991.anotherme.Model.Model;
import com.example.oris1991.anotherme.Model.Entities.Task;
import com.example.oris1991.anotherme.Model.Services.CheckUpdateService;
import com.example.oris1991.anotherme.Model.Services.GpsService;
import com.example.oris1991.anotherme.PopUpAndSMS.PopupTemplates;
import com.example.oris1991.anotherme.PopUpAndSMS.SmsTemplates;

public class MainActivity extends AppCompatActivity implements NewEventFragment.Delegate,SoluttionFragment.Delegate,CalendarViewFragment.Delegate, EditFragment.Delegate,UsersFragment.UsersFragmentInterface,EditSolutionFragment.Delegate {

    int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    FragmentManager manager;
    CalendarViewFragment calendarFra;
    NewEventFragment newEventFra;
    SettingsFragment settingsFra;
    SoluttionFragment solFra;
    EditFragment editFra;
    EditSolutionFragment editSolFra;
    protected LocationManager  mlocManager;
    SharedPreferences sharedPreferencesPut;
    SharedPreferences sharedPreferencesGet;
    ShareHistoryFragment shareHistoryFragment;
    UsersFragment userFrag;
    Task task;
    protected LocationManager locManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = new Intent(MainActivity.this,CheckUpdateService.class);
        startService(intent);


        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean enabled = locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if(!enabled) {
            showDialogGPS();
        }
        else {
            Intent intentt = new Intent(MainActivity.this,GpsService.class);
            startService(intentt);
        }

        calendarFra=new CalendarViewFragment();
        manager = getFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        //getFragmentManager().beginTransaction();
        transaction.add(R.id.frag_container, calendarFra);
        //transaction.show(calendarFra);
        transaction.commit();

    }


    private void showDialogGPS() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Enable GPS");
        builder.setMessage("Please enable GPS");
        builder.setInverseBackgroundForced(true);
        builder.setPositiveButton("Enable", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                startActivity(
                        new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        });
        builder.setNegativeButton("Ignore", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calendar_adapter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Fragment currentFragment = manager.findFragmentById(R.id.frag_container);
            settingsFra=new SettingsFragment();
            manager = getFragmentManager();
            FragmentTransaction transaction=manager.beginTransaction();
            transaction.replace(R.id.frag_container,settingsFra);
            transaction.commit();
            return true;
        }
        if (id == R.id.action_history) {
            Intent intent = new Intent(getApplicationContext(),
                    HistoryActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_camera)
        {
            selectImage();
            return true;
        }
        if (id == R.id.action_add)
        {
            int day=calendarFra.gridvalue;
            if (day==0)
                day=1;
            int month= calendarFra.getMmonth();
            int year = calendarFra.getYear();
            newEventFra = new NewEventFragment();
            newEventFra.setDate(day,month,year);
            FragmentTransaction transaction = manager.beginTransaction();
            //getFragmentManager().beginTransaction();
           /* transaction.remove(calendarFra);
            transaction.add(R.id.frag_container, newEventFra);*/
            transaction.replace(R.id.frag_container,newEventFra);
            transaction.addToBackStack(null);
            invalidateOptionsMenu();
            transaction.commit();
            return true;
        }

        if (id == R.id.action_popup_templates)
        {
            Intent intent = new Intent(getApplicationContext(),
                    PopupTemplates.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_sms_templates)
        {
            Intent intent = new Intent(getApplicationContext(),
                    SmsTemplates.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.fragment_user)
        {
            userFrag = new UsersFragment();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.frag_container, userFrag);
            invalidateOptionsMenu();
            transaction.commit();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showNotification(String smsNote){

        // define sound URI, the sound to be played when there's a notification
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        // intent triggered, you can add other intent for other actions
        Intent intent = new Intent(MainActivity.this, NotificationReceiver.class);
        PendingIntent pIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);

        // this is it, we'll build the notification!
        // in the addAction method, if you don't want any icon, just set the first param to 0
        Notification mNotification = new Notification.Builder(this)

                .setContentTitle("New Post!")
                .setContentText("send sms "+smsNote+" ?")
                .setSmallIcon(R.drawable.ninja)
                .setContentIntent(pIntent)
                .setSound(soundUri)

                .addAction(R.drawable.ninja, "View", pIntent)
                .addAction(0, "Remind", pIntent)

                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // If you want to hide the notification after it was selected, do the code below
        // myNotification.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, mNotification);
    }
    private void selectImage() {

        final CharSequence[] items = { "Add User","Add Share", "Get History of Shard", "Cancel" };
        //  boolean result=Utility.checkPermission(MainActivity.this);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Share");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Add User")) {
                    dialog.dismiss();
                    AlertDialog.Builder builderShare = new AlertDialog.Builder(MainActivity.this);
                    builderShare.setTitle("Add User");
                    final EditText input = new EditText(MainActivity.this);
                    input.setInputType(InputType.TYPE_CLASS_TEXT);

                    builderShare.setView(input);

                    builderShare.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Users user = new Users(input.getText().toString());
                            Model.instance().addUser(user);
                        }
                    });
                    builderShare.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builderShare.show();

                    Log.d("LOG","Add User");
                } else if (items[item].equals("Add Share")) {
                    Intent intent = new Intent(getApplicationContext(),
                            ShareActivity.class);
                    startActivity(intent);

                } else if (items[item].equals("Get History of Shard")) {
                    Log.d("LOG","shared History");

                    shareHistoryFragment = new ShareHistoryFragment();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.frag_container, shareHistoryFragment);
                    invalidateOptionsMenu();
                    transaction.commit();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    public void endFragment(int code) {

        if (code==1)
        {
            calendarFra = new CalendarViewFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frag_container,calendarFra);
            //transaction.add(R.id.frag_container, calendarFra);
            //transaction.hide(newEventFra);
            //transaction.show(calendarFra);
            transaction.commit();
        }
        else
        {
            solFra=new SoluttionFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frag_container,solFra);
            //transaction.add(R.id.frag_container, solFra);
            //transaction.hide(newEventFra);
            //transaction.show(solFra);
            transaction.commit();

        }
    }


    @Override
    public void SaveSolution(Solution sol,Task task) {

        newEventFra = new NewEventFragment();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frag_container,newEventFra);
       // transaction.remove(solFra);
       // transaction.add(R.id.frag_container, newEventFra);
       // transaction.addToBackStack(null);
        invalidateOptionsMenu();
        newEventFra.setSolution(sol);
        newEventFra.setTask(task);
        transaction.commit();
    }

    @Override
    public void SaveSolutionEdit(Solution solutionAfterEditSolution, Task task) {

        editFra = new EditFragment();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frag_container,editFra);

       // transaction.remove(editSolFra);
        //transaction.add(R.id.frag_container, editFra);
        //transaction.addToBackStack(null);
        invalidateOptionsMenu();
        editFra.setSolution(solutionAfterEditSolution);
        editFra.setTask(task);
        transaction.commit();

    }

    @Override
    public void CancelSolutionEdit() {

        calendarFra = new CalendarViewFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frag_container,calendarFra);


        //transaction.remove(editSolFra);
       // transaction.add(R.id.frag_container, calendarFra);
       // transaction.addToBackStack(null);
        invalidateOptionsMenu();
        transaction.commit();
    }

    @Override
    public void CancelSolution() {
        calendarFra = new CalendarViewFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frag_container,calendarFra);

        //transaction.remove(solFra);
        //transaction.add(R.id.frag_container, calendarFra);
       // transaction.addToBackStack(null);
        invalidateOptionsMenu();
        transaction.commit();
    }

    @Override
    public void showNot(String smsNote) {
        showNotification(smsNote);
    }



    @Override
    public void taskWithSolution(Task task, Solution sol) {
        //this.task=task;
        solFra = new SoluttionFragment();
        solFra.setTask(task);
        solFra.setSol(sol);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frag_container,solFra);

     //   transaction.remove(newEventFra);
       // transaction.add(R.id.frag_container, solFra);
       // transaction.addToBackStack("task");
        invalidateOptionsMenu();
        transaction.commit();

    }

    @Override
    public void taskEditWithSolution(Task task, Solution solutionAfterEditSolution, Solution old) {
        editSolFra = new EditSolutionFragment();
        editSolFra.setTask(task);
        editSolFra.setSol(solutionAfterEditSolution,old);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frag_container,editSolFra);

        //transaction.remove(editFra);
       // transaction.add(R.id.frag_container,editSolFra);
        //transaction.addToBackStack(null);
        invalidateOptionsMenu();
        transaction.commit();
    }

    @Override
    public void endFragmentEdit() {

        calendarFra = new CalendarViewFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frag_container,calendarFra);

       // transaction.remove(editFra);
       // transaction.add(R.id.frag_container, calendarFra);
       // transaction.addToBackStack(null);
        invalidateOptionsMenu();
        transaction.commit();
    }

    @Override
    public void endFragmentTask() {
        calendarFra = new CalendarViewFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frag_container,calendarFra);

       // transaction.remove(newEventFra);
       // transaction.add(R.id.frag_container, calendarFra);
       // transaction.addToBackStack(null);
        invalidateOptionsMenu();
        transaction.commit();
    }


    @Override
    public void onBackPressed() {
        Fragment currentFragment = manager.findFragmentById(R.id.frag_container);
        if (currentFragment instanceof CalendarViewFragment) {
            super.onBackPressed();
            Intent intentt = new Intent(MainActivity.this,GpsService.class);
            stopService(intentt);
        }
      else  if(currentFragment instanceof NewEventFragment){
            calendarFra = new CalendarViewFragment();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.frag_container,calendarFra);
           // invalidateOptionsMenu();
            transaction.commit();
        }
        else if(currentFragment instanceof SettingsFragment){
            calendarFra = new CalendarViewFragment();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.frag_container,calendarFra);
            invalidateOptionsMenu();
            transaction.commit();
        }
        else if(currentFragment instanceof SoluttionFragment){

        }
        else if(currentFragment instanceof EditFragment){
            calendarFra = new CalendarViewFragment();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.frag_container,calendarFra);
            invalidateOptionsMenu();
            transaction.commit();

        }
        else if(currentFragment instanceof EditSolutionFragment){

        }
        else {
            calendarFra = new CalendarViewFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frag_container,calendarFra);
            //transaction.add(R.id.frag_container, calendarFra);
            //transaction.remove(currentFragment);
            //transaction.show(calendarFra);
            transaction.commit();
        }
    }

    @Override
    public void startEdit(int pos) {

        editFra = new EditFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        //  transaction.replace(R.id.frag_container,editFra);
        transaction.remove(calendarFra);
        transaction.add(R.id.frag_container,editFra);
        transaction.addToBackStack(null);
        invalidateOptionsMenu();
        editFra.setPosition(pos);
        transaction.commit();
    }


    @Override
    public void upgateUsersFragment() {
        userFrag = new UsersFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frag_container, userFrag);
        invalidateOptionsMenu();
        transaction.commit();

    }
}