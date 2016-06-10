package com.example.oris1991.anotherme;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.oris1991.anotherme.Model.Model;
import com.example.oris1991.anotherme.Model.Entities.SMSOrPopup;
import com.example.oris1991.anotherme.PopUpAndSMS.PopupTemplates;
import com.example.oris1991.anotherme.PopUpAndSMS.SmsTemplates;

import java.util.List;

/**
 * Created by oris1991 on 19/05/2016.
 */
public class HistoryActivity extends AppCompatActivity {

    ListView list;
    List<SMSOrPopup> data;
    MyAddapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.history_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list = (ListView) findViewById(R.id.PS_listview);

        data = Model.instance().getSmsOrPopups();

        adapter = new MyAddapter();
        list.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

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
            if(convertView == null){
                LayoutInflater inflater = getLayoutInflater();
                convertView = inflater.inflate(R.layout.history_row, null);
            }else{
                Log.d("TAG", "use convert view:" + position);
            }

            TextView type = (TextView) convertView.findViewById(R.id.row_type);
            TextView time = (TextView) convertView.findViewById(R.id.row_time);
            TextView sent_to = (TextView) convertView.findViewById(R.id.row_sent_to);
            TextView text = (TextView) convertView.findViewById(R.id.row_text);

            SMSOrPopup sp = data.get(position);
            //checkBox.setTag(new Integer(position));

            type.setText(sp.getType());
            time.setText(sp.getTime());
            sent_to.setText(sp.getSendto());
            text.setText(sp.getText());


            return convertView;
        }
    }

}
