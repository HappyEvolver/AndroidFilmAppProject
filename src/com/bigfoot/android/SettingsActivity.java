package com.bigfoot.android;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.Toast;

/**
 * User: Neil Pattinson
 * Date: 24/01/13
 * Time: 13:36
 */
public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String PREF_EXCLUDE_ADULT = "pref_exclude_adult";
    public static final String PREF_THIS_CENTURY = "pref_this_century";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
//        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
//        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
//        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if (s.equals(PREF_EXCLUDE_ADULT)) {
//            Preference adultPref = findPreference(s);
//            boolean value = sharedPreferences.getBoolean(PREF_EXCLUDE_ADULT, false);
            Toast.makeText(this, "Adult setting changed", Toast.LENGTH_LONG);
        }

        if (s.equals(PREF_THIS_CENTURY)) {
            Toast.makeText(this, "Films this century setting changed", Toast.LENGTH_LONG);
        }
    }
}