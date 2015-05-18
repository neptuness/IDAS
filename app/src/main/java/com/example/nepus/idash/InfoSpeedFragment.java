package com.example.nepus.idash;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;


public class InfoSpeedFragment extends Fragment {
    private ViewGroup rootView;
    private ArcProgress speedProgress;


    public InfoSpeedFragment() {}

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
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_speed_details,container,false);

        speedProgress = (ArcProgress) rootView.findViewById(R.id.speed_progress);
        speedProgress.setMax(300);
        return rootView;
    }

    public ArcProgress getSpeedProgress(){
        return speedProgress;
    }
}
