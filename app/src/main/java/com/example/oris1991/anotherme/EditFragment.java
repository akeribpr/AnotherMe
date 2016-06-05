package com.example.oris1991.anotherme;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.oris1991.anotherme.ExternalCalendar.Utility;
import com.example.oris1991.anotherme.Model.Entities.Task;
import com.example.oris1991.anotherme.Model.Model;

import java.util.Calendar;

/**
 * Created by oris1991 on 02/06/2016.
 */
public class EditFragment extends Fragment {

    int pos;

    interface Delegate{
        public void endFragment(int code);
        public void taskWithSolution(Task task);
        public void endFragmentEdit();

    }

    @Nullable
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

        eventTitle.setText(Utility.nameOfEvent.get(pos));
        eventLocation.setText(Utility.locations.get(pos));
        Calendar cls = Calendar.getInstance();
        Calendar cle = Calendar.getInstance();
        cls.setTimeInMillis(Long.valueOf(Utility.startDateAndTime.get(pos)));
        cle.setTimeInMillis(Long.valueOf(Utility.endDateAndTime.get(pos)));
        eventStartDate.setText(cls.get(Calendar.DAY_OF_MONTH) + "/" + String.valueOf(Integer.valueOf(cls.get(Calendar.MONTH)) + 1) + "/" + cls.get(Calendar.YEAR));
        eventStartTime.setText(cls.get(Calendar.HOUR_OF_DAY) + ":" + cls.get(Calendar.MINUTE));
        eventEndDate.setText(cle.get(Calendar.DAY_OF_MONTH) + "/" + String.valueOf(Integer.valueOf(cle.get(Calendar.MONTH)) + 1) + "/" + cle.get(Calendar.YEAR));
        eventEndTime.setText(cle.get(Calendar.HOUR_OF_DAY) + ":" + cle.get(Calendar.MINUTE));


        Button toDo= (Button) view.findViewById(R.id.toDoButton);
        Button save= (Button) view.findViewById(R.id.saveB);
        Button cancel= (Button) view.findViewById(R.id.cancelB);

        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                delegate.endFragmentEdit();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           /*     long startMillis = 0;
                long endMillis = 0;
                Calendar beginTime = Calendar.getInstance();
                beginTime.set(eventStartDate.getYear(),eventStartDate.getMonth(), eventStartDate.getDay(), eventStartTime.getHour(),eventEndTime.getMinutes());
                startMillis = beginTime.getTimeInMillis();
                Calendar endTime = Calendar.getInstance();
                endTime.set(eventEndDate.getYear(), eventStartDate.getMonth(), eventStartDate.getDay(), eventEndTime.getHour(),eventEndTime.getMinutes());
                endMillis = endTime.getTimeInMillis();

                final Task newTask = new Task(1,eventTitle.getText().toString(),startMillis,endMillis,eventLocation.getText().toString());
                newTask.setSolution(sol);
                Model.instance().addTaskWithSolution(newTask);*/

                delegate.endFragmentEdit();
            }
        });


        toDo.setOnClickListener(new View.OnClickListener(){
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
                Task newTask = new Task(1,eventTitle.getText().toString(),startMillis,endMillis,eventLocation.getText().toString());
                delegate.taskWithSolution(newTask);

            }
        });
        return view;
    }

    public void setPosition (int pos)
    {
        this.pos=pos;
    }
}
