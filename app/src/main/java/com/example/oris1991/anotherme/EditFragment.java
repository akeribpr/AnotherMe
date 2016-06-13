package com.example.oris1991.anotherme;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.oris1991.anotherme.ExternalCalendar.Utility;
import com.example.oris1991.anotherme.Model.Entities.Solution;
import com.example.oris1991.anotherme.Model.Entities.Task;
import com.example.oris1991.anotherme.Model.Model;

import java.util.Calendar;

/**
 * Created by oris1991 on 02/06/2016.
 */
public class EditFragment extends Fragment {

    int pos;
    Solution solutionAfterEditSolution;
    Task task;





    interface Delegate{
        public void taskEditWithSolution(Task task,Solution sol,Solution s);
        public void endFragmentEdit();

    }

    @Nullable
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
            eventEndDate.setText(cle.get(Calendar.DAY_OF_MONTH) + "/" + String.valueOf(Integer.valueOf(cle.get(Calendar.MONTH)) + 1) + "/" + cle.get(Calendar.YEAR));
            eventStartTime.setText(cls.get(Calendar.HOUR_OF_DAY) + ":" + cls.get(Calendar.MINUTE));
            eventEndTime.setText(cle.get(Calendar.HOUR_OF_DAY) + ":" + cle.get(Calendar.MINUTE));
            eventStartDate.set(cls.get(Calendar.YEAR), Integer.valueOf(cls.get(Calendar.MONTH)) , cls.get(Calendar.DAY_OF_MONTH));
            eventEndDate.set(cle.get(Calendar.YEAR), Integer.valueOf(cle.get(Calendar.MONTH))  ,cle.get(Calendar.DAY_OF_MONTH));
            eventStartTime.set(cls.get(Calendar.HOUR_OF_DAY),cls.get(Calendar.MINUTE));
            eventEndTime.set(cle.get(Calendar.HOUR_OF_DAY),cle.get(Calendar.MINUTE));
        }
        else
        {
            int  c = Integer.valueOf(Utility.eventId.get(pos));
            eventTitle.setText(Utility.nameOfEvent.get(pos));
            eventLocation.setText(Utility.locations.get(pos));
            Calendar cls = Calendar.getInstance();
            Calendar cle = Calendar.getInstance();
            cls.setTimeInMillis(Long.valueOf(Utility.startDateAndTime.get(pos)));
            cle.setTimeInMillis(Long.valueOf(Utility.endDateAndTime.get(pos)));
            eventStartDate.setText(cls.get(Calendar.DAY_OF_MONTH) + "/" + String.valueOf(Integer.valueOf(cls.get(Calendar.MONTH)) + 1) + "/" + cls.get(Calendar.YEAR));
            if (eventStartTime.getMinutes()/10==0)
                eventStartTime.setText(cls.get(Calendar.HOUR_OF_DAY) + ":0" + cls.get(Calendar.MINUTE));
            else
                eventStartTime.setText(cls.get(Calendar.HOUR_OF_DAY) + ":" + cls.get(Calendar.MINUTE));
            eventEndDate.setText(cle.get(Calendar.DAY_OF_MONTH) + "/" + String.valueOf(Integer.valueOf(cle.get(Calendar.MONTH)) + 1) + "/" + cle.get(Calendar.YEAR));
            if (eventEndTime.getMinutes()/10==0)
                eventEndTime.setText(cle.get(Calendar.HOUR_OF_DAY) + ":0" + cle.get(Calendar.MINUTE));
            else
                eventEndTime.setText(cle.get(Calendar.HOUR_OF_DAY) + ":" + cle.get(Calendar.MINUTE));
            eventStartDate.set(cls.get(Calendar.YEAR), Integer.valueOf(cls.get(Calendar.MONTH)), cls.get(Calendar.DAY_OF_MONTH));
            eventEndDate.set(cle.get(Calendar.YEAR), Integer.valueOf(cle.get(Calendar.MONTH)), cle.get(Calendar.DAY_OF_MONTH));
            eventStartTime.set(cls.get(Calendar.HOUR_OF_DAY), cls.get(Calendar.MINUTE));
            eventEndTime.set(cle.get(Calendar.HOUR_OF_DAY), cle.get(Calendar.MINUTE));
        }

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
                if(task!=null){
                    Model.instance().deleteTask(task.getId());
                    if(task.getSolution()!=null){
                        Model.instance().deleteSolution(task.getId());
                    }

                    long startMillis = 0;
                    long endMillis = 0;
                    Calendar beginTime = Calendar.getInstance();
                    beginTime.set(eventStartDate.getYear(),eventStartDate.getMonth(), eventStartDate.getDay(), eventStartTime.getHour(),eventStartTime.getMinutes());
                    startMillis = beginTime.getTimeInMillis();
                    Calendar endTime = Calendar.getInstance();
                    endTime.set(eventEndDate.getYear(), eventEndDate.getMonth(), eventEndDate.getDay(), eventEndTime.getHour(),eventEndTime.getMinutes());
                    endMillis = endTime.getTimeInMillis();

                    final Task newTask = new Task(1,eventTitle.getText().toString(),startMillis,endMillis,eventLocation.getText().toString());
                    if (solutionAfterEditSolution !=null){
                        newTask.setSolution(solutionAfterEditSolution);
                    }
                    else {
                        newTask.setSolution(task.getSolution());
                    }
                    Model.instance().addTaskWithSolution(newTask);

                    delegate.endFragmentEdit();

                }
                else{


                   int  c = Integer.valueOf(Utility.eventId.get(pos));
                Task taskTest= Model.instance().getTask(c);
                    if(Model.instance().getSolution(c)!=null && solutionAfterEditSolution!=null){
                        Model.instance().deleteTask(Integer.valueOf(Utility.eventId.get(pos)));
                        Model.instance().deleteSolution(Integer.valueOf(Utility.eventId.get(pos)));
                    }
                   else{
                        Model.instance().deleteTask(Integer.valueOf(Utility.eventId.get(pos)));
                    }

                long startMillis = 0;
                long endMillis = 0;
                Calendar beginTime = Calendar.getInstance();
                beginTime.set(eventStartDate.getYear(),eventStartDate.getMonth(), eventStartDate.getDay(), eventStartTime.getHour(),eventStartTime.getMinutes());
                startMillis = beginTime.getTimeInMillis();
                Calendar endTime = Calendar.getInstance();
                endTime.set(eventEndDate.getYear(), eventEndDate.getMonth(), eventEndDate.getDay(), eventEndTime.getHour(),eventEndTime.getMinutes());
                endMillis = endTime.getTimeInMillis();

                final Task newTask = new Task(1,eventTitle.getText().toString(),startMillis,endMillis,eventLocation.getText().toString());
                if (solutionAfterEditSolution !=null){
                    newTask.setSolution(solutionAfterEditSolution);
                }
                else {
                    newTask.setSolution(taskTest.getSolution());
                }
                Model.instance().addTaskWithSolution(newTask);

                delegate.endFragmentEdit();
                }
            }
        });


        toDo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //if null to do

                if(task!=null){
                    long startMillis = 0;
                    long endMillis = 0;
                    Calendar beginTime = Calendar.getInstance();
                    beginTime.set(eventStartDate.getYear(),eventStartDate.getMonth(), eventStartDate.getDay(), eventStartTime.getHour(),eventStartTime.getMinutes());
                    startMillis = beginTime.getTimeInMillis();
                    Calendar endTime = Calendar.getInstance();
                    endTime.set(eventEndDate.getYear(), eventEndDate.getMonth(), eventEndDate.getDay(), eventEndTime.getHour(),eventEndTime.getMinutes());
                    endMillis = endTime.getTimeInMillis();
                    Task newTask = new Task(Integer.valueOf(Utility.eventId.get(pos)),eventTitle.getText().toString(),startMillis,endMillis,eventLocation.getText().toString());
                    delegate.taskEditWithSolution(newTask,solutionAfterEditSolution,solutionAfterEditSolution);
                }
                else {
                    Solution old = null;
                    if(Model.instance().getSolution(Integer.valueOf(Utility.eventId.get(pos)))!=null){
                        old = Model.instance().getSolution(Integer.valueOf(Utility.eventId.get(pos)));
                    }
                    long startMillis = 0;
                    long endMillis = 0;
                    Calendar beginTime = Calendar.getInstance();
                    beginTime.set(eventStartDate.getYear(),eventStartDate.getMonth(), eventStartDate.getDay(), eventStartTime.getHour(),eventStartTime.getMinutes());
                    startMillis = beginTime.getTimeInMillis();
                    Calendar endTime = Calendar.getInstance();
                    endTime.set(eventEndDate.getYear(), eventEndDate.getMonth(), eventEndDate.getDay(), eventEndTime.getHour(),eventEndTime.getMinutes());
                    endMillis = endTime.getTimeInMillis();
                    Task newTask = new Task(Integer.valueOf(Utility.eventId.get(pos)),eventTitle.getText().toString(),startMillis,endMillis,eventLocation.getText().toString());

                    delegate.taskEditWithSolution(newTask,solutionAfterEditSolution,old);
                }


            }
        });
        return view;
    }

    public void setPosition (int pos)
    {
        this.pos=pos;
    }


    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_edit, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete) {
            Model.instance().deleteTask(Integer.valueOf(Utility.eventId.get(pos)));
            Model.instance().deleteSolution(Integer.valueOf(Utility.eventId.get(pos)));
            final Delegate delegate = (Delegate) getActivity();
            delegate.endFragmentEdit();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public void setTask(Task task) {

        this.task=task;
    }

    public void setSolution(Solution sol)
    {
        this.solutionAfterEditSolution=sol;
    }


}
