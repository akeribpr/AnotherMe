package com.example.oris1991.anotherme.PopUpAndSMS;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oris1991.anotherme.HistoryActivity;
import com.example.oris1991.anotherme.Model.Entities.Users;
import com.example.oris1991.anotherme.R;
import com.example.oris1991.anotherme.Model.Model;
import com.example.oris1991.anotherme.Model.Entities.SMSOrPopup;

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
    String phoneNumber;
    EditText templateTextt;
    String phoneName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sms_templates);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list = (ListView) findViewById(R.id.sms_list);

        data = Model.instance().getSmsTemplates();

        adapter = new MyAddapter();
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                 final int idFromList = data.get(position).getId();
                //  Toast.makeText(getActivity().getApplicationContext(),idFromList, Toast.LENGTH_LONG).show();
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:

                                Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_LONG).show();
                                Model.instance().deleteSmsOrPopup(idFromList);
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:

                                break;
                        }
                    }
                };
                adapter.notifyDataSetChanged();

                AlertDialog.Builder builder = new AlertDialog.Builder(SmsTemplates.this);
                builder.setMessage("delete " + data.get(position).getText() + " ?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();

            }


        });

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             /*   Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT_REQUEST);*/
                newTemplateSmsDialog("Enter template text:", SmsTemplates.this);
            }

        });

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sms, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_history) {
            Intent intent = new Intent(getApplicationContext(),
                    HistoryActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        if (id == R.id.action_popup_templates)
        {
            Intent intent = new Intent(getApplicationContext(),
                    PopupTemplates.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request it is that we're responding to
        if (requestCode == PICK_CONTACT_REQUEST && resultCode == RESULT_OK) {
            Uri contactUri = data.getData();
            Cursor cursor = getContentResolver().query(contactUri, null, null, null, null);
            cursor.moveToFirst();
            int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int columnName = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            phoneNumber=cursor.getString(column);
            phoneName=cursor.getString(columnName);
        }
    }

    public void newTemplateSmsDialog(String title, final Context activity) {

        final Dialog myDialog = new Dialog(activity);
        myDialog.setContentView(R.layout.sms_templates_dialog);
        myDialog.setTitle(title);
        myDialog.setCancelable(false);

        //Log.d("ddd",templateText.toString());


        Button createAccount= (Button) myDialog.findViewById(R.id.pick_contact);
        createAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT_REQUEST);
            }
        });

        Button submitButton= (Button) myDialog.findViewById(R.id.submitSmsTemplate);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                templateTextt = (EditText)myDialog.findViewById(R.id.template_sms_txt);
               // int i = Model.instance().numberOfRow();
               // i++;
                SMSOrPopup sp;
                if (phoneName!=null&&phoneNumber!=null) {
                    sp = new SMSOrPopup(1, "Sms template", phoneNumber, phoneName, null, templateTextt.getText().toString());
                }
                else
                {
                    sp = new SMSOrPopup(1, "Sms template", null, null, null, templateTextt.getText().toString());
                }

                Model.instance().addSmsOrPop(sp);
                data = Model.instance().getSmsTemplates();
                adapter.notifyDataSetChanged();
                myDialog.dismiss();
            }
        });

        Button cancelButton= (Button) myDialog.findViewById(R.id.cancelSmsTemplate);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
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
            if (sp.getSendto()!=null&&sp.getSendtoName()!=null)
                sendTo.setText(sp.getSendto()+" "+sp.getSendtoName());
            else
                sendTo.setText("Template");
            return convertView;
        }
    }
}
