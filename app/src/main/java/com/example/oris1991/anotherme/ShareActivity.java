package com.example.oris1991.anotherme;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.oris1991.anotherme.Model.Entities.SharePictureOrText;
import com.example.oris1991.anotherme.Model.Entities.Users;
import com.example.oris1991.anotherme.Model.Model;
import com.example.oris1991.anotherme.Model.ModelMain;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ShareActivity extends AppCompatActivity{
    int REQUEST_CAMERA = 0, SELECT_FILE = 1;


   static String picName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button save =(Button) findViewById(R.id.save);
        final Button cancel =(Button) findViewById(R.id.cancel);
        final Button takePic =(Button) findViewById(R.id.makePic);
        final Button addpic =(Button) findViewById(R.id.getPic);
        final EditText message = (EditText) findViewById(R.id.message);
        final EditText share = (EditText) findViewById(R.id.with);
        List<Users> listUser = Model.instance().getUsers();
        List<String> listUserString = convertToString(listUser);
        final CharSequence[] items= listUserString.toArray(new CharSequence[listUserString.size()]);

        share.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder3=new AlertDialog.Builder(ShareActivity.this);
                builder3.setTitle("Enter Name").setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        share.setText(items[which]);
                    }
                });
                builder3.show();
            }

        });
        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int id = 1;

                String shardWtith = share.getText().toString();
                String text = message.getText().toString();
                Model.instance().addPic(new SharePictureOrText(id,picName,shardWtith,text,false));
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            finish();

            }
        });
        addpic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_FILE);

            }
        });
        takePic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CAMERA);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)  {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calendar_adapter, menu);

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            String path = null;
            if (requestCode == REQUEST_CAMERA) {

                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                String pic= System.currentTimeMillis() + ".jpg";
                picName =pic;
                ModelMain.getInstance().saveImage(imageBitmap,pic);

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
                picName =filename;
                try {
                    Bitmap  bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImageUri));
                    ModelMain.getInstance().saveImageOncloudinary(bitmap,filename);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    public  List<String> convertToString (List<Users> list){
        List<String> listString = new LinkedList<String>();
        for(int i=0;i<list.size();i++ ){
            listString.add(list.get(i).getname());
        }
        return listString;
    }
}
