package com.example.nepus.idash.Setting;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;



public class IDASPreferenceManager {

    public static final String MODE = "ModePref";
    public static final String SPEED_LIMIT = "Speedlimit";
    public static final String RPM_LIMIT = "RPMlimit";
    public static final String COOLANT_LIMIT = "Coolantlimit";
    public static final String TEMP_UNIT = "TempUPref";
    public static final String SPEED_UNIT = "SpeedUPref";
    public static final String DEFAULT_BUTTON = "BReset";
    private static final String[] prefKeys = {MODE, SPEED_LIMIT, RPM_LIMIT, COOLANT_LIMIT, TEMP_UNIT, SPEED_UNIT};
    private static final Map<String, String> defaultVal = new HashMap<>();
    private static void initDefaultValMap(){
        defaultVal.put(MODE, "1");
        defaultVal.put(SPEED_LIMIT, "80");
        defaultVal.put(RPM_LIMIT, "3000");
        defaultVal.put(COOLANT_LIMIT, "100");
        defaultVal.put(TEMP_UNIT, "1");
        defaultVal.put(SPEED_UNIT,"1");
    }

    //put the data to the share preference using map
    public static void putPref(String key, String value, Context context) {
        SharedPreferences prefs = android.preference.PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    //get the value of the key in sharepreference
    public static String getPref(String key, Context context) {
        initPrefernceFile(context);
        SharedPreferences preferences = android.preference.PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    //check the file in share preference null or not if not null out function ,if null set the default of pref;
    private static void initPrefernceFile(Context context){
        SharedPreferences preferences = android.preference.PreferenceManager.getDefaultSharedPreferences(context);
        for (String prefKey : prefKeys) {
            String val = preferences.getString(prefKey, null);
            if (val == null){
                setDefaultVal(context);
                return;
            }
        }
    }


    //set the default for the new preference
    public static void setDefaultVal(Context context){
        initDefaultValMap();
        for (String prefKey : prefKeys) {
            putPref(prefKey, defaultVal.get(prefKey), context);
        }
    }




}
