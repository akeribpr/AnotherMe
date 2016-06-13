package com.example.oris1991.anotherme.LogIn;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.oris1991.anotherme.MainActivity;
import com.example.oris1991.anotherme.Model.Entities.LogIn;
import com.example.oris1991.anotherme.R;
import com.example.oris1991.anotherme.Model.Model;

/**
 * Created by oris1991 on 08/05/2016.
 */
public class RegisterFragment extends Fragment {

    interface Delegate{
        public void finishFra();
    }

    EditText usernameText,passwordText,password2Text;
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
        mcontext = getActivity().getApplicationContext();
        //model = ModelMain.instance();
        Button submit= (Button) view.findViewById(R.id.submitB);
        Button exit= (Button) view.findViewById(R.id.exitB);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user =  usernameText.getText().toString();
                pass1 = passwordText.getText().toString();
                pass2 = password2Text.getText().toString();

                if(pass1.compareTo(pass2)!=0)
                {
                    Toast.makeText(mcontext, "The passwords don't match", Toast.LENGTH_LONG).show();
                }
                else
                {
                    if (pass1.length()<1)
                    {
                        Toast.makeText(mcontext, "The password must consist at least 6 digits", Toast.LENGTH_LONG).show();
                    }

                    else
                    {
                        LogIn logIn = new LogIn(user,pass1);
                        if(Model.instance().register(logIn)){
                            Intent intent = new Intent(getActivity(),
                                    MainActivity.class);
                            startActivity(intent);
                            delegate.finishFra();
                        }
                        else{
                            Toast.makeText(mcontext, "user already exists. please choose a different username", Toast.LENGTH_LONG).show();
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
