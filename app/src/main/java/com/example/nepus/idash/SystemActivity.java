package com.example.nepus.idash;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class SystemActivity extends FragmentActivity{
    private static final int NUM_PAGES = 4;

    private DashboardFragment dashboardFragment;
    private InfoSpeedFragment infoSpeedFragment;
    private InfoTempFragment infoTempFragment;
    private InfoEnergyFragment infoEnergyFragment;


    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    private int val_coolant, val_engine, val_rpm, val_torque, val_th,val_maf;
    private int val_speed, val_fuel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        viewPager = (ViewPager) findViewById(R.id.pager);

        pagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                invalidateOptionsMenu();
            }
        });

        val_coolant = 100;
        val_engine = 90;
        val_rpm = 3500;
        val_maf = 1500;
        val_th = 300;
        val_torque = 1200;
        val_speed = 160;
        val_fuel = 75;

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArcProgress coolantProgress = dashboardFragment.getCoolantProgress();
                        ArcProgress engineProgress = dashboardFragment.getEngineProgress();
                        ArcProgress rpmProgress = dashboardFragment.getRPMProgress();
                        ArcProgress torqueProgress = dashboardFragment.getTorqueProgress();
                        ArcProgress thProgress = dashboardFragment.getThProgress();
                        ArcProgress mafProgress = dashboardFragment.getMAFProgress();

                        if (coolantProgress.getProgress() < val_coolant)
                            coolantProgress.setProgress(coolantProgress.getProgress() + 1);

                        if (engineProgress.getProgress() < val_engine)
                            engineProgress.setProgress(engineProgress.getProgress() + 1);

                        if (rpmProgress.getProgress() < val_rpm)
                            rpmProgress.setProgress(rpmProgress.getProgress() + 100);

                        if (torqueProgress.getProgress() < val_torque)
                            torqueProgress.setProgress(torqueProgress.getProgress() + 100);

                        if (thProgress.getProgress() < val_th)
                            thProgress.setProgress(thProgress.getProgress() + 10);

                        if (mafProgress.getProgress() < val_maf)
                            mafProgress.setProgress(mafProgress.getProgress() + 19);

                        ArcProgress speedProgress = infoSpeedFragment.getSpeedProgress();

                        if (speedProgress.getProgress() < val_speed)
                            speedProgress.setProgress(speedProgress.getProgress() + 1);

                        ArcProgress fuelProgress = infoEnergyFragment.getFuelProgress();

                        if (fuelProgress != null && fuelProgress.getProgress() < val_fuel)
                            fuelProgress.setProgress(fuelProgress.getProgress() + 1);

                        ArcProgress tempProgress = infoTempFragment.getCoolantProgress();

                        if (tempProgress != null && tempProgress.getProgress() < val_coolant)
                            tempProgress.setProgress(tempProgress.getProgress() + 1);





                    }
                });
            }
        }, 1000, 100);

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
