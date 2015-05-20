package com.example.nepus.idash.Dashboard;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.nepus.idash.R;

//This is a information of temperature that call layout to show on the page
public class InfoTempFragment extends Fragment {
    public InfoTempFragment() {}

    private ArcProgress coolTemp;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_temp_details, container, false);

        //declare progress of coolant temperature
        coolTemp = (ArcProgress) rootView.findViewById(R.id.coolant_progress);
        coolTemp.setMax(150);
        return rootView;
    }

    public ArcProgress getCoolantProgress(){
        return coolTemp;
    }
}
