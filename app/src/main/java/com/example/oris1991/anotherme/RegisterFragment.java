package com.example.oris1991.anotherme;

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

/**
 * Created by oris1991 on 08/05/2016.
 */
public class RegisterFragment extends Fragment {

    interface Delegate{
        public void finishFra();
    }

    EditText usernameText,passwordText,password2Text;
    String pass1,pass2;
    Context mcontext;
    Delegate delegate;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.register_fragment, container, false);
        delegate = (Delegate) getActivity();
        usernameText = (EditText)view.findViewById(R.id.usernameEditText);
        passwordText = (EditText)view.findViewById(R.id.passwordEditText);
        password2Text = (EditText)view.findViewById(R.id.password2EditText);
        mcontext = getActivity().getApplicationContext();

        Button submit= (Button) view.findViewById(R.id.submitB);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass1 = passwordText.getText().toString();
                pass2 = password2Text.getText().toString();

                if(pass1.compareTo(pass2)!=0)
                {
                    Toast.makeText(mcontext, "The passwords don't match", Toast.LENGTH_LONG).show();
                }
                else
                {
                    if (pass1.length()<6)
                    {
                        Toast.makeText(mcontext, "The password must consist at least 6 digits", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Intent intent = new Intent(getActivity(),
                                MainActivity.class);
                        startActivity(intent);
                        delegate.finishFra();
                    }
                }
            }
        });

        return view;
    }
}
