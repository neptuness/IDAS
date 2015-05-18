package com.example.nepus.idash;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by NEPUS on 5/17/15 AD.
 */
public class InfoTempFragment extends Fragment {
    public InfoTempFragment() {}

    private ViewGroup rootView;
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
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_temp_details,container,false);
        coolTemp = (ArcProgress) rootView.findViewById(R.id.coolant_progress);
        coolTemp.setMax(150);
        return rootView;
    }

    public ArcProgress getCoolantProgress(){
        return coolTemp;
    }
}
