package com.miguelcr.maps;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    LatLng previousLocation = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(50.866077,20.628568);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        mMap.setOnMapClickListener(this);



    }

    @Override
    public void onMapClick(LatLng latLng) {
        Marker m = mMap.addMarker(
                new MarkerOptions()
                        .position(latLng)
                        .title("Marker new")
        );

        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

        if(previousLocation!=null) {
            // Instantiates a new Polyline object and adds points to define a rectangle
            PolylineOptions rectOptions = new PolylineOptions()
                    .add(previousLocation)
                    .add(latLng);

            // Get back the mutable Polyline
            Polyline polyline = mMap.addPolyline(rectOptions);

            // Polyline style
            polyline.setColor(ContextCompat.getColor(this,R.color.colorAccent));
            polyline.setWidth(5.0f);
        }

        previousLocation = latLng;
    }
}
