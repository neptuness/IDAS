package com.example.nepus.idash.System;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.example.nepus.idash.Dashboard.ArcProgress;
import com.example.nepus.idash.Dashboard.DashboardFragment;
import com.example.nepus.idash.Dashboard.InfoEnergyFragment;
import com.example.nepus.idash.Dashboard.InfoSpeedFragment;
import com.example.nepus.idash.Dashboard.InfoTempFragment;
import com.example.nepus.idash.R;

import java.util.Timer;
import java.util.TimerTask;

//This is an Activity that control ViewPager of Dashboard fucntion
//which include 4 pages of information

public class SystemActivity extends FragmentActivity{
    private static final int NUM_PAGES = 4;

    private DashboardFragment dashboardFragment;
    private InfoSpeedFragment infoSpeedFragment;
    private InfoTempFragment infoTempFragment;
    private InfoEnergyFragment infoEnergyFragment;

    private int val_coolant, val_engine, val_rpm, val_torque, val_throttle,val_maf;
    private int val_speed, val_fuel;

     private ArcProgress coolantProgress,engineProgress,rpmProgress,torqueProgress,thProgress,mafProgress,speedProgress,fuelProgress,tempProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

//        create page adapter to control the pages
        PagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                invalidateOptionsMenu();
            }
        });

        initVal();

//        control the widgets to run on pages
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        createProgress();
                        callProgress(coolantProgress,500,1);
                        callProgress(engineProgress,val_engine,1);
                        callProgress(rpmProgress,val_rpm,100);
                        callProgress(torqueProgress,val_torque,1);
                        callProgress(thProgress,val_throttle,1);
                        callProgress(mafProgress,val_maf,1);
                        callProgress(speedProgress,val_speed,1);
                        callProgress(fuelProgress,val_fuel,1);
                        callProgress(tempProgress,val_coolant,1);
                    }
                });
            }
        }, 1000, 100);

    }

    public void initVal(){
        val_coolant = 89;
        val_engine = 94;
        val_rpm = 8000;
        val_maf = 3;
        val_throttle = 60;
        val_torque = 40;
        val_speed = 400;
        val_fuel = 75;
    }

    public void createProgress(){
        coolantProgress = dashboardFragment.getCoolantProgress();
        engineProgress = dashboardFragment.getEngineProgress();
        rpmProgress = dashboardFragment.getRPMProgress();
        torqueProgress = dashboardFragment.getTorqueProgress();
        thProgress = dashboardFragment.getThrottleProgress();
        mafProgress = dashboardFragment.getMAFProgress();
        speedProgress = infoSpeedFragment.getSpeedProgress();
        fuelProgress = infoEnergyFragment.getFuelProgress();
        tempProgress = infoTempFragment.getCoolantProgress();
    }

    public void callProgress(ArcProgress progress, int val, int countUp){
//        if the current value of the progress less than max value
//                so that the progress will be stopped
        if (progress != null && progress.getProgress() < val)
            progress.setProgress(progress.getProgress() + countUp);
    }

    public void setVal_coolant(int val_coolant) {
        this.val_coolant = val_coolant;
    }

    public void setVal_engine(int val_engine) {
        this.val_engine = val_engine;
    }

    public void setVal_rpm(int val_rpm) {
        this.val_rpm = val_rpm;
    }

    public void setVal_torque(int val_torque) {
        this.val_torque = val_torque;
    }

    public void setVal_throttle(int val_throttle) {
        this.val_throttle = val_throttle;
    }

    public void setVal_maf(int val_maf) {
        this.val_maf = val_maf;
    }

    public void setVal_speed(int val_speed) {
        this.val_speed = val_speed;
    }

    public void setVal_fuel(int val_fuel) {
        this.val_fuel = val_fuel;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return true;
    }



    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        Fragment[] fragments;

        public ScreenSlidePagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);

            // all fragment pages that shown on dashboard viewpager
            fragments = new Fragment[NUM_PAGES];
            dashboardFragment = new DashboardFragment();
            infoSpeedFragment = new InfoSpeedFragment();
            infoEnergyFragment = new InfoEnergyFragment();
            infoTempFragment = new InfoTempFragment();

            fragments[0] = dashboardFragment;
            fragments[1] = infoSpeedFragment;
            fragments[2] = infoEnergyFragment;
            fragments[3] = infoTempFragment;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }


    }
}
