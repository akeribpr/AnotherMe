package com.example.oris1991.anotherme;

import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by oris1991 on 07/05/2016.
 */
public class NewEventFragment extends Fragment {

    interface Delegate{
        public void endFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.new_event_fragment, container, false);
        final TextView eventName = (TextView) view.findViewById(R.id.EventNameEditText);
        final Delegate delegate = (Delegate) getActivity();

        Button save= (Button) view.findViewById(R.id.saveB);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.endFragment();
            }
        });

        return view;
    }
}
