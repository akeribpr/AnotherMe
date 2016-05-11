package com.example.oris1991.anotherme;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by oris1991 on 26/04/2016.
 */
public class SettingsActivity extends PreferenceActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

    }
}
