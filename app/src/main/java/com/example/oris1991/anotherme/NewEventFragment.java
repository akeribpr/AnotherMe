package com.example.oris1991.anotherme;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.oris1991.anotherme.ExternalCalendar.Utility;
import com.example.oris1991.anotherme.Model.Entities.Task;

import java.util.Calendar;

/**
 * Created by oris1991 on 07/05/2016.
 */
public class NewEventFragment extends Fragment {

    Spinner spinner;

    interface Delegate{
        public void endFragment(int code);
        public void taskWithSolution(Task task);
        public void endFragmentTask();



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.new_event_fragment, container, false);
        final Delegate delegate = (Delegate) getActivity();
        final TextView eventTitle = (TextView) view.findViewById(R.id.EventTitleEditText);
        final DateEditText eventStartDate = (DateEditText) view.findViewById(R.id.startDateEditText);
        final DateEditText eventEndDate = (DateEditText) view.findViewById(R.id.endDateEditText);
        final TimeEditText eventStartTime = (TimeEditText) view.findViewById(R.id.startTimeEditText);
        final TimeEditText eventEndTime = (TimeEditText) view.findViewById(R.id.endTimeEditText);
        final TextView eventLocation = (TextView) view.findViewById(R.id.EventLocationEditText);
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.autoGenerateCheckBox);


        Button toDo= (Button) view.findViewById(R.id.toDoButton);
        Button save= (Button) view.findViewById(R.id.saveB);
        Button cancel= (Button) view.findViewById(R.id.cancelB);

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

                final Task newTask = new Task(1,eventTitle.getText().toString(),startMillis,endMillis,eventLocation.getText().toString());

                Utility.insertCalendarEvent(getActivity().getApplicationContext(), newTask);
                delegate.endFragmentTask();
                //delegate.endFragment(1);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                delegate.endFragmentTask();
                //delegate.endFragment(1);

            }
        });

        toDo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               //Tas task =null;
               // delegate.taskWithSolution();
               // delegate.endFragment(2);

            }
        });



        return view;
    }
}
