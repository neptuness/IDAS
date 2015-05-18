package com.example.nepus.idash;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    public static final String DATE = MapsActivity.class.getPackage().getName() + "DATE";

    Marker mMarker;
    LocationManager lm;
    double lat, lng;
    private List<String> routeData = new ArrayList<>();
    private List<String> eachRouteData = new ArrayList<>();
    private List<String> eachValueData = new ArrayList<>();
    private boolean markerCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        String str = getIntent().getExtras().getString(DATE);
//        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
        routeData = Arrays.asList(str.split("\\s*New Data\\s*"));

//        Log.i("TestingX", routeData.toString());
        for(String eachLine: routeData)
        {
            if(eachLine.length()<5)
            {
                continue;
            }
            eachRouteData = Arrays.asList(eachLine.split("\\s*\n\\s*"));
            PolylineOptions rectLine = new PolylineOptions();
            markerCheck = true;
            Log.i("TestingX", "Here here");
            for(String eachValue:eachRouteData)
            {

                eachValueData = Arrays.asList(eachValue.split("\\s*,\\s*"));
//                Log.i("TestingX", eachValueData.get(0).toString());
                if(markerCheck)
                {
                    double driveTime = Double.parseDouble(eachValueData.get(2));

                    mMarker = mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(eachValueData.get(0)), Double.parseDouble(eachValueData.get(1)))).title("StartPoint").snippet("Time: "+driveTime));
                    Log.i("TestingX", eachValueData.toString());
                    markerCheck = false;
                }
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        new LatLng(Double.parseDouble(eachValueData.get(0)), Double.parseDouble(eachValueData.get(1))), 19));
                rectLine.add(new LatLng(Double.parseDouble(eachValueData.get(0)), Double.parseDouble(eachValueData.get(1))));
            }
            rectLine.color(Color.RED);
            mMap.addPolyline(rectLine);
//            Log.i("TestingX", eachRouteData.toString());
        }
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
//                new LatLng(13.728643, 100.775061), 19));
//
//        mMarker = mMap.addMarker(new MarkerOptions().position(new LatLng(13.728643, 100.775061)).title("StartPoint"));
//
//        PolylineOptions rectLine = new PolylineOptions()
//                .add(new LatLng(13.728643, 100.775061))
//                .add(new LatLng(13.729586, 100.775094))
//                .add(new LatLng(13.730138, 100.775035))
//                .add(new LatLng(13.730771, 100.775038));
//
//        rectLine.color(Color.RED);
//        mMap.addPolyline(rectLine);

    }



//    PolylineOptions line = new PolylineOptions();
//
//    LocationListener listener = new LocationListener() {

//        public void onLocationChanged(Location loc) {
//            LatLng coordinate = new LatLng(loc.getLatitude()
//                    , loc.getLongitude());
//            lat = loc.getLatitude();
//            lng = loc.getLongitude();

//            mMap.addMarker(new MarkerOptions().position(new LatLng(13.729104,  100.775048)).title("StartPoint"));
//            LatLng coordinate = new LatLng(13.729104,  100.775048);


//            lat = loc.getLatitude();
//            lng = loc.getLongitude();
//            if(mMarker != null) {
//                line.add(new LatLng(lat, lng)).width(5).color(Color.RED);
//                mMap.addPolyline(line);
//                mMarker.remove();
//
//            }
//           mMarker = mMap.addMarker(new MarkerOptions()
//                    .position(new LatLng(13.728643, 100.775061)));
//            mMap.addPolyline(line);


//            line.add(new LatLng(13.729104, 100.775048)).width(5).color(Color.RED);
//            //mMap.addPolyline(line);
//
//            line.add(new LatLng(13.729586, 100.775094)).width(5).color(Color.RED);
//           // mMap.addPolyline(line);
//
//            line.add(new LatLng(13.730138, 100.775035)).width(5).color(Color.RED);
//          //  mMap.addPolyline(line);
//
//            line.add(new LatLng(13.730771, 100.775038)).width(5).color(Color.RED);
//          //  mMap.addPolyline(line);
//
//            line.add(new LatLng(13.731536, 100.775032)).width(5).color(Color.RED);
//            mMap.addPolyline(line);


//            mMarker = mMap.addMarker(new MarkerOptions()
//                    .position(new LatLng(lat, lng)));

//            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
//                    coordinate, 16));
//        }
//
//        public void onStatusChanged(String provider, int status
//                , Bundle extras) {}
//        public void onProviderEnabled(String provider) {}
//        public void onProviderDisabled(String provider) {}
//    };

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();


//        lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
//
//        boolean isNetwork =
//                lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//        boolean isGPS =
//                lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
//
//        if(isNetwork) {
//            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER
//                    , 5000, 10, listener);
//            Location loc = lm.getLastKnownLocation(
//                    LocationManager.NETWORK_PROVIDER);
//            if(loc != null) {
//                lat = loc.getLatitude();
//                lng = loc.getLongitude();
//            }
//        }
//
//        if(isGPS) {
//            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER
//                    , 5000, 10, listener);
//            Location loc = lm.getLastKnownLocation(
//                    LocationManager.GPS_PROVIDER);
//            if(loc != null) {
//                lat = loc.getLatitude();
//                lng = loc.getLongitude();
//            }
//        }

    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        //mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }
}
