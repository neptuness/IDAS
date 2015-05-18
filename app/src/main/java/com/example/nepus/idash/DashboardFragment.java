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


/**
 * Created by NEPUS on 5/16/15 AD.
 */
public class DashboardFragment extends Fragment {

    private GridView gridView;
    private ViewGroup rootView;
    private ArcProgress coolantProgress, engineProgress, rpmProgress,torqueProgress, throttleProgress,mafProgress;


    public DashboardFragment() { }

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
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_dashboard_widgets,container,false);
//
//        gridView = (GridView) rootView.findViewById(R.id.gridview);
//        gridView.setAdapter(new DashboardAdapter(getActivity(),6));

        coolantProgress= (ArcProgress) rootView.findViewById(R.id.coolant_temp);
        engineProgress= (ArcProgress) rootView.findViewById(R.id.engine_temp);
        rpmProgress= (ArcProgress) rootView.findViewById(R.id.rpm_progress);
        torqueProgress= (ArcProgress) rootView.findViewById(R.id.torque_progress);
        throttleProgress = (ArcProgress) rootView.findViewById(R.id.th_progress);
        mafProgress= (ArcProgress) rootView.findViewById(R.id.MAF_progress);

        coolantProgress.setMax(100);
        engineProgress.setMax(150);
        rpmProgress.setMax(7000);
        torqueProgress.setMax(80);
        throttleProgress.setMax(100);
        mafProgress.setMax(12);

        return rootView;
    }

    public ArcProgress getCoolantProgress(){
        return coolantProgress;
    }

    public ArcProgress getEngineProgress(){
        return engineProgress;
    }

    public ArcProgress getRPMProgress(){
        return rpmProgress;
    }

    public ArcProgress getTorqueProgress(){
        return torqueProgress;
    }

    public ArcProgress getThrottleProgress(){
        return throttleProgress;
    }

    public ArcProgress getMAFProgress(){
        return mafProgress;
    }


}
