package com.example.oris1991.anotherme.LogIn;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.oris1991.anotherme.MainActivity;
import com.example.oris1991.anotherme.Model.LogIn;
import com.example.oris1991.anotherme.R;
import com.example.oris1991.anotherme.sqlLite.Model;


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
   // private ModelMain model;

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
       // model = ModelMain.instance().
        saveLogin = loginPreferences.getBoolean("saveLogin", false);
//        if (saveLogin == true) {
//            editTextUsername.setText(loginPreferences.getString("username", ""));
//            editTextPassword.setText(loginPreferences.getString("password", ""));
//            saveLoginCheckBox.setChecked(true);
//        }
      //  LogIn logIn = new LogIn(editTextUsername.getText().toString(),editTextPassword.getText().toString());

//        if(ModelMain.instance().checkUser(logIn)){
////        if(model.checkUser(logIn)){
//            startActivity();
//        }
//        if(editTextUsername.getText().toString().compareTo("admin")==0&&editTextPassword.getText().toString().compareTo("1234")==0)
//        {
//
//            startActivity();
//        }



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

            if (saveLoginCheckBox.isChecked()) {
                loginPrefsEditor.putBoolean("saveLogin", true);
                loginPrefsEditor.putString("username", username);
                loginPrefsEditor.putString("password", password);
                loginPrefsEditor.commit();
            } else {
                loginPrefsEditor.clear();
                loginPrefsEditor.commit();
            }
            LogIn logIn = new LogIn(editTextUsername.getText().toString(),editTextPassword.getText().toString());
            if(Model.instance().checkUser(logIn)){
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
