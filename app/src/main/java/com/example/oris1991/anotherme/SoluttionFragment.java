package com.example.oris1991.anotherme;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.oris1991.anotherme.sqlLite.Model;
import com.example.oris1991.anotherme.Model.SMSOrPopup;

import java.util.List;

/**
 * Created by oris1991 on 19/05/2016.
 */
public class SoluttionFragment  extends Fragment{

    int PICK_CONTACT_REQUEST =1;
    String phoneNumber;
    ListView listSms;
    List<SMSOrPopup> dataSms;
    AdapterSmsList adapterSms;
    ListView listPopup;
    List<SMSOrPopup> dataPopup;
    AdapterPopupList adapterPopup;
    TextView popupTemplateChoose;
    TextView  smsTemplateChoose;
    TextView phoneChoose;
    TextView timeBeforeMissionStart;
    Spinner spinnerActions ;
    NumberPicker np;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.solution_fragment, container, false);

        phoneChoose = (TextView) view.findViewById(R.id.phone_text);
        smsTemplateChoose = (TextView) view.findViewById(R.id.sms_text);
        popupTemplateChoose = (TextView) view.findViewById(R.id.popup_text);
        np = (NumberPicker) view.findViewById(R.id.time_before_picker);

        Button doWith= (Button) view.findViewById(R.id.doWithB);
        Button sms= (Button) view.findViewById(R.id.smsB);
        Button popup= (Button) view.findViewById(R.id.popupB);

        spinnerActions = (Spinner) view.findViewById(R.id.spinner_actions);
       //spinnerValue=String.valueOf(spinnerActions.getSelectedItem());

        doWith.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doWithDialog(getActivity());
            }
        });

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (phoneNumber != null)
                    smsDialog(getActivity());
            }
        });

        popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupDialog(getActivity());
            }
        });

        np.setMinValue(0);
        np.setMaxValue(60);
        np.setWrapSelectorWheel(false);

        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                // TODO Auto-generated method stub

            }
        });





        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request it is that we're responding to
        if (requestCode == PICK_CONTACT_REQUEST && resultCode == Activity.RESULT_OK) {
            Uri contactUri = data.getData();
            Cursor cursor = getActivity().getContentResolver().query(contactUri, null, null, null, null);
            cursor.moveToFirst();
            int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            phoneNumber=cursor.getString(column);
        }
    }

    public void doWithDialog( final Context activity) {

        final Dialog myDialog = new Dialog(activity);
        myDialog.setContentView(R.layout.do_with_dialog);
        myDialog.setCancelable(false);


        Button pickOldContact= (Button) myDialog.findViewById(R.id.old_contact);
        pickOldContact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        Button pickNewContact= (Button) myDialog.findViewById(R.id.new_contact);
        pickNewContact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT_REQUEST);

            }
        });

        Button submitButton= (Button) myDialog.findViewById(R.id.submitDoWith);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                phoneChoose.setText(phoneNumber);
                myDialog.dismiss();
            }
        });

        Button cancelButton= (Button) myDialog.findViewById(R.id.cancelDoWith);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });


        myDialog.show();
    }

    public void smsDialog( final Context activity) {

        final Dialog myDialog = new Dialog(activity);
        myDialog.setContentView(R.layout.sms_dialog);
        myDialog.setTitle("Pick a sms template:");
        myDialog.setCancelable(false);

        listSms = (ListView) myDialog.findViewById(R.id.sms_dialog_list);
        dataSms = Model.instance().getSmsForPerson(phoneNumber);

        //SMSOrPopup ns = new SMSOrPopup("Sms template",null,null,"nothing");
        //dataSms.add(ns);
        adapterSms = new AdapterSmsList();

        listSms.setAdapter(adapterSms);

        listSms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //add by id to solution sms
                smsTemplateChoose.setText(dataSms.get(position).getText().toString());
                myDialog.dismiss();
            }
        });



        myDialog.show();
    }

    public void popupDialog( final Context activity) {

        final Dialog myDialog = new Dialog(activity);
        myDialog.setContentView(R.layout.popup_dialog);
        myDialog.setTitle("Pick a popup template:");
        myDialog.setCancelable(false);

        listPopup = (ListView) myDialog.findViewById(R.id.popup_dialog_list);
        dataPopup = Model.instance().getPopupsTemplates();
//        SMSOrPopup np = new SMSOrPopup("Popup template",null,null,"nothing");
//        dataPopup.add(np);
        adapterPopup = new AdapterPopupList();

        listPopup.setAdapter(adapterPopup);

        listPopup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                popupTemplateChoose.setText(dataPopup.get(position).getText().toString());
                myDialog.dismiss();
            }
        });



        myDialog.show();
    }

    class AdapterSmsList extends BaseAdapter {


        @Override
        public int getCount() {
            return dataSms.size();
        }

        @Override
        public Object getItem(int position) {
            return dataSms.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView,
                            ViewGroup parent) {
            if(convertView == null){
                LayoutInflater inflater = getActivity().getLayoutInflater();
                convertView = inflater.inflate(R.layout.sms_row, null);
            }else{
                Log.d("TAG", "use convert view:" + position);
            }

            TextView text = (TextView) convertView.findViewById(R.id.smsName);

            SMSOrPopup sp = dataSms.get(position);

            text.setText(sp.getText());


            return convertView;
        }
    }

    class AdapterPopupList extends BaseAdapter {


        @Override
        public int getCount() {
            return dataPopup.size();
        }

        @Override
        public Object getItem(int position) {
            return dataPopup.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView,
                            ViewGroup parent) {
            if(convertView == null){
                LayoutInflater inflater = getActivity().getLayoutInflater();
                convertView = inflater.inflate(R.layout.popup_row, null);
            }else{
                Log.d("TAG", "use convert view:" + position);
            }

            TextView text = (TextView) convertView.findViewById(R.id.popupName);

            SMSOrPopup sp = dataPopup.get(position);

            text.setText(sp.getText());


            return convertView;
        }
    }


}
