package com.example.getlocationgps;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LocationListener {

    LocationManager lmgr;
    TextView lon,lat;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lmgr = (LocationManager) getSystemService(LOCATION_SERVICE);
        lon = (TextView) findViewById(R.id.textView);
        lat = (TextView) findViewById(R.id.textView2);
        String provider=lmgr.getBestProvider(new Criteria(), false);
        if(checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            Location l = lmgr.getLastKnownLocation(provider);
        if(l != null){
            onLocationChanged(l);
        } else  {
            lat.setText("null");
            lon.setText("null");
        }
                }
    }

    @Override
    public void onLocationChanged(Location location) {
        lat.setText(location.getLatitude() + "");
        lon.setText(location.getLongitude() + "");
    }

    //Auto Generated
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}