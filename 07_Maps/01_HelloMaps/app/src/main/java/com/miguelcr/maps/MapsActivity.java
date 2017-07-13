package com.miguelcr.maps;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerDragListener {

    private GoogleMap mMap;

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

        mMap.setOnMapClickListener(this);
        mMap.setOnMarkerDragListener(this);

        // Add a marker in Sydney and move the camera
        LatLng kielceCenter = new LatLng(50.866077,20.628568);

        mMap.addMarker(
                new MarkerOptions()
                        .position(kielceCenter)
                        .title("Marker in Kielce")
                        .snippet("The best city in Poland")
        );

        LatLng sevilleTriana = new LatLng(37.380346,-6.007744);
        mMap.addMarker(
                new MarkerOptions()
                        .position(sevilleTriana)
                        .title("Marker in Seville")
                        .snippet("The best city in Spain")
        );

        mMap.moveCamera(CameraUpdateFactory.newLatLng(kielceCenter));
    }

    @Override
    public void onMapClick(LatLng latLng) {
        // latLng > we receive in this object the position
        // where we clicked on map

        mMap.addMarker(
                new MarkerOptions()
                        .position(latLng)
                        .title("New Marker")
                        .snippet("lat,lon:"+latLng.latitude+","+latLng.longitude)
                        .draggable(true)
        );
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        // Hide the info window, the dialog where
        // we're showing the marker information
        marker.hideInfoWindow();
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        LatLng position = marker.getPosition();
        Log.i("MARKER DRAG","LOCATION: "+position.latitude+","+position.longitude);
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        LatLng latLng = marker.getPosition();
        marker.setSnippet("lat,lon:"+latLng.latitude+","+latLng.longitude);
        marker.showInfoWindow();
    }
}
