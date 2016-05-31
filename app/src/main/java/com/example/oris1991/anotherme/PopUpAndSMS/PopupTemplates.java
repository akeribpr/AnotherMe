package com.example.oris1991.anotherme.PopUpAndSMS;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.oris1991.anotherme.R;
import com.example.oris1991.anotherme.Model.Model;
import com.example.oris1991.anotherme.Model.Entities.SMSOrPopup;

import java.util.List;

/**
 * Created by oris1991 on 21/05/2016.
 */
public class PopupTemplates  extends AppCompatActivity{

    ListView list;
    List<SMSOrPopup> data;
    MyAddapter adapter;
    FloatingActionButton fab;
    EditText templateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_templates);

        list = (ListView) findViewById(R.id.pop_up_list);

        data = Model.instance().getPopupsTemplates();

        adapter = new MyAddapter();
        list.setAdapter(adapter);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             /*   Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT_REQUEST);*/
                newTemplatePopupDialog("Enter template text:", PopupTemplates.this);
            }

        });
    }

    public void newTemplatePopupDialog(String title, final Context activity) {

        final Dialog myDialog = new Dialog(activity);
        myDialog.setContentView(R.layout.popup_templates_dialog);
        myDialog.setTitle(title);
        myDialog.setCancelable(false);

        Button submitButton= (Button) myDialog.findViewById(R.id.submitPopupTemplate);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                templateText = (EditText) myDialog.findViewById(R.id.template_popup_txt);
                int i = Model.instance().numberOfRow();
                i++;
                SMSOrPopup sp = new SMSOrPopup(i,"Popup template",null,null, null, templateText.getText().toString());
                Model.instance().add(sp);
                data = Model.instance().getPopupsTemplates();
                adapter.notifyDataSetChanged();
                myDialog.dismiss();
            }
        });

        Button cancelButton= (Button) myDialog.findViewById(R.id.cancelPopupTemplate);
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
