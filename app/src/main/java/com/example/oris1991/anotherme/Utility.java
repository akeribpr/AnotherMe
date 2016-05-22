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
package com.example.oris1991.anotherme;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.util.Log;

import com.example.oris1991.anotherme.Model.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Mukesh Y
 */
public class Utility {
	public static ArrayList<String> nameOfEvent = new ArrayList<String>();
	public static ArrayList<String> startDates = new ArrayList<String>();
	public static ArrayList<String> endDates = new ArrayList<String>();
	public static ArrayList<String> descriptions = new ArrayList<String>();
	public static ArrayList<String> startTime = new ArrayList<String>();
	public static ArrayList<String> startDateAndTime = new ArrayList<String>();
	public static ArrayList<Long> eventId = new ArrayList<Long>();

	public static ArrayList<String> readCalendarEvent(Context context) {
		Cursor cursor = context.getContentResolver()
				.query(Uri.parse("content://com.android.calendar/events"),
						new String[] { "calendar_id", "title", "description",
								"dtstart", "dtend", "eventLocation"},"deleted != 1",
						null, null);
		cursor.moveToFirst();



		// fetching calendars name
		String CNames[] = new String[cursor.getCount()];

		SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.getDefault());

		// fetching calendars id
		nameOfEvent.clear();
		startDates.clear();
		//endDates.clear();
		//descriptions.clear();
		startTime.clear();
		eventId.clear();
		startDateAndTime.clear();
		for (int i = 0; i < CNames.length; i++) {

			nameOfEvent.add(cursor.getString(1));
			startDates.add(getDate(Long.parseLong(cursor.getString(3))));
//			endDates.add(getDate(Long.parseLong(cursor.getString(4))));
		//	descriptions.add(cursor.getString(2));
			CNames[i] = cursor.getString(1);
			startTime.add(format.format(new Date(Long.parseLong(cursor.getString(3)))));
			/*long eventID = Long.parseLong(uri.getLastPathSegment());*/
			startDateAndTime.add(cursor.getString(3));
			cursor.moveToNext();

		}
		return nameOfEvent;
	}

	public static void insertCalendarEvent(Context context,Event newEvent) {

		long calID = 1;

		ContentValues values = new ContentValues();

		values.put("calendar_id", calID);
		values.put("title",newEvent.getTitle());
		values.put("dtstart", newEvent.getStartTime());
		values.put("dtend", newEvent.getEndTime());
		values.put("eventLocation", newEvent.getLocation());
		values.put("eventTimezone", TimeZone.getDefault().getID());


		Uri calendarUri =context.getContentResolver().insert(Uri.parse("content://com.android.calendar/events"), values);

	}

	public static void deleteCalendarEvent(Context context,int numberOfEvent) {


		//Log.d("Tag",eventId.get(numberOfEvent));
	/*	long calID = 1;

		ContentValues values = new ContentValues();

		values.put("calendar_id", calID);
		values.put("title",newEvent.getTitle());
		values.put("description", newEvent.getDescription());
		values.put("dtstart", newEvent.getStartTime());
		values.put("dtend",newEvent.getEndTime());
		values.put("eventLocation", newEvent.getLocation());
		values.put("eventTimezone", TimeZone.getDefault().getID());*/


		//Uri calendarUri =context.getContentResolver().delete(Uri.parse("content://com.android.calendar/events"),"");

		String[] selectionsArgs = new String[] {nameOfEvent.get(numberOfEvent),startDateAndTime.get(numberOfEvent)};
		int calendarUri =context.getContentResolver().delete(Uri.parse(String.valueOf(CalendarContract.Events.CONTENT_URI)), "title=? and  dtstart=?  ",
				selectionsArgs);
		/*String[] selectionsArgs = new String[] {String.valueOf(numberOfEvent)};
		int calendarUri =context.getContentResolver().delete(Uri.parse(String.valueOf(CalendarContract.Events.CONTENT_URI)), CalendarContract.Events._ID+"=?",
				selectionsArgs);*/
		Log.d("Tag",String.valueOf(calendarUri));




	}

	public static String getDate(long milliSeconds) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds);
		return formatter.format(calendar.getTime());
	}


}
