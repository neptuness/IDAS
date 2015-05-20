package com.example.nepus.idash.Setting;

import android.app.ActionBar;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
//import android.util.Log;  Will log when running program
import android.view.MenuItem;

import com.example.nepus.idash.R;


public class SettingActivity extends PreferenceActivity{


    //private static final String TAG = SettingActivity.class.getSimpleName();
    private ListPreference listspeedUPreference;
    private ListPreference listtempUPreference;
    private ListPreference listmodePreference;
    private EditTextPreference SpeedlimitPreference;
    private EditTextPreference RPMlimitPreference;
    private EditTextPreference CoolantlimitPreference;


    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.layout.activity_setting);
        listspeedUPreference = (ListPreference) findPreference(IDASPreferenceManager.SPEED_UNIT);
        listtempUPreference = (ListPreference) findPreference(IDASPreferenceManager.TEMP_UNIT);
        listmodePreference = (ListPreference) findPreference(IDASPreferenceManager.MODE);
        SpeedlimitPreference = (EditTextPreference) findPreference(IDASPreferenceManager.SPEED_LIMIT);
        RPMlimitPreference = (EditTextPreference) findPreference(IDASPreferenceManager.RPM_LIMIT);
        CoolantlimitPreference = (EditTextPreference) findPreference(IDASPreferenceManager.COOLANT_LIMIT);
        Preference DefaultPreference =  findPreference(IDASPreferenceManager.DEFAULT_BUTTON);

        DefaultPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                IDASPreferenceManager.setDefaultVal(getApplicationContext());
                refresh();
                return true;
            }
        });

        listspeedUPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                int index = listspeedUPreference.findIndexOfValue(newValue.toString());
                CharSequence test = listspeedUPreference.getEntries()[index];
                listspeedUPreference.setSummary(test);
               // Log.e("Value", "" + newValue.toString());
                return true;
            }
        });

        listtempUPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {

                int index = listtempUPreference.findIndexOfValue(newValue.toString());
                CharSequence test = listtempUPreference.getEntries()[index];
                listtempUPreference.setSummary(test);
                //Log.e("Value", "" + newValue.toString());
                return true;
            }

        });

        listmodePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                int index = listmodePreference.findIndexOfValue(newValue.toString());
                if (index != -1 && !(newValue.toString().equals("3"))) {
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

                CharSequence test = listmodePreference.getEntries()[index];
                listmodePreference.setSummary(test);

                //Log.e("Value", "" + newValue.toString());
                //Log.d(TAG, newValue.toString());

                IDASPreferenceManager.putPref(IDASPreferenceManager.MODE, newValue.toString(), getApplicationContext());

                SpeedlimitPreference.setEnabled(true);
                RPMlimitPreference.setEnabled(true);
                CoolantlimitPreference.setEnabled(true);
//                Log.e("DEBUG", "Value of modeIdx = "+modeIdx);
                if (!newValue.toString().equals("3")){
                    SpeedlimitPreference.setEnabled(false);
                    RPMlimitPreference.setEnabled(false);
                    CoolantlimitPreference.setEnabled(false);
                }

                SpeedlimitPreference.getOnPreferenceChangeListener().onPreferenceChange(SpeedlimitPreference, IDASPreferenceManager.getPref(IDASPreferenceManager.SPEED_LIMIT, getApplicationContext()));
                RPMlimitPreference.getOnPreferenceChangeListener().onPreferenceChange(RPMlimitPreference, IDASPreferenceManager.getPref(IDASPreferenceManager.RPM_LIMIT, getApplicationContext()));
                CoolantlimitPreference.getOnPreferenceChangeListener().onPreferenceChange(CoolantlimitPreference, IDASPreferenceManager.getPref(IDASPreferenceManager.COOLANT_LIMIT, getApplicationContext()));

                return true;
            }
        });


        SpeedlimitPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener(){
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                SpeedlimitPreference.setSummary(newValue.toString());
                return true;
            }
        });

        RPMlimitPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener(){
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                RPMlimitPreference.setSummary(newValue.toString());
                return true;
            }
        });

        CoolantlimitPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                CoolantlimitPreference.setSummary(newValue.toString());
                return true;
            }
        });

//        getActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar = getActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

    }

    //refresh the value when the listeners was changed and set to he summary on preference
    public void refresh(){
        String mode = IDASPreferenceManager.getPref(IDASPreferenceManager.MODE, this);
        String tempU = IDASPreferenceManager.getPref(IDASPreferenceManager.TEMP_UNIT, this);
        String speedU = IDASPreferenceManager.getPref(IDASPreferenceManager.SPEED_UNIT, this);
        String speedlimit = IDASPreferenceManager.getPref(IDASPreferenceManager.SPEED_LIMIT, this);
        String rpmlimit = IDASPreferenceManager.getPref(IDASPreferenceManager.RPM_LIMIT,this);
        String coollimit = IDASPreferenceManager.getPref(IDASPreferenceManager.COOLANT_LIMIT, this);


        listmodePreference.getOnPreferenceChangeListener().onPreferenceChange(listmodePreference, mode);
        listtempUPreference.getOnPreferenceChangeListener().onPreferenceChange(listtempUPreference, tempU);
        listspeedUPreference.getOnPreferenceChangeListener().onPreferenceChange(listspeedUPreference, speedU);
        SpeedlimitPreference.getOnPreferenceChangeListener().onPreferenceChange(SpeedlimitPreference, speedlimit);
        RPMlimitPreference.getOnPreferenceChangeListener().onPreferenceChange(RPMlimitPreference, rpmlimit);
        CoolantlimitPreference.getOnPreferenceChangeListener().onPreferenceChange(CoolantlimitPreference, coollimit);


        listmodePreference.setValueIndex(Integer.parseInt(mode) - 1);
        listtempUPreference.setValueIndex(Integer.parseInt(tempU) - 1);
        listspeedUPreference.setValueIndex(Integer.parseInt(speedU) - 1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    //actionbar for back to home screen
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

}

