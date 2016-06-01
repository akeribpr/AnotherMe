package com.example.oris1991.anotherme;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.oris1991.anotherme.Model.ModelMain;
import com.example.oris1991.anotherme.Model.Entities.Task;
import com.example.oris1991.anotherme.PopUpAndSMS.PopupTemplates;
import com.example.oris1991.anotherme.PopUpAndSMS.SmsTemplates;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements NewEventFragment.Delegate, LocationListener,SoluttionFragment.Delegate {

    int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    FragmentManager manager;
    CalendarViewFragment calendarFra;
    NewEventFragment newEventFra;
    SettingsActivity settingsFra;
    SoluttionFragment solFra;
    FloatingActionButton fab;
    protected LocationManager  mlocManager;
    SharedPreferences sharedPreferencesPut;
    SharedPreferences sharedPreferencesGet;
    Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Gps gp =new Gps("10:00","232.543","43.4543");

        // Make sure that GPS is enabled on the device
        mlocManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        boolean enabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if(!enabled) {
            showDialogGPS();
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000, 0, this);

        calendarFra=new CalendarViewFragment();
        manager = getFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        //getFragmentManager().beginTransaction();
        transaction.add(R.id.frag_container, calendarFra);
        //transaction.show(calendarFra);
        transaction.commit();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                addFragment = new AddFragment();
//                FragmentTransaction transaction = manager.beginTransaction();
//                transaction.remove(studentListFragment);
//                transaction.add(R.id.main,addFragment, "TAG");
//                transaction.addToBackStack(null);
//                invalidateOptionsMenu();
//                transaction.commit();


                newEventFra = new NewEventFragment();
                FragmentTransaction transaction = manager.beginTransaction();
                //getFragmentManager().beginTransaction();
                transaction.remove(calendarFra);
                transaction.add(R.id.frag_container, newEventFra);
                transaction.addToBackStack(null);
                invalidateOptionsMenu();

                //transaction.hide(calendarFra);
                //transaction.show(newEventFra);
                transaction.commit();
                fab.setVisibility(view.GONE);
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        SharedPreferences  sharedPreferencesPut = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor =  sharedPreferencesPut.edit();
        editor.putString("Lat", String.valueOf( location.getLatitude()));
        editor.putString("Lon", String.valueOf(location.getLongitude()));
        editor.commit();

        sharedPreferencesGet = PreferenceManager
                .getDefaultSharedPreferences(this);
        String lat = sharedPreferencesGet.getString("Lat", "no lat");
        String lon = sharedPreferencesGet.getString("Lon", "no lon");
        Log.d("Tag", lat);
        Log.d("Tag", lon);
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude", "disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude", "enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude", "status");
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
            settingsFra=new SettingsActivity();
            manager = getFragmentManager();
            FragmentTransaction transaction=manager.beginTransaction();
            transaction.remove(currentFragment);
            transaction.add(R.id.frag_container, settingsFra);
            fab.setVisibility(View.GONE);
            //transaction.show(calendarFra);
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
        if ( id ==R.id.action_SMS)
        {
            String phoneNo = "0547297791";
            String msg = "hi";
            try {

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNo, null, msg, null, null);
                Toast.makeText(getApplicationContext(), "Message Sent",
                        Toast.LENGTH_LONG).show();
            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(),
                        ex.getMessage().toString(),
                        Toast.LENGTH_LONG).show();
                ex.printStackTrace();
            }
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
//        if (id == R.id.action_login)
//        {
//            Intent intent = new Intent(getApplicationContext(),
//                    LogInActivity.class);
//            startActivity(intent);
//            return true;
//        }

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
        final CharSequence[] items = { "Take Photo", "Choose from Library", "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            SELECT_FILE);
                    //  startActivityForResult(intent, SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            String path = null;
            if (requestCode == REQUEST_CAMERA) {

                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");

                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                File destination = new File(Environment.getExternalStorageDirectory(),
                        System.currentTimeMillis() + ".jpg");
                saveInCloudinary(destination.getAbsolutePath());
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                saveInCloudinary(destination.getAbsolutePath());
                //  ivImage.setImageBitmap(thumbnail);
            } else if (requestCode == SELECT_FILE) {
                Uri selectedImageUri = data.getData();
                String[] projection = {MediaStore.MediaColumns.DATA};
                CursorLoader cursorLoader = new CursorLoader(this, selectedImageUri, projection, null, null,
                        null);
                Cursor cursor = cursorLoader.loadInBackground();
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                cursor.moveToFirst();
                String selectedImagePath = cursor.getString(column_index);
                String filename=selectedImagePath.substring(selectedImagePath.lastIndexOf("/")+1);
                Bitmap bm;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(selectedImagePath, options);

                final int REQUIRED_SIZE = 200;
                int scale = 1;
                while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                        && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                    scale *= 2;
                options.inSampleSize = scale;
                options.inJustDecodeBounds = false;
                bm = BitmapFactory.decodeFile(selectedImagePath, options);
                saveInCloudinary(selectedImagePath);
                // ModelMain.getInstance().saveImage(bm,filename);
                // ivImage.setImageBitmap(bm);
            }
        }
    }

    public void saveInCloudinary(String destination){
        String filename=destination.substring(destination.lastIndexOf("/")+1);
        //  Bitmap bm;
        BitmapFactory.Options options;


        try {

            Bitmap bitmap = BitmapFactory.decodeFile(destination);
            ModelMain.getInstance().saveImage(bitmap,filename);
        } catch (OutOfMemoryError e) {
            try {
                options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                Bitmap bitmap = BitmapFactory.decodeFile(destination, options);
                ModelMain.getInstance().saveImage(bitmap,filename);
            } catch(Exception ex) {
                Log.d("savepic","seve pic 2 faild");
            }
        }


//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        bm = BitmapFactory.decodeFile(destination, options);
//        ModelMain.getInstance().saveImage(bm,filename);

    }



    @Override
    public void endFragment(int code) {

        if (code==1)
        {
            calendarFra = new CalendarViewFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.frag_container, calendarFra);
            transaction.hide(newEventFra);
            transaction.show(calendarFra);
            transaction.commit();
            fab.setVisibility(View.VISIBLE);
        }
        else
        {
            solFra=new SoluttionFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.frag_container, solFra);
            transaction.hide(newEventFra);
            transaction.show(solFra);
            transaction.commit();

        }
    }

    @Override
    public void SaveSolution() {
        calendarFra = new CalendarViewFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(solFra);
        transaction.add(R.id.frag_container, calendarFra);
        transaction.addToBackStack(null);
        invalidateOptionsMenu();
//        manager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
//            @Override
//            public void onBackStackChanged() {
//                if(getFragmentManager().getBackStackEntryCount() == 0) finish();
//            }
//        });
        //transaction.hide(newEventFra);
        //transaction.show(calendarFra);
        transaction.commit();
        fab.setVisibility(View.VISIBLE);
    }

    @Override
    public void CancelSolution() {
        calendarFra = new CalendarViewFragment();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.remove(solFra);
        transaction.add(R.id.frag_container, calendarFra);
        transaction.addToBackStack(null);
        invalidateOptionsMenu();
        transaction.commit();
        fab.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNot(String smsNote) {
        showNotification(smsNote);
    }



    @Override
    public void taskWithSolution(Task task) {
        this.task=task;
        solFra = new SoluttionFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(newEventFra);
        transaction.add(R.id.frag_container, solFra);
        transaction.addToBackStack("task");
        invalidateOptionsMenu();
        transaction.commit();
        fab.setVisibility(View.VISIBLE);

    }

    @Override
    public void endFragmentTask() {
        calendarFra = new CalendarViewFragment();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.remove(newEventFra);
        transaction.add(R.id.frag_container, calendarFra);
        transaction.addToBackStack(null);
        invalidateOptionsMenu();
        transaction.commit();
        fab.setVisibility(View.VISIBLE);
    }
    @Override
    public void onBackPressed() {
        Fragment currentFragment = manager.findFragmentById(R.id.frag_container);
        if (currentFragment instanceof CalendarViewFragment) {
            super.onBackPressed();
        }
        else
        {
            calendarFra = new CalendarViewFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.frag_container, calendarFra);
            transaction.remove(currentFragment);
            transaction.show(calendarFra);
            transaction.commit();
            fab.setVisibility(View.VISIBLE);
        }
    }
}