package com.example.oris1991.anotherme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.oris1991.anotherme.Model.Model;
import com.example.oris1991.anotherme.Model.Entities.SMSOrPopup;

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

        list = (ListView) findViewById(R.id.PS_listview);

        data = Model.instance().getSmsOrPopups();

        adapter = new MyAddapter();
        list.setAdapter(adapter);

        int i = Model.instance().numberOfRow();
        i++;
        SMSOrPopup sp = new SMSOrPopup(i,"SMS","0547297791","Itzik","10:00","la la la la la la la la la la la la la la la la la");
        Model.instance().add(sp);
        data = Model.instance().getSmsOrPopups();
        adapter.notifyDataSetChanged();
        SMSOrPopup sp2 = new SMSOrPopup(i+1,"Popup",null,null,"10:00","la la la la la la la la la la la la la la la la la");
        Model.instance().add(sp2);
        data = Model.instance().getSmsOrPopups();
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
