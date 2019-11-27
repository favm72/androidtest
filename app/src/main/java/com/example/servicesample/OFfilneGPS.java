package com.example.servicesample;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

public class OFfilneGPS implements LocationListener {

    LocationManager locationManager;
    public String LAT = "";
    public String LNG = "";
    Context ctx;
    private int mInterval = 500; // 3 seconds by default, can be changed later
    private Handler mHandler;

    public boolean getLocation() {
        try {
            if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((MainActivity)ctx, new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION }, 22);
                return false;
            }
            else {
                locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 200, 0, (LocationListener) this);
                return true;
            }
        }
        catch(SecurityException e) {
            e.printStackTrace();
            return  false;
        }
    }

    public OFfilneGPS(Context ctx){
        this.ctx = ctx;
        Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            public void run() {
                mHandler = new Handler();
                start();
            }
        }, 1000);   //5 seconds
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
           on_run();
        }
    };

    public void on_run(){
        try {
            Boolean result = getLocation(); //this function can change value of mInterval.
            if (result) {
                doSomething();
            }
            else {
                Toast.makeText(ctx, "incorrect update", Toast.LENGTH_LONG).show();
            }
        } finally {
            mHandler.postDelayed(mStatusChecker, mInterval);
        }
    }

    public void start() {
        mStatusChecker.run();
    }

    public void doSomething(){

    }

    public void stop() {
        mHandler.removeCallbacks(mStatusChecker);
    }

    @Override
    public void onLocationChanged(Location location) {
        LAT = location.getLatitude() + "";
        LNG = location.getLongitude() + "";
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(ctx, "Please Enable GPS", Toast.LENGTH_SHORT).show();
    }
}
