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
    private static final int NUM_PAGES = 5;

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private int num_widget = 1;

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
        getMenuInflater().inflate(R.menu.menu_add_widget, menu);
        menu.findItem(R.id.menu_add_item).setEnabled(viewPager.getCurrentItem() == 0);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
//            case R.id.menu_add_item:
//                addWidget();
//                return true;
        }
        return true;
    }

//    public void addWidget(){
//        final ViewGroup newView = (ViewGroup) LayoutInflater.from(this).inflate(
//                R.layout.fragment_dashboard,getApplicationContext(),false)   ;
//
//        viewPager.addView(newView);
//    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        Fragment[] fragments;

        public ScreenSlidePagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);

            fragments = new Fragment[NUM_PAGES];
            fragments[0] = new DashboardFragment();
            for (int i=1; i<fragments.length; ++i)
                fragments[i] = new InformationFragment();
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
