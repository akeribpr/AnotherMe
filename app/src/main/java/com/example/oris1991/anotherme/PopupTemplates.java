package com.example.oris1991.anotherme;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oris1991.anotherme.Model.Model;
import com.example.oris1991.anotherme.Model.SMSOrPopup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * Created by oris1991 on 21/05/2016.
 */
public class PopupTemplates  extends AppCompatActivity{

    ListView list;
    List<SMSOrPopup> data;
    MyAddapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_templates);

        list = (ListView) findViewById(R.id.pop_up_list);

        data = Model.instance().getSmsOrPopups();

        adapter = new MyAddapter();
        list.setAdapter(adapter);

        SMSOrPopup sp = new SMSOrPopup("Popup template",null,null,"sorry i am late");
        Model.instance().add(sp);
        data = Model.instance().getPopupsTemplates();
        adapter.notifyDataSetChanged();


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
                convertView = inflater.inflate(R.layout.popup_template_row, null);
            }else{
                Log.d("TAG", "use convert view:" + position);
            }

            TextView text = (TextView) convertView.findViewById(R.id.template_popup_text);

            SMSOrPopup sp = data.get(position);

            text.setText(sp.getText());


            return convertView;
        }
    }
}
