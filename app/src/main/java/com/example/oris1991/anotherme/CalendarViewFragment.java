package com.example.oris1991.anotherme;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.oris1991.anotherme.ExternalCalendar.CalendarAdapter;
import com.example.oris1991.anotherme.ExternalCalendar.Utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by oris1991 on 07/05/2016.
 */
public class CalendarViewFragment extends Fragment {


    interface Delegate {

        //  public void plusButton(int code);
        public void startEdit(int pos);

    }

    public GregorianCalendar month, itemmonth;// main_content instances.

    public CalendarAdapter adapter;// adapter instance
    public Handler handler;// for grabbing some event values for showing the dot
    // marker.
    public ArrayList<String> items; // container to store main_content items which
    // needs showing the event marker
    ArrayList<String> event;
    ListView rLayout;
    ArrayList<String> date;
    ArrayList<String> desc;
    ArrayList<Integer> time;
    MyAddapter adapterEvent = new MyAddapter();
    View view;
    Context mContext;
    int gridvalue;
    int mmonth;
    int year;

    public int getYear() {
        return year;
    }

    public int getMmonth() {
        return mmonth;
    }

    public int getGridvalue() {
        return gridvalue;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.calendar_fragment, container, false);
        final Delegate delegate = (Delegate) getActivity();
        mContext = container.getContext();


        Locale.setDefault(Locale.US);

        rLayout = (ListView) view.findViewById(R.id.event_listview);
        month = (GregorianCalendar) GregorianCalendar.getInstance();
        itemmonth = (GregorianCalendar) month.clone();

        date = new ArrayList<String>();
        items = new ArrayList<String>();


        adapter = new CalendarAdapter(getActivity(), month);

        rLayout.setAdapter(adapterEvent);

        GridView gridview = (GridView) view.findViewById(R.id.gridview);
        gridview.setAdapter(adapter);

        handler = new Handler();
        handler.post(calendarUpdater);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
        mmonth = Integer.valueOf(android.text.format.DateFormat.format("M", month).toString()) - 1;
        year = Integer.valueOf(android.text.format.DateFormat.format("yyyy", month).toString());
        Calendar calendar = Calendar.getInstance();
        gridvalue = calendar.get(Calendar.DAY_OF_MONTH);


        ImageView previous = (ImageView) view.findViewById(R.id.previous);

        previous.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setPreviousMonth();
                refreshCalendar();
            }
        });

        ImageView next = (ImageView) view.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setNextMonth();
                refreshCalendar();

            }
        });


        rLayout.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if (Utility.eventId.get(time.get(position)) != "0")
                    delegate.startEdit(time.get(position));
                adapter.notifyDataSetChanged();
                handler.post(calendarUpdater);
                adapterEvent.notifyDataSetChanged();


            }
        });

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {


                desc = new ArrayList<String>();
                time = new ArrayList<Integer>();

                ((CalendarAdapter) parent.getAdapter()).setSelected(v);
                String selectedGridDate = CalendarAdapter.dayString
                        .get(position);
                String[] separatedTime = selectedGridDate.split("-");
                String gridvalueString = separatedTime[2].replaceFirst("^0*",
                        "");// taking last part of date. ie; 2 from 2012-12-02.
                gridvalue = Integer.parseInt(gridvalueString);
                // navigate to next or previous month on clicking offdays.
                if ((gridvalue > 10) && (position < 8)) {
                    setPreviousMonth();
                    refreshCalendar();
                } else if ((gridvalue < 7) && (position > 28)) {
                    setNextMonth();
                    refreshCalendar();
                }
                ((CalendarAdapter) parent.getAdapter()).setSelected(v);

                for (int i = 0; i < Utility.startDates.size(); i++) {
                    if (Utility.startDates.get(i).equals(selectedGridDate)) {
                        time.add(i);
                    }
                }

                int temp;
                for (int i = 0; i < time.size(); i++)
                    for (int j = i; j < time.size(); j++) {
                        if (Utility.startTime.get(time.get(i)).compareTo(Utility.startTime.get(time.get(j))) > 0) {
                            temp = time.get(i);
                            time.set(i, time.get(j));
                            time.set(j, temp);
                        }
                    }
                for (int i = 0; i < time.size(); i++) {
                    desc.add(Utility.nameOfEvent.get(time.get(i)) + "  " + Utility.startTime.get(time.get(i)));
                }


                adapterEvent.notifyDataSetChanged();

            }

        });
        return view;
    }

    protected void setNextMonth() {
        mmonth = Integer.valueOf(android.text.format.DateFormat.format("M", month).toString());
        year = Integer.valueOf(android.text.format.DateFormat.format("yyyy", month).toString());
        if (month.get(GregorianCalendar.MONTH) == month
                .getActualMaximum(GregorianCalendar.MONTH)) {
            month.set((month.get(GregorianCalendar.YEAR) + 1),
                    month.getActualMinimum(GregorianCalendar.MONTH), 1);
        } else {
            month.set(GregorianCalendar.MONTH,
                    month.get(GregorianCalendar.MONTH) + 1);
        }

    }

    protected void setPreviousMonth() {
        mmonth = Integer.valueOf(android.text.format.DateFormat.format("M", month).toString());
        year = Integer.valueOf(android.text.format.DateFormat.format("yyyy", month).toString());
        if (month.get(GregorianCalendar.MONTH) == month
                .getActualMinimum(GregorianCalendar.MONTH)) {
            month.set((month.get(GregorianCalendar.YEAR) - 1),
                    month.getActualMaximum(GregorianCalendar.MONTH), 1);
        } else {
            month.set(GregorianCalendar.MONTH,
                    month.get(GregorianCalendar.MONTH) - 1);
        }

    }

    public void refreshCalendar() {
        TextView title = (TextView) view.findViewById(R.id.title);

        adapter.refreshDays();
        adapter.notifyDataSetChanged();
        handler.post(calendarUpdater); // generate some main_content items

        title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
    }

    public Runnable calendarUpdater = new Runnable() {

        @Override
        public void run() {
            items.clear();

            // Print dates of the current week
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            String itemvalue;
            event = Utility.readCalendarEvent(getActivity().getApplicationContext());
            // Log.d("=====Task====", event.toString());
            // Log.d("=====Date ARRAY====", Utility.startDates.toString());

            for (int i = 0; i < Utility.startDates.size(); i++) {
                itemvalue = df.format(itemmonth.getTime());
                itemmonth.add(GregorianCalendar.DATE, 1);
                items.add(Utility.startDates.get(i).toString());
            }
            adapter.setItems(items);
            adapter.notifyDataSetChanged();
        }
    };

    class MyAddapter extends BaseAdapter {


        @Override
        public int getCount() {
            if (desc != null)
                return desc.size();
            else
                return 0;

        }

        @Override
        public Object getItem(int position) {
            return desc.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView,
                            ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = getActivity().getLayoutInflater();
                convertView = inflater.inflate(R.layout.event_row, null);
            }
            TextView eventName = (TextView) convertView.findViewById(R.id.event_text);
            ImageView image = (ImageView) convertView.findViewById(R.id.event_row_image);

            eventName.setText(desc.get(position));
            if (Utility.eventId.get(time.get(position)) == "0") {
                image.setImageResource(R.drawable.google_icon);
            } else
                image.setImageResource(android.R.color.transparent);
            return convertView;
        }
    }
}
