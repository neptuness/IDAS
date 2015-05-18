package com.example.nepus.idash;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InformationFragment extends Fragment {
    TextView dist_total, dist_sinceStart, dist_timeSinceStart;
    TextView remain_fuel, remain_distance, fuel_rate, fuel_pressure;
    TextView rpm, torque, max_speed, fastest_timeSpeed;
    TextView temp_coolant, temp_engine;

    public InformationFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_info,container,false);

        dist_total = (TextView) rootView.findViewById(R.id.val_totalDistance);
        dist_sinceStart = (TextView) rootView.findViewById(R.id.val_distanceSinceStart);
        dist_timeSinceStart = (TextView) rootView.findViewById(R.id.val_timeSinceStart);

        remain_distance = (TextView) rootView.findViewById(R.id.val_remainingDistance);
        remain_fuel = (TextView) rootView.findViewById(R.id.val_fuel);
        fuel_rate = (TextView) rootView.findViewById(R.id.val_fuelRate);
        fuel_pressure = (TextView) rootView.findViewById(R.id.val_fuelPressure);

        rpm = (TextView) rootView.findViewById(R.id.val_rpm);
        torque = (TextView) rootView.findViewById(R.id.val_torque);

        temp_coolant = (TextView) rootView.findViewById(R.id.val_coolantTemp);
        temp_engine  = (TextView) rootView.findViewById(R.id.val_engineTemp);

        return rootView;
    }

    public void setDist_total(int dist_total) {
//        String setting = "km";
//        double value = dist_total;
//        if (setting.equalsIgnoreCase("mile"))
//            value = value * 1.6;
//        this.dist_total.setText("" + value);
    }

    public void setDist_sinceStart(String dist_sinceStart) {
    }

    public void setDist_timeSinceStart(String dist_timeSinceStart) {
    }

    public void setRemain_fuel(String remain_fuel) {
    }

    public void setRemain_distance(String remain_distance) {
    }

    public void setFuel_rate(String fuel_rate) {
    }

    public void setFuel_pressure(String fuel_pressure) {
    }

    public void setRpm(String rpm) {
    }

    public void setTorque(String torque) {
    }

    public void setMax_speed(String max_speed) {
    }

    public void setFastest_timeSpeed(String fastest_timeSpeed) {
    }

    public void setTemp_coolant(String temp_coolant) {
    }

    public void setTemp_engine(String temp_engine) {
    }
}
