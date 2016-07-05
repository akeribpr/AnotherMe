package com.example.oris1991.anotherme.LogIn;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.oris1991.anotherme.R;

/**
 * Created by oris1991 on 07/05/2016.
 */
public class LogInActivity extends Activity implements LogInFragment.Delegate, RegisterFragment.Delegate {

    LogInFragment logInFra;
    RegisterFragment registerFra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrance);
        logInFra = new LogInFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.Log_in_frag_container, logInFra);
        transaction.show(logInFra);
        transaction.commit();
    }

    @Override
    public void startRegister() {
        registerFra = new RegisterFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.Register_frag_container, registerFra);
        transaction.hide(logInFra);
        transaction.show(registerFra);
        transaction.commit();
    }

    @Override
    public void finishFra() {
        finish();
    }
}
