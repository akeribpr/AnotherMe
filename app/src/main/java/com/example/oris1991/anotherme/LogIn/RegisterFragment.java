package com.example.oris1991.anotherme.LogIn;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.oris1991.anotherme.MainActivity;
import com.example.oris1991.anotherme.Model.Entities.LogIn;
import com.example.oris1991.anotherme.R;
import com.example.oris1991.anotherme.Model.Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by oris1991 on 08/05/2016.
 */
public class RegisterFragment extends Fragment {

    interface Delegate{
        public void finishFra();
    }

    EditText usernameText,passwordText,password2Text;
    ProgressBar progressBar;
    String pass1,pass2,user;
    Context mcontext;
    Delegate delegate;
    private Model model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.register_fragment, container, false);
        delegate = (Delegate) getActivity();
        usernameText = (EditText)view.findViewById(R.id.usernameEditText);
        passwordText = (EditText)view.findViewById(R.id.passwordEditText);
        password2Text = (EditText)view.findViewById(R.id.password2EditText);
        progressBar = (ProgressBar) view.findViewById(R.id.sdProgressBar);
        mcontext = getActivity().getApplicationContext();


        Button submit= (Button) view.findViewById(R.id.submitB);
        Button exit= (Button) view.findViewById(R.id.exitB);
        progressBar.setVisibility(View.INVISIBLE);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                user =  usernameText.getText().toString();
                pass1 = passwordText.getText().toString();
                pass2 = password2Text.getText().toString();

                if(pass1.compareTo(pass2)!=0)
                {

                    Toast.makeText(mcontext, "The passwords don't match", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else
                {
                    progressBar.setVisibility(View.VISIBLE);// check length of password
                    if (pass1.length()<1)
                    {

                        Toast.makeText(mcontext, "The password must consist at least 6 digits", Toast.LENGTH_LONG).show();

                    }

                    else
                    {
                        progressBar.setVisibility(View.VISIBLE);
                        LogIn logIn = new LogIn(user,pass1);

                        if(Model.instance().register(logIn)){

                            DateFormat df = new SimpleDateFormat(" d MMM yyyy");
                            String date = df.format(Calendar.getInstance().getTime());

                            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
                            SharedPreferences.Editor registerTime = prefs.edit();

                            registerTime.putString("regTime",date);
                            registerTime.commit();


                            Intent intent = new Intent(getActivity(),
                                    MainActivity.class);

                            startActivity(intent);
                            delegate.finishFra();
                        }
                        else{

                            Toast.makeText(mcontext, "user already exists. please choose a different username", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }

                    }
                }
            }
        });


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                delegate.finishFra();
            }
        });

        return view;
    }
}
