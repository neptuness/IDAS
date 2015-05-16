package com.example.nepus.idash;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by NEPUS on 5/16/15 AD.
 */
public class DashboardFragment extends Fragment {
    public static final String NUM_WIDGET = DashboardFragment.class.getPackage().toString() + "NUM_WIDGET";
    private static final String TAG = DashboardFragment.class.getSimpleName();
    private int num_widget;

    public DashboardFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int numWidget = savedInstanceState.getInt(NUM_WIDGET);
        Log.d(TAG, "" + numWidget);
//        savedInstanceState.putSerializable();
    }
}
