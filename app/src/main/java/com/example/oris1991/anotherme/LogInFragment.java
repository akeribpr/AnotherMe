package com.example.oris1991.anotherme;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by oris1991 on 07/05/2016.
 */
public class LogInFragment extends Fragment {

    interface Delegate{
        public void startRegister();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.log_in_fragment, container, false);
        final Delegate delegate = (Delegate) getActivity();

        Button register= (Button) view.findViewById(R.id.registerB);
        Button submit= (Button) view.findViewById(R.id.submitB);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),
                        MainActivity.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.startRegister();
            }
        });

        return view;
    }
}
