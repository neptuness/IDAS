package com.example.nepus.idash;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by AbS01ute on 5/18/15 AD.
 */
public class IDASPreferenceManager {

    public static final String MODE = "ModePref";
    public static final String SPEED_LIMIT = "Speedlimit";
    public static final String RPM_LIMIT = "RPMlimit";
    public static final String COOLANT_LIMIT = "Coolantlimit";
    public static final String TEMP_UNIT = "TempUPref";
    public static final String SPEED_UNIT = "SpeedUPref";

    public static void putPref(String key, String value, Context context) {
        SharedPreferences prefs = android.preference.PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getPref(String key, Context context) {
        SharedPreferences preferences = android.preference.PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }




}
