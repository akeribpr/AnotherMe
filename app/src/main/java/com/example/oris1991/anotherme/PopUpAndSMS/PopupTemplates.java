package com.example.oris1991.anotherme.PopUpAndSMS;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.oris1991.anotherme.Model.Entities.SMSOrPopup;
import com.example.oris1991.anotherme.Model.Model;
import com.example.oris1991.anotherme.R;

import java.util.List;

/**
 * Created by oris1991 on 21/05/2016.
 */
public class PopupTemplates extends AppCompatActivity {

    ListView list;
    List<SMSOrPopup> data;
    MyAddapter adapter;
    FloatingActionButton fab;
    EditText templateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_templates);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list = (ListView) findViewById(R.id.pop_up_list);

        data = Model.instance().getPopupsTemplates();

        adapter = new MyAddapter();
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                final int idFromList = data.get(position).getId();
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

                AlertDialog.Builder builder = new AlertDialog.Builder(PopupTemplates.this);
                builder.setMessage("delete " + data.get(position).getText() + " ?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();

            }


        });


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newTemplatePopupDialog("Enter template text:", PopupTemplates.this);
            }

        });
    }

    public void newTemplatePopupDialog(String title, final Context activity) {

        final Dialog myDialog = new Dialog(activity);
        myDialog.setContentView(R.layout.popup_templates_dialog);
        myDialog.setTitle(title);
        myDialog.setCancelable(false);

        Button submitButton = (Button) myDialog.findViewById(R.id.submitPopupTemplate);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                templateText = (EditText) myDialog.findViewById(R.id.template_popup_txt);
                SMSOrPopup sp = new SMSOrPopup(1, "Popup template", null, null, null, templateText.getText().toString());
                Model.instance().addSmsOrPop(sp);
                data = Model.instance().getPopupsTemplates();
                adapter.notifyDataSetChanged();
                myDialog.dismiss();
            }
        });

        Button cancelButton = (Button) myDialog.findViewById(R.id.cancelPopupTemplate);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });


        myDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_popup, menu);
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
            return true;
        }
        if (id == R.id.action_sms_templates) {
            Intent intent = new Intent(getApplicationContext(),
                    SmsTemplates.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
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
            if (convertView == null) {
                LayoutInflater inflater = getLayoutInflater();
                convertView = inflater.inflate(R.layout.popup_template_row, null);
            } else {
                Log.d("TAG", "use convert view:" + position);
            }

            TextView text = (TextView) convertView.findViewById(R.id.template_popup_text);

            SMSOrPopup sp = data.get(position);

            text.setText(sp.getText());


            return convertView;
        }
    }
}
