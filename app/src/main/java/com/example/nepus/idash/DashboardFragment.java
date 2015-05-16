package com.example.nepus.idash;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.gms.tagmanager.Container;

/**
 * Created by NEPUS on 5/16/15 AD.
 */
public class DashboardFragment extends Fragment {
//    public static final String NUM_WIDGET = DashboardFragment.class.getPackage().toString() + "NUM_WIDGET";
//    private static final String TAG = DashboardFragment.class.getSimpleName();
//    private int num_widget;
//
//    public DashboardFragment() {
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        int numWidget = savedInstanceState.getInt(NUM_WIDGET);
//        Log.d(TAG, "" + numWidget);
////        savedInstanceState.putSerializable();
//    }

    private GridView gridView;
    private ViewGroup rootView;
    private Context context;

    public DashboardFragment() { }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.menu_add_widget, menu);
//        menu.findItem(R.id.menu_add_item).getActionView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.menu_add_item:
//                addWidget();
//                return true;
//        }
        return true;
    }

//    public void addWidget(){
//
//        final ViewGroup newView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(
//                R.layout.fragment_dashboard, gridView, false);
//        rootView.addView(newView);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_dashboard,container,false);

        gridView = (GridView) rootView.findViewById(R.id.gridview);
        gridView.setAdapter(new DashboardAdapter(getActivity(),6));

//        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, "" + position,
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
        return rootView;
    }
}
