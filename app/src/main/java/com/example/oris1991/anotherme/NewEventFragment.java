package com.example.oris1991.anotherme;

import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.oris1991.anotherme.Model.Event;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by oris1991 on 07/05/2016.
 */
public class NewEventFragment extends Fragment {

    Spinner spinner;

    interface Delegate{
        public void endFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.new_event_fragment, container, false);
        final Delegate delegate = (Delegate) getActivity();
        final TextView eventTitle = (TextView) view.findViewById(R.id.EventTitleEditText);
        final TextView eventDesc = (TextView) view.findViewById(R.id.EventDescEditText);
        final DateEditText eventStartDate = (DateEditText) view.findViewById(R.id.startDateEditText);
        final DateEditText eventEndDate = (DateEditText) view.findViewById(R.id.endDateEditText);
        final TimeEditText eventStartTime = (TimeEditText) view.findViewById(R.id.startTimeEditText);
        final TimeEditText eventEndTime = (TimeEditText) view.findViewById(R.id.endTimeEditText);
        final TextView eventLocation = (TextView) view.findViewById(R.id.EventLocationEditText);

        spinner = (Spinner) view.findViewById(R.id.solution_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.solution_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //spinner.getSelectedItem().toString()
        Button save= (Button) view.findViewById(R.id.saveB);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long startMillis = 0;
                long endMillis = 0;
                Calendar beginTime = Calendar.getInstance();
                beginTime.set(eventStartDate.getYear(),eventStartDate.getMonth(), eventStartDate.getDay(), eventStartTime.getHour(),eventEndTime.getMinutes());
                startMillis = beginTime.getTimeInMillis();
                Calendar endTime = Calendar.getInstance();
                endTime.set(eventEndDate.getYear(), eventStartDate.getMonth(), eventStartDate.getDay(), eventEndTime.getHour(),eventEndTime.getMinutes());
                endMillis = endTime.getTimeInMillis();

                final Event newEvent= new Event(eventTitle.getText().toString(),eventDesc.getText().toString(),startMillis,endMillis,eventLocation.getText().toString(),spinner.getSelectedItem().toString());

                Utility.insertCalendarEvent(getActivity().getApplicationContext(),newEvent);
                delegate.endFragment();
            }
        });

        return view;
    }
}
