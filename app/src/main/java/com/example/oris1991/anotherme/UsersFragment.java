package com.example.oris1991.anotherme;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oris1991.anotherme.Model.Entities.SharePictureOrText;
import com.example.oris1991.anotherme.Model.Entities.Users;
import com.example.oris1991.anotherme.Model.Model;
import com.example.oris1991.anotherme.Model.ModelMain;
import com.example.oris1991.anotherme.PopUpAndSMS.PopupTemplates;
import com.example.oris1991.anotherme.PopUpAndSMS.SmsTemplates;

import java.util.List;

/**
 * Created by Itzik on 04/06/2016.
 */
public class UsersFragment extends Fragment {

    ListView list;
    List<Users> data;
    String idFromList;
    MyAddapter adapter;

    interface UsersFragmentInterface{
        public void upgateUsersFragment();

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.add_users_list_fragment,
                container, false);

        setHasOptionsMenu(true);

        list = (ListView) view.findViewById(R.id.add_user_list);
        data = Model.instance().getUsers();
        adapter = new MyAddapter();
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                idFromList = data.get(position).getname();
                //  Toast.makeText(getActivity().getApplicationContext(),idFromList, Toast.LENGTH_LONG).show();
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                Log.d("log", "Yes button clicked " + idFromList);
                                Users user = new Users(idFromList);
                                Boolean bool = Model.instance().deleteUser(user);
                                Toast.makeText(getActivity().getApplicationContext(), "Done", Toast.LENGTH_LONG).show();
                                UsersFragmentInterface interfaceUpdate = (UsersFragmentInterface) getActivity();
                                interfaceUpdate.upgateUsersFragment();

                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                Log.d("log", "No button clicked " + idFromList);
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("delete " + idFromList + " ?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });


        return view;
    }


    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_share, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_history) {
            Intent intent = new Intent(getActivity(),
                    HistoryActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_sms_templates)
        {
            Intent intent = new Intent(getActivity(),
                    SmsTemplates.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_popup_templates)
        {
            Intent intent = new Intent(getActivity(),
                    PopupTemplates.class);
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
                LayoutInflater inflater = getActivity().getLayoutInflater();
                convertView = inflater.inflate(R.layout.add_users_fragment, null);
                Log.d("TAG", "create view:" + position);

            } else {
                Log.d("TAG", "use convert view:" + position);
            }

            TextView withPerson = (TextView) convertView.findViewById(R.id.user);
            Users st = data.get(position);
            withPerson.setText(st.getname());

            return convertView;
        }
    }
}
