package com.example.oris1991.anotherme;

import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.oris1991.anotherme.Model.Model;
import com.example.oris1991.anotherme.Model.SMSOrPopup;

import java.util.List;

/**
 * Created by oris1991 on 22/05/2016.
 */
public class SmsTemplates extends AppCompatActivity {

    ListView list;
    List<SMSOrPopup> data;
    MyAddapter adapter;
    FloatingActionButton fab;
    int PICK_CONTACT_REQUEST =1;
    EditText templateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sms_templates);

        list = (ListView) findViewById(R.id.sms_list);

        data = Model.instance().getSmsOrPopups();

        adapter = new MyAddapter();
        list.setAdapter(adapter);

        SMSOrPopup sp = new SMSOrPopup("Sms template","Itzik Choen",null,"sorry i am late...");
        Model.instance().add(sp);
        data = Model.instance().getSmsTemplates();
        adapter.notifyDataSetChanged();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             /*   Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT_REQUEST);*/
                newTemplateSmsDialog("sss","sss",SmsTemplates.this);
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request it is that we're responding to
        if (requestCode == PICK_CONTACT_REQUEST && resultCode == RESULT_OK) {
            Uri contactUri = data.getData();
            Cursor cursor = getContentResolver().query(contactUri, null, null, null, null);
            cursor.moveToFirst();
            int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            Log.d("phone number", cursor.getString(column));
        }
    }

    public void newTemplateSmsDialog(String title, String message, final Context activity) {

        final Dialog myDialog = new Dialog(activity);
        myDialog.setContentView(R.layout.sms_templates_dialog);
        myDialog.setTitle(title);
        myDialog.setCancelable(false);

        templateText = (EditText)findViewById(R.id.template_sms_txt);;
     /*   text.setMovementMethod(ScrollingMovementMethod.getInstance());
        text.setText(message);*/


        Button createAccount= (Button) myDialog.findViewById(R.id.pick_contact);
        createAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT_REQUEST);
                myDialog.dismiss();
            }
        });


        myDialog.show();

    }

    class MyAddapter extends BaseAdapter {


        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView,
                            ViewGroup parent) {
            if(convertView == null){
                LayoutInflater inflater = getLayoutInflater();
                convertView = inflater.inflate(R.layout.sms_template_row, null);
            }else{
                Log.d("TAG", "use convert view:" + position);
            }

            TextView text = (TextView) convertView.findViewById(R.id.sms_text_template);
            TextView sendTo = (TextView) convertView.findViewById(R.id.sms_sendto_template);

            SMSOrPopup sp = data.get(position);

            text.setText(sp.getText());
            sendTo.setText(sp.getSendto());

            return convertView;
        }
    }
}
