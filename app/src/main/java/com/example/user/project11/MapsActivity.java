package com.example.user.project11;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity {

    GoogleMap googleMap;
    ArrayList<LatLng> points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        points = new ArrayList<LatLng>();

        // Getting reference to the SupportMapFragment of activity_main.xml
        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        // Getting GoogleMap object from the fragment
        googleMap = fm.getMap();

        // Enabling MyLocation Layer of Google Map
        googleMap.setMyLocationEnabled(true);

        // Setting OnClick event listener for the Google Map
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {

                // Instantiating the class MarkerOptions to plot marker on the map
                MarkerOptions markerOptions = new MarkerOptions();

                // Setting latitude and longitude of the marker position
                markerOptions.position(point);

                // Setting titile of the infowindow of the marker
                markerOptions.title("Position");

                // Setting the content of the infowindow of the marker
                markerOptions.snippet("Latitude:"+point.latitude+","+"Longitude:"+point.longitude);

                // Instantiating the class PolylineOptions to plot polyline in the map
                PolylineOptions polylineOptions = new PolylineOptions();

                // Setting the color of the polyline
                polylineOptions.color(Color.RED);

                // Setting the width of the polyline
                polylineOptions.width(3);

                // Adding the taped point to the ArrayList
                points.add(point);

                // Setting points of polyline
                polylineOptions.addAll(points);

                // Adding the polyline to the map
                googleMap.addPolyline(polylineOptions);

                // Adding the marker to the map
                googleMap.addMarker(markerOptions);

            }
        });

        googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {

            @Override
            public void onMapLongClick(LatLng point) {
                // Clearing the markers and polylines in the google map
                googleMap.clear();

                // Empty the array list
                points.clear();
            }
        });
    }


}