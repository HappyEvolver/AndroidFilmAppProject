package com.bigfoot.android;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Activity backing settings menu and behaviour.
 *
 * User: Neil Pattinson
 * Date: 24/01/13
 * Time: 13:36
 */
public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String PREF_EXCLUDE_ADULT = "pref_exclude_adult";
    public static final String PREF_THIS_CENTURY = "pref_this_century";

    @SuppressWarnings("deprecation")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(getLocalClassName(), "Pause detected");
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(getLocalClassName(), "Resume detected");
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        // All we're doing for now is logging that we have detected the setting change.
        if (s.equals(PREF_EXCLUDE_ADULT)) {
            Log.i(getLocalClassName(), "'Adult' pref changed");
        }

        if (s.equals(PREF_THIS_CENTURY)) {
            Log.i(getLocalClassName(), "'Y2K' pref changed");
        }
    }
}