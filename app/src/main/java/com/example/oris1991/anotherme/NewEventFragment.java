package com.example.oris1991.anotherme;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oris1991.anotherme.Model.Entities.Solution;
import com.example.oris1991.anotherme.Model.Entities.Task;
import com.example.oris1991.anotherme.Model.Model;
import com.example.oris1991.anotherme.Model.ModelServer.TaskModelServer;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by oris1991 on 07/05/2016.
 */
public class NewEventFragment extends Fragment {

    Solution sol;
    Task task;
    int day;
    int month;
    int year;



    interface Delegate{

        public void taskWithSolution(Task task,Solution sol);
        public void endFragmentTask();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.new_event_fragment, container, false);
        setHasOptionsMenu(true);
        final Delegate delegate = (Delegate) getActivity();
        final TextView eventTitle = (TextView) view.findViewById(R.id.EventTitleEditText);
        final DateEditText eventStartDate = (DateEditText) view.findViewById(R.id.startDateEditText);
        final DateEditText eventEndDate = (DateEditText) view.findViewById(R.id.endDateEditText);
        final TimeEditText eventStartTime = (TimeEditText) view.findViewById(R.id.startTimeEditText);
        final TimeEditText eventEndTime = (TimeEditText) view.findViewById(R.id.endTimeEditText);
        final TextView eventLocation = (TextView) view.findViewById(R.id.EventLocationEditText);

        if (task!=null)
        {
            eventTitle.setText(task.getTitle());
            eventLocation.setText(task.getLocation());
            Calendar cls = Calendar.getInstance();
            cls.setTimeInMillis(task.getStartTime());
            Calendar cle = Calendar.getInstance();
            cle.setTimeInMillis(task.getEndTime());
            eventStartDate.setText(cls.get(Calendar.DAY_OF_MONTH) + "/" + String.valueOf(Integer.valueOf(cls.get(Calendar.MONTH)) + 1) + "/" + cls.get(Calendar.YEAR));
            if (cls.get(Calendar.MINUTE)/10==0)
                eventStartTime.setText(cls.get(Calendar.HOUR_OF_DAY) + ":0" + cls.get(Calendar.MINUTE));
            else
                eventStartTime.setText(cls.get(Calendar.HOUR_OF_DAY) + ":" + cls.get(Calendar.MINUTE));
            eventEndDate.setText(cle.get(Calendar.DAY_OF_MONTH) + "/" + String.valueOf(Integer.valueOf(cle.get(Calendar.MONTH)) + 1) + "/" + cle.get(Calendar.YEAR));
            if (cle.get(Calendar.MINUTE)/10==0)
                eventEndTime.setText(cle.get(Calendar.HOUR_OF_DAY) + ":0" + cle.get(Calendar.MINUTE));
            else
                eventEndTime.setText(cle.get(Calendar.HOUR_OF_DAY) + ":" + cle.get(Calendar.MINUTE));
            eventStartDate.set(cls.get(Calendar.YEAR), Integer.valueOf(cls.get(Calendar.MONTH)) , cls.get(Calendar.DAY_OF_MONTH));
            eventEndDate.set(cle.get(Calendar.YEAR), Integer.valueOf(cle.get(Calendar.MONTH))  ,cle.get(Calendar.DAY_OF_MONTH));
            eventStartTime.set(cls.get(Calendar.HOUR_OF_DAY),cls.get(Calendar.MINUTE));
            eventEndTime.set(cle.get(Calendar.HOUR_OF_DAY),cle.get(Calendar.MINUTE));
        }
        else
        {
            eventStartDate.setText(day+"/"+String.valueOf(month+1)+"/"+year);
            eventStartDate.set(year,month,day);
            eventEndDate.setText(day+"/"+String.valueOf(month+1)+"/"+year);
            eventEndDate.set(year,month,day);
        }

        Button toDo= (Button) view.findViewById(R.id.toDoButton);
        Button save= (Button) view.findViewById(R.id.saveB);
        Button cancel= (Button) view.findViewById(R.id.cancelB);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = getActivity().getApplicationContext();
                String pattern = "^[A-Za-z ]+$";

                Pattern u = Pattern.compile(pattern);
                Matcher s = u.matcher(eventTitle.getText().toString());
                if (s.find())
                    Toast.makeText(context, "you entered english text- algorithm won't work ", Toast.LENGTH_LONG).show();
                // is English input


                long startMillis = 0;
                long endMillis = 0;
                Calendar beginTime = Calendar.getInstance();
                beginTime.set(eventStartDate.getYear(),eventStartDate.getMonth(), eventStartDate.getDay(), eventStartTime.getHour(),eventStartTime.getMinutes());
                startMillis = beginTime.getTimeInMillis();
                Calendar endTime = Calendar.getInstance();
                endTime.set(eventEndDate.getYear(), eventEndDate.getMonth(), eventEndDate.getDay(), eventEndTime.getHour(),eventEndTime.getMinutes());
                endMillis = endTime.getTimeInMillis();

                final Task newTask = new Task(1,eventTitle.getText().toString(),startMillis,endMillis,eventLocation.getText().toString());
                if (sol !=null)
                    newTask.setSolution(sol);

                Model.instance().addTaskWithSolution(newTask);

                delegate.endFragmentTask();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                delegate.endFragmentTask();
            }
        });

        toDo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                long startMillis = 0;
                long endMillis = 0;
                Calendar beginTime = Calendar.getInstance();
                beginTime.set(eventStartDate.getYear(),eventStartDate.getMonth(), eventStartDate.getDay(), eventStartTime.getHour(),eventStartTime.getMinutes());
                startMillis = beginTime.getTimeInMillis();
                Calendar endTime = Calendar.getInstance();
                endTime.set(eventEndDate.getYear(), eventEndDate.getMonth(), eventEndDate.getDay(), eventEndTime.getHour(),eventEndTime.getMinutes());
                endMillis = endTime.getTimeInMillis();
                Task newTask = new Task(1,eventTitle.getText().toString(),startMillis,endMillis,eventLocation.getText().toString());
                delegate.taskWithSolution(newTask,sol);

            }
        });



        return view;
    }

    public void setTask(Task task) {

        this.task=task;
    }

    public void setDate(int day, int month, int year) {

        this.day=day;
        this.month=month;
        this.year=year;
    }

    public void setSolution(Solution sol)
    {
        this.sol=sol;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_defualt, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }


}
