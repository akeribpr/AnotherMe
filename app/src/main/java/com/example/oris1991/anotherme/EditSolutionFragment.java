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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.oris1991.anotherme.ExternalCalendar.Utility;
import com.example.oris1991.anotherme.Model.Entities.SMSOrPopup;
import com.example.oris1991.anotherme.Model.Entities.Solution;
import com.example.oris1991.anotherme.Model.Entities.Task;
import com.example.oris1991.anotherme.Model.Model;

import java.util.List;

/**
 * Created by oris1991 on 06/06/2016.
 */
public class EditSolutionFragment extends Fragment{

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
    Solution sol,old;
    int pos;
    int smsId,popupid;



    interface Delegate{

        public void SaveSolutionEdit(Solution sol,Task task);
        public void CancelSolutionEdit();
        public void showNot(String smsNote);

    }

    public void setSol(Solution sol,Solution old) {
        this.sol = sol;
        this.old=old;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.solution_fragment, container, false);
        final Delegate delegate = (Delegate) getActivity();
        phoneChoose = (TextView) view.findViewById(R.id.phone_text);
        smsTemplateChoose = (TextView) view.findViewById(R.id.sms_text);
        popupTemplateChoose = (TextView) view.findViewById(R.id.popup_text);
        np = (NumberPicker) view.findViewById(R.id.time_before_picker);
        spinnerActions = (Spinner) view.findViewById(R.id.spinner_actions);

        np.setMinValue(0);
        np.setMaxValue(60);
        np.setWrapSelectorWheel(false);

        if (old!=null)
        {
            if(old.getSms()!=null){

            smsId=old.getSms().getId();
            phoneNumber=old.getSms().getSendto();
            phoneName=old.getSms().getSendtoName();
            if (old.getSms().getSendto()!=null&&old.getSms().getSendtoName()!=null){
                phoneChoose.setText(phoneNumber+ " "+phoneName);
                timeBefore=Integer.valueOf(sol.getSms().getTime());
                smsNotification=old.getSms().getText();
                smsTemplateChoose.setText(old.getSms().getText());
            }
            }
            if(old.getPopUp()!=null){
                popupid=old.getPopUp().getId();
                popupData=old.getPopUp().getText();
                popupTemplateChoose.setText(old.getPopUp().getText());
            }

            spinnerActions.setSelection(old.getWhatToDo());
            np.setValue(timeBefore);
        }
//        else
//        {
//            phoneNumber=s.getServerSms().getSendto();
//            phoneName=s.getServerSms().getSendtoName();
//            phoneChoose.setText(phoneNumber + " " + phoneName);
//            timeBefore=Integer.valueOf(s.getServerSms().getTime());
//           /* Utility.taskArry.get(pos).getServerSolution()
//            Utility.taskArry.get(pos).getServerSolution()
//            Utility.taskArry.get(pos).getServerSolution()
//            Utility.taskArry.get(pos).getServerSolution()
//            Utility.taskArry.get(pos).getServerSolution()*/
//        }

        Button doWith= (Button) view.findViewById(R.id.doWithB);
        Button sms= (Button) view.findViewById(R.id.smsB);
        Button popup= (Button) view.findViewById(R.id.popupB);
        Button save= (Button) view.findViewById(R.id.solution_save);
        Button cancel = (Button) view.findViewById(R.id.solution_cancel);


        doWith.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doWithDialog(getActivity());
            }
        });

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    smsDialog(getActivity());
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


                sol = new Solution(1,Model.instance().getSmsOrPopupById(smsId),Model.instance().getSmsOrPopupById(popupid),spinnerActions.getSelectedItemPosition());
             //   delegate.SaveSolutionEdit(sol,task);

               // SMSOrPopup sms = new SMSOrPopup(0,"ServerSMS",phoneNumber,phoneName,String.valueOf(timeBefore),smsNotification);
               // SMSOrPopup popup= new SMSOrPopup (0,"Popup",null,null,String.valueOf(timeBefore),popupData);
                //sol = new ServerSolution(1,sms,popup,spinnerActions.getSelectedItemPosition());

                delegate.SaveSolutionEdit(sol, task);
               // delegate.showNot(smsNotification);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                delegate.CancelSolutionEdit();

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
        if((phoneNumber!=null)){
            if(Model.instance().getSmsForPerson(phoneNumber).size()!=0){
                dataSms = (Model.instance().getSmsForPerson(phoneNumber));
                dataSms.addAll(Model.instance().getSmsTemplatesWithoutPerson());
            }

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
                smsId=dataSms.get(position).getId();
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

        final Dialog myDialog = new Dialog(activity);
        myDialog.setContentView(R.layout.popup_dialog);
        myDialog.setTitle("Pick a popup template:");
        myDialog.setCancelable(true);

        Button cancel= (Button) myDialog.findViewById(R.id.popup_dialog_cancel);

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
                popupData=dataPopup.get(position).getText().toString();
                popupid=dataPopup.get(position).getId();

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

    public void setTask(Task task) {
        this.task=task;
    }
    public void setPos(int pos) {
        this.pos = pos;
    }


    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_defualt, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }


}
