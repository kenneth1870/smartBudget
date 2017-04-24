package com.example.kenneth.smartbudget;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.R.attr.fragment;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener,
        GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private Marker posMarker;
    private String locDirection;
    private LocationManager handle;
    private String providerServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        InitServices();
        ShowActualPosition();
    }

    public void InitServices() {

        handle = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria c = new Criteria();
        c.setAccuracy(Criteria.ACCURACY_FINE);
        providerServices = handle.getBestProvider(c, true);
        Toast.makeText(getApplicationContext(), "Provedor: " + providerServices, Toast.LENGTH_SHORT).show();

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        handle.requestLocationUpdates(providerServices, 10000, 1, this);
    }

    public void ShowActualPosition() {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = handle.getLastKnownLocation(providerServices);
        if(location!=null){
            AddMyMarker(location.getLatitude(), location.getLongitude());
            getDirection(location);
        }
        else
            Toast.makeText(getApplicationContext(), "No se pudo obtener la actual posición", Toast.LENGTH_SHORT).show();
    }

    public void getDirection(Location loc){

        if(loc != null){
            if(loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0){
                try{
                    Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                    List<Address> direc = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
                    if(!direc.isEmpty()){
                        Address direcFinal = direc.get(0);
                        locDirection = direcFinal.getAddressLine(0);
                    }
                } catch (IOException e) {
                    locDirection = ""+e;
                }
            }
        }
    }

    private void AddMyMarker(double lat, double lon) {

        LatLng mPos = new LatLng(lat, lon);
        CameraUpdate MyLocation = CameraUpdateFactory.newLatLngZoom(mPos, 16);
        if (posMarker != null) posMarker.remove();
        posMarker = mMap.addMarker(new MarkerOptions().position(mPos).title("My actual position")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_add_location)));
        mMap.animateCamera(MyLocation);
        mMap.setOnMarkerClickListener(this);
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        GastosFragment.DirecUser = locDirection;
        super.finish();
        return true;
    }
}
