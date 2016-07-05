/*
 * Copyright (C) 2014 Mukesh Y authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.oris1991.anotherme.ExternalCalendar;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.example.oris1991.anotherme.Model.Entities.Task;
import com.example.oris1991.anotherme.Model.Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author Mukesh Y
 */
public class Utility {
    public static ArrayList<String> nameOfEvent = new ArrayList<String>();
    public static ArrayList<String> locations = new ArrayList<String>();
    public static ArrayList<String> startDates = new ArrayList<String>();
    public static ArrayList<String> endDates = new ArrayList<String>();
    public static ArrayList<String> startTime = new ArrayList<String>();
    public static ArrayList<String> endTime = new ArrayList<String>();
    public static ArrayList<String> startDateAndTime = new ArrayList<String>();
    public static ArrayList<String> endDateAndTime = new ArrayList<String>();
    public static ArrayList<String> eventId = new ArrayList<String>();
    public static List<Task> taskArry = new ArrayList<Task>();

    public static ArrayList<String> readCalendarEvent(Context context) {
        Cursor cursor = context.getContentResolver()
                .query(Uri.parse("content://com.android.calendar/events"),
                        new String[]{"calendar_id", "title", "description",
                                "dtstart", "dtend", "eventLocation"}, "deleted != 1",
                        null, null);
        cursor.moveToFirst();

        taskArry = Model.instance().getTasks();

        // fetching calendars name
        String CNames[] = new String[cursor.getCount()];

        SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.getDefault());

        // fetching calendars id
        nameOfEvent.clear();
        startDates.clear();
        endDates.clear();
        endTime.clear();
        locations.clear();
        startTime.clear();
        eventId.clear();
        startDateAndTime.clear();
        endDateAndTime.clear();
        for (int i = 0; i < CNames.length; i++) {

            nameOfEvent.add(cursor.getString(1));//text
            locations.add(cursor.getString(5));
            startDates.add(getDate(Long.parseLong(cursor.getString(3))));//start date
            CNames[i] = cursor.getString(1);
            startTime.add(format.format(new Date(Long.parseLong(cursor.getString(3)))));
            startDateAndTime.add(cursor.getString(3));
            endDateAndTime.add(cursor.getString(4));
            eventId.add("0");

            cursor.moveToNext();
        }
        for (int i = 0; i < taskArry.size(); i++) {
            nameOfEvent.add(taskArry.get(i).getTitle());
            locations.add(taskArry.get(i).getLocation());
            startDates.add(getDate(Long.parseLong(String.valueOf(taskArry.get(i).getStartTime()))));
            startTime.add(format.format(new Date(Long.parseLong(String.valueOf(taskArry.get(i).getStartTime())))));
            endDates.add(getDate(Long.parseLong(String.valueOf(taskArry.get(i).getEndTime()))));
            endTime.add(format.format(new Date(Long.parseLong(String.valueOf(taskArry.get(i).getEndTime())))));
            startDateAndTime.add(String.valueOf(taskArry.get(i).getStartTime()));
            endDateAndTime.add(String.valueOf(taskArry.get(i).getEndTime()));
            eventId.add(String.valueOf(taskArry.get(i).getId()));
        }

        return nameOfEvent;
    }


    public static String getDate(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }


}
