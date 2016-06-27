package com.example.oris1991.anotherme;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oris1991.anotherme.Model.Entities.Solution;
import com.example.oris1991.anotherme.Model.Entities.Task;
import com.example.oris1991.anotherme.Model.Model;
import com.example.oris1991.anotherme.Model.Entities.SMSOrPopup;
import com.example.oris1991.anotherme.Model.sqlLite.ModelSql;

import java.util.List;

/**
 * Created by oris1991 on 19/05/2016.
 */
public class SoluttionFragment  extends Fragment{

    int PICK_CONTACT_REQUEST =1;
    String phoneNumber;
    String phoneName;
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
    String smsNotification,popupData;
    int timeBefore;
    Task task;
    Solution sol;
    int popupid;
    int smsId;



    interface Delegate{

        public void SaveSolution(Solution sol,Task task);
        public void CancelSolution(Task task);
        public void showNot(String smsNote);

    }

    public void setSol(Solution sol) {
        this.sol = sol;
    }

    public Task getTask() {
        return task;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.solution_fragment, container, false);
        setHasOptionsMenu(true);
        final Delegate delegate = (Delegate) getActivity();
        phoneChoose = (TextView) view.findViewById(R.id.phone_text);
        smsTemplateChoose = (TextView) view.findViewById(R.id.sms_text);
        popupTemplateChoose = (TextView) view.findViewById(R.id.popup_text);
        np = (NumberPicker) view.findViewById(R.id.time_before_picker);
        spinnerActions = (Spinner) view.findViewById(R.id.spinner_actions);

        np.setMinValue(0);
        np.setMinValue(0);
        np.setMaxValue(60);
        np.setWrapSelectorWheel(false);

        if (sol!=null)
        {
            if(sol.getSms()!=null) {
                phoneNumber = sol.getSms().getSendto();
                phoneName = sol.getSms().getSendtoName();
                if (sol.getSms().getSendto() != null && sol.getSms().getSendtoName() != null)
                    phoneChoose.setText(phoneNumber + " " + phoneName);
                if (sol.getSms().getTime()!=null)
                    timeBefore = Integer.valueOf(sol.getSms().getTime());
                else
                    timeBefore=0;
                smsNotification = sol.getSms().getText();
                smsTemplateChoose.setText(sol.getSms().getText());
            }
            if(sol.getPopUp()!=null) {
                popupData = sol.getPopUp().getText();
                popupTemplateChoose.setText(sol.getPopUp().getText());
            }
            spinnerActions.setSelection(sol.getWhatToDo());
            np.setValue(timeBefore);
        }

        Button doWith= (Button) view.findViewById(R.id.doWithB);
        final Button sms= (Button) view.findViewById(R.id.smsB);
        final Button popup= (Button) view.findViewById(R.id.popupB);
        Button save= (Button) view.findViewById(R.id.solution_save);
        Button cancel= (Button) view.findViewById(R.id.solution_cancel);



        //    int i = spinnerActions.getSelectedItemPosition();
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
                else{
                    Toast.makeText(getActivity().getApplicationContext(), "please enter contact first", Toast.LENGTH_LONG).show();
                }
            }
        });

        popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupDialog(getActivity());
            }
        });


        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                timeBefore=newVal;

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //SMSOrPopup sms = new SMSOrPopup(0,"SMS",phoneNumber,phoneName,String.valueOf(timeBefore),smsNotification);
                // SMSOrPopup popup= new SMSOrPopup (0,"Popup",null,null,String.valueOf(timeBefore),popupData);
                if(smsId==0||popupid==0) {
                 Toast.makeText(view.getContext(), "user already exists. please choose a different username", Toast.LENGTH_LONG).show();
                }
                else{


                if (smsId!=0)
                    Model.instance().editTimeBefore(smsId,timeBefore);
                sol = new Solution(1,Model.instance().getSmsOrPopupById(smsId),Model.instance().getSmsOrPopupById(popupid),spinnerActions.getSelectedItemPosition());
                delegate.SaveSolution(sol,task);
                //  delegate.showNot(smsNotification);
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                delegate.CancelSolution(task);

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
            int columnNu = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int columnNa = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            phoneNumber=cursor.getString(columnNu);
            phoneName=cursor.getString(columnNa);
        }
    }

    public void doWithDialog( final Context activity) {

        final Dialog myDialog = new Dialog(activity);
        myDialog.setContentView(R.layout.do_with_dialog);
        myDialog.setCancelable(true);
        Window view = ((Dialog)myDialog).getWindow();
        view.setBackgroundDrawableResource(R.color.summer);

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
                phoneChoose.setText(phoneNumber+ " "+phoneName);
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
        myDialog.setCancelable(true);

        Button cancel= (Button) myDialog.findViewById(R.id.sms_dialog_cancel);
        listSms = (ListView) myDialog.findViewById(R.id.sms_dialog_list);
        if((phoneNumber!=null)&&(Model.instance().getSmsForPerson(phoneNumber).size()!=0)){
//            if(){

                dataSms = (Model.instance().getSmsForPerson(phoneNumber));
                dataSms.addAll(Model.instance().getSmsTemplatesWithoutPerson());
           // }

        }
        else{
            dataSms = (Model.instance().getSmsTemplatesWithoutPerson());
        }

        adapterSms = new AdapterSmsList();

        listSms.setAdapter(adapterSms);

        listSms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //add by id to solution sms
                smsTemplateChoose.setText(dataSms.get(position).getText().toString());
                smsNotification=dataSms.get(position).getText().toString();
                smsId = dataSms.get(position).getId();
                SMSOrPopup sms = Model.instance().getSmsOrPopupById(smsId);
                if((sms.getSendtoName()==null)&&(sms.getSendto()==null)){

                    sms.setSendto(phoneNumber);
                    sms.setSendtoName(phoneName);
                    SMSOrPopup newSms = sms;
                    Model.instance().addSmsOrPop(newSms);
                    smsId = Model.instance().numberOfRowe("sms_popup") -1;
                }

                myDialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        myDialog.show();
    }

    public void popupDialog( final Context activity) {

        final Dialog myDialogg = new Dialog(activity);
        myDialogg.setContentView(R.layout.popup_dialog);
        myDialogg.setTitle("Pick a popup template:");
        myDialogg.setCancelable(true);

        Button cancel= (Button) myDialogg.findViewById(R.id.popup_dialog_cancel);

        listPopup = (ListView) myDialogg.findViewById(R.id.popup_dialog_list);
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
                popupData=dataPopup.get(position).getText().toString();
                popupid= dataPopup.get(position).getId();
                // SMSOrPopup s = Model.instance().getSmsOrPopupById(popupid);
                myDialogg.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myDialogg.dismiss();
            }
        });

        myDialogg.show();
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

//            if(sp.getTime().equals("Template")){
//                text.setText(sp.getText()+"   Template");
//            }
//            else {
                text.setText(sp.getText());
          //  }



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

    public void setTask(Task task) {
        this.task=task;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_defualt, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }



}
