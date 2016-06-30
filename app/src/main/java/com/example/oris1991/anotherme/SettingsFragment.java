package com.example.oris1991.anotherme;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SettingsFragment extends PreferenceFragment {

    private static final int PREFERENCE_MODE_PRIVATE=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

        final SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getActivity());
        String timeReg=(mSharedPreference.getString("regTime", "1.1.2016"));

        Preference timeRg = (Preference) findPreference("pref_register_time");
        timeRg.setSummary(timeReg);

        EditTextPreference agePref = (EditTextPreference) findPreference("pref_age");
        agePref.setSummary(agePref.getText());
        
        EditTextPreference emailPref = (EditTextPreference) findPreference("pref_mail");
        if (emailPref.getText().equals("false"))
            emailPref.setSummary("הזן מייל");
        else
            emailPref.setSummary(emailPref.getText());

        EditTextPreference prefTelephone = (EditTextPreference) findPreference("pref_telephone");
        if ( prefTelephone.getText().equals("false"))
            prefTelephone.setSummary("הזן טלפון");
        else
            prefTelephone.setSummary(prefTelephone.getText());

        EditTextPreference prefAge = (EditTextPreference) findPreference("pref_age");
        if ( prefAge.getText().equals("false"))
            prefAge.setSummary("הזן גיל");
        else
            prefAge.setSummary(prefTelephone.getText());

        final CheckBoxPreference SMS = (CheckBoxPreference) findPreference("pref_SMS");
        final CheckBoxPreference popups = (CheckBoxPreference) findPreference("pref_popups");
        final CheckBoxPreference solution = (CheckBoxPreference) findPreference("pref_solution");
        solution.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if (!solution.isChecked()) {
                    popups.setEnabled(false);
                    SMS.setEnabled(false);
                }
                else {
                    popups.setEnabled(true);
                    SMS.setEnabled(true);
                }
                return true;
            }
        });
        popups.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if (!popups.isChecked())
                    SMS.setEnabled(false);
                else
                    SMS.setEnabled(true);
                return true;
            }
        });

    }
}
