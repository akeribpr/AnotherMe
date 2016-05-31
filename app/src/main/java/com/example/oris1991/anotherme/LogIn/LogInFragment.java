package com.example.oris1991.anotherme.LogIn;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.oris1991.anotherme.MainActivity;
import com.example.oris1991.anotherme.Model.Entities.LogIn;
import com.example.oris1991.anotherme.R;
import com.example.oris1991.anotherme.Model.Model;


/**
 * Created by oris1991 on 07/05/2016.
 */
public class LogInFragment extends Fragment implements View.OnClickListener {

    private String username,password;
    private Button ok;
    private EditText editTextUsername,editTextPassword;
    private CheckBox saveLoginCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    Delegate delegate;

    interface Delegate{
        public void startRegister();
        public void finishFra();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.login_fragment, container, false);
        delegate = (Delegate) getActivity();

        Button register= (Button) view.findViewById(R.id.buttonRegistar);
        ok = (Button)view.findViewById(R.id.buttonLogin);
        ok.setOnClickListener(this);
        editTextUsername = (EditText)view.findViewById(R.id.editTextUsername);
        editTextPassword = (EditText)view.findViewById(R.id.editTextPassword);
        saveLoginCheckBox = (CheckBox)view.findViewById(R.id.saveLoginCheckBox);
        loginPreferences = getActivity().getSharedPreferences("loginPrefs",  Context.MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            editTextUsername.setText(Model.instance().getUser().getPersonId());
            editTextPassword.setText(Model.instance().getUser().getPassword());
            saveLoginCheckBox.setChecked(true);
            LogIn logIn = new LogIn(editTextUsername.getText().toString(),editTextPassword.getText().toString());
            if(Model.instance().checkIfUserExist(logIn)){
                startActivity();
            }
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.startRegister();
            }
        });
        return view;
    }


    public void onClick(View view) {
        if (view == ok) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editTextUsername.getWindowToken(), 0);

            username = editTextUsername.getText().toString();
            password = editTextPassword.getText().toString();
            LogIn logIn = new LogIn(username,password);
            if(Model.instance().checkIfUserExist(logIn)){
            if (saveLoginCheckBox.isChecked()) {
                loginPrefsEditor.putBoolean("saveLogin", true);
                loginPrefsEditor.commit();
            } else {
                loginPrefsEditor.clear();
                loginPrefsEditor.commit();
            }
              startActivity();
            }
            else
            {
                Context context = getActivity().getApplicationContext();
                Toast.makeText(context, "Wrong username or password" , Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void startActivity() {
        Intent intent = new Intent(getActivity(),MainActivity.class);
        startActivity(intent);
        delegate.finishFra();
    }
}
