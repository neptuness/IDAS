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

//This is information of ENERGY fragment that call information layout
public class InfoEnergyFragment extends Fragment{
    private ArcProgress fuelProgress;

    public InfoEnergyFragment() {}

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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_energy_details, container, false);

        //declare a widget progress
        fuelProgress = (ArcProgress) rootView.findViewById(R.id.fuel_progress);
        fuelProgress.setMax(100);
        return rootView;
    }

    public ArcProgress getFuelProgress(){
        return fuelProgress;
    }
}
