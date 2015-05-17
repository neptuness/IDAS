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
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_dashboard,container,false);

        gridView = (GridView) rootView.findViewById(R.id.gridview);
        gridView.setAdapter(new DashboardAdapter(getActivity(),6));

        return rootView;
    }
}
