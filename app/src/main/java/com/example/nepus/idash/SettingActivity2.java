package com.example.nepus.idash;

import android.content.Context;
import android.preference.PreferenceActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.widget.Toast;



public class SettingActivity2 extends PreferenceActivity implements OnSharedPreferenceChangeListener {


    private ListPreference listspeedPreference;
    private ListPreference listtempPreference;
    private ListPreference listmodePreference;
    private EditTextPreference SpeedlimitPreference;
    private EditTextPreference RPMlimitPreference;
    private EditTextPreference CoolantlimitPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.layout.activity_setting);

        Context context = getApplicationContext();
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings.registerOnSharedPreferenceChangeListener(this);

        listspeedPreference = (ListPreference) findPreference("SpeedUPref");
        listtempPreference = (ListPreference) findPreference("TempUPref");
        listmodePreference = (ListPreference) findPreference("ModePref");
        SpeedlimitPreference = (EditTextPreference) findPreference("Speedlimit");
        RPMlimitPreference = (EditTextPreference) findPreference("RPMlimit");
        CoolantlimitPreference = (EditTextPreference) findPreference("Coolantlimit");
        listspeedPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                int index = listspeedPreference.findIndexOfValue(newValue.toString());
                if (index != -1) {
                    Toast.makeText(getBaseContext(), listspeedPreference.getEntries()[index], Toast.LENGTH_LONG).show();
                }
                CharSequence test = listspeedPreference.getEntries()[index];
                listspeedPreference.setSummary(test);
                Log.e("Value", "" + newValue.toString());

                return true;
            }
        });

        listtempPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {

                int index = listtempPreference.findIndexOfValue(newValue.toString());
                if (index != -1) {
                    Toast.makeText(getBaseContext(), listtempPreference.getEntries()[index], Toast.LENGTH_LONG).show();
                }
                CharSequence test = listtempPreference.getEntries()[index];
                listtempPreference.setSummary(test);
                Log.e("Value", "" + newValue.toString());

                return true;
            }
        });

        listmodePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {

                int index = listmodePreference.findIndexOfValue(newValue.toString());
                if (index != -1 && !(newValue.toString().equals("3"))) {

                    Toast.makeText(getBaseContext(), listmodePreference.getEntries()[index], Toast.LENGTH_LONG).show();
                    SpeedlimitPreference.setEnabled(false);
                    RPMlimitPreference.setEnabled(false);
                    CoolantlimitPreference.setEnabled(false);
                    if (newValue.toString().equals("1")) {
                        SpeedlimitPreference.setText("80");
                        RPMlimitPreference.setText("3000");
                        CoolantlimitPreference.setText("100");
                    }
                    if (newValue.toString().equals("2")) {
                        SpeedlimitPreference.setText("100");
                        RPMlimitPreference.setText("3500");
                        CoolantlimitPreference.setText("100");
                    }
                }

                if (newValue.toString().equals("3")) {
                    SpeedlimitPreference.setEnabled(true);
                    RPMlimitPreference.setEnabled(true);
                    CoolantlimitPreference.setEnabled(true);
                }
                CharSequence test = listmodePreference.getEntries()[index];
                listmodePreference.setSummary(test);
                Log.e("Value", "" + newValue.toString());

                return true;
            }
        });

        getActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
        String mode = IDASPreferenceManager.getPref(IDASPreferenceManager.MODE, this);
        int modeIdx = Integer.parseInt(mode) - 1;
        String tempU = IDASPreferenceManager.getPref(IDASPreferenceManager.TEMP_UNIT, this);
        int tempUIdx = Integer.parseInt(tempU) - 1;
        String speedU = IDASPreferenceManager.getPref(IDASPreferenceManager.SPEED_UNIT, this);
        int speedUIdx = Integer.parseInt(speedU) - 1;

        listmodePreference.setSummary(listmodePreference.getEntries()[modeIdx]);
        listtempPreference.setSummary(listtempPreference.getEntries()[tempUIdx]);
        listspeedPreference.setSummary(listspeedPreference.getEntries()[speedUIdx]);

        SpeedlimitPreference.setEnabled(true);
        RPMlimitPreference.setEnabled(true);
        CoolantlimitPreference.setEnabled(true);

        if (modeIdx != 2){
            SpeedlimitPreference.setEnabled(false);
            RPMlimitPreference.setEnabled(false);
            CoolantlimitPreference.setEnabled(false);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == android.R.id.home){
            finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public static final String KEY_PREF_SYNC_CONN = "pref_syncConnectionType";

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(KEY_PREF_SYNC_CONN)) {
            Preference connectionPref = findPreference(key);
            // Set summary to be the user-description for the selected value
            connectionPref.setSummary(sharedPreferences.getString(key, ""));
        }
    }
}

