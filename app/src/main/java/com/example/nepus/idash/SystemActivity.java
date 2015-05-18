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

public class SystemActivity extends FragmentActivity{
    private static final int NUM_PAGES = 4;

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

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
            fragments[0] = new DashboardFragment();
            fragments[1] = new InfoSpeedFragment();
            fragments[2] = new InfoEnergyFragment();
            fragments[3] = new InfoTempFragment();


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
