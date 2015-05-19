package com.example.nepus.idash;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;



import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    public static final String DATE = MapsActivity.class.getPackage().getName() + "DATE";

    Marker mMarker;
    private boolean layoutToggle = true;
    private Random rnd = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        mMap.setMyLocationEnabled(true); // Enable my location button

        String str = getIntent().getExtras().getString(DATE); //Get latitude,longitude from Another class
        List<String> routeData = Arrays.asList(str.split("\\s*New Data\\s*")); // split string when find "New Data" to list
//        Log.i("TestingX", routeData.toString());
        for(String eachLine: routeData)
        {
            if(eachLine.length()<5)
                continue;
            List<String> eachRouteData = Arrays.asList(eachLine.split("\\s*\n\\s*")); // split each line to list
            PolylineOptions rectLine = new PolylineOptions();
            boolean markerCheck = true;
            double distance = 0;
//            Log.i("TestingX", String.valueOf(eachRouteData.size()));
//            Log.i("TestingX", "Here here");
//            for(String eachValue:eachRouteData)
//            {
            Log.i("Distance", "NewRoute");

            for(int i = 0;i< eachRouteData.size();i++)
                {
                    String eachValue = eachRouteData.get(i);
                    String nextValue;
                    List<String> eachValueData = Arrays.asList(eachValue.split("\\s*,\\s*")); // split string when find "," to list
                    if(i< eachRouteData.size()-1)
                    {
                        nextValue = eachRouteData.get(i + 1); // get next value
                        List<String> nextValueData = Arrays.asList(nextValue.split("\\s*,\\s*")); // split string when find "," to list
//                        Log.i("Lat1", eachValueData.get(0));
//                        Log.i("Lon1", eachValueData.get(1));
//                        Log.i("Lat2", nextValueData.get(0));
//                        Log.i("Lon2", nextValueData.get(1));

                        distance =  distance + distanceCalculate(Double.parseDouble(eachValueData.get(0)), Double.parseDouble(eachValueData.get(1)) // calculate all distance
                                ,Double.parseDouble(nextValueData.get(0)) ,Double.parseDouble(nextValueData.get(1)) );
//                        Log.i("ds", String.valueOf(distance));
                    }


                if(markerCheck && i == eachRouteData.size()-1) // check condition to last set of data
                {

                    List<String> startValue = Arrays.asList(eachRouteData.get(0).split("\\s*,\\s*")); // split string when find "," to list (only first line value)
                    List<String> endValue = Arrays.asList(eachRouteData.get(eachRouteData.size() - 1).split("\\s*,\\s*")); // split string when find "," to list (only last line value)
                    int startTime = Integer.parseInt(startValue.get(2));
                    int stopTime = Integer.parseInt(endValue.get(2));

                    distance = distance /1000; // convert distance to km

                    mMarker = mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(startValue.get(0)), Double.parseDouble(startValue.get(1)))) // add marker to map
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                    mMarker.setTitle("StartPoint");

                        mMarker.setSnippet("DriveTime: " + String.valueOf((stopTime - startTime)) + " minute" // add information to marker
                                + "\nDistance: " + distance + " km");

                    mMarker = mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(endValue.get(0)), Double.parseDouble(endValue.get(1)))) // add marker to map
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                    mMarker.setTitle("StopPoint");

                    mMarker.setSnippet("DriveTime: "+String.valueOf((stopTime-startTime)) + " minute" // add information to marker
                            + "\nDistance: " + distance + " km");

                    markerCheck = false;

                }
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        new LatLng(Double.parseDouble(eachValueData.get(0)), Double.parseDouble(eachValueData.get(1))), 19)); //move camera to current line position

                rectLine.add(new LatLng(Double.parseDouble(eachValueData.get(0)), Double.parseDouble(eachValueData.get(1)))); // add lat,lon to rectLine
            }

            rectLine.color(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))); //random color of polyline

            mMap.addPolyline(rectLine); // add polyline to map
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



    private double distanceCalculate(double lat1, double lon1,double lat2, double lon2) {

        final int R = 6371; // Radius of the earth

        Double latDistance = deg2rad(lat2 - lat1);
        Double lonDistance = deg2rad(lon2 - lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        distance = Math.pow(distance, 2); // - Math.pow(high,2) if have a high
        distance = Math.sqrt(distance);
//        Log.i("Distance", String.valueOf(distance));
        return distance;
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    public void changeLayout(View view)
    {
        if(layoutToggle)
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            Toast.makeText( getBaseContext(),"Satellite Mode", Toast.LENGTH_SHORT).show();
            layoutToggle = false;
        }
        else
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            Toast.makeText( getBaseContext(),"Normal Mode", Toast.LENGTH_SHORT).show();
            layoutToggle = true;
        }
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
