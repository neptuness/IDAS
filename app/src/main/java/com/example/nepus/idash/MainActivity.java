package com.example.nepus.idash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ic.kmitl.idas.notification.IDASNotificationSystem;

//public class MainActivity extends FragmentActivity {
//    private static final int NUM_PAGES = 3;
//
//    private ViewPager viewPager;
//    private PagerAdapter pagerAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_viewpager);
//
//        viewPager = (ViewPager) findViewById(R.id.pager);
//
//        pagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
//        viewPager.setAdapter(pagerAdapter);
//
//        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
//            @Override
//            public void onPageSelected(int position) {
//                invalidateOptionsMenu();
//            }
//        });
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }
//
//    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
//        public ScreenSlidePagerAdapter(FragmentManager fragmentManager) {
//            super(fragmentManager);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return SystemPagerFragment.create(position);
//        }
//
//        @Override
//        public int getCount() {
//            return NUM_PAGES;
//        }
//    }
//}




/**
 * The launchpad activity for this sample project. This activity launches other activities that
 * demonstrate implementations of common animations.
 */
public class MainActivity extends Activity{
    private static final String TAG = MainActivity.class.getSimpleName();
    private IDASNotificationSystem notificationSystem = IDASNotificationSystem.getInstance();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        notificationSystem.removeAllNotification(this);
        super.onDestroy();
    }

    public void gotoSystem (View view){
        Intent intent = new Intent(this, SystemActivity.class);
        startActivity(intent);
    }

    public void gotoNotification(View view){
        Intent intent = new Intent(this, NotificationActivity.class);
        startActivity(intent);
    }

    public void gotoSetting(View view){
        Intent intent = new Intent(this, SettingActivity2.class);
        startActivity(intent);
    }

    public void gotoHistory(View view){
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }




}
