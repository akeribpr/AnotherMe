package com.example.oris1991.anotherme;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

/**
 * Created by oris1991 on 26/04/2016.
 */
public class SettingsActivity extends PreferenceActivity{

    private static final int PREFERENCE_MODE_PRIVATE=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        Preference customPref = (Preference) findPreference("pref_register_time");
        customPref.setSummary("2.3.16");


    }
}
