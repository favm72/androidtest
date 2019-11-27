package com.example.servicesample;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

public class MyLocation extends Service implements LocationListener {

    Context context = this;
    LocationManager locationManager;
    String lat = "";
    String lng = "";

    private void onRun() {
        Integer fineLocation = ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        Integer coarseLocation = ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION);

        if (fineLocation != PackageManager.PERMISSION_GRANTED
            || coarseLocation != PackageManager.PERMISSION_GRANTED) {
            // No se puede pedir permisos de acceso al geolocalización desde un servicio
            Toast.makeText(context, "Solicitar permiso de Geolocalización", Toast.LENGTH_SHORT).show();
            return;
        }

        if ( !locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            Toast.makeText(context, "Encender GPS", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(context, "Calcular", Toast.LENGTH_SHORT).show();
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // Distancia mínima de 10 metros de distancia en los puntos
        // Intervalo de medio segundo de actualización de gps
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 0, this);
    }

    @Override
    public void onCreate() {
        onRun();
    }

    @Override
    public void onLocationChanged(Location location) {
        if ((location.getLatitude() + "").equals(lat)){
            return;
        }
        lat = location.getLatitude() + "";
        lng = location.getLongitude() + "";

        SharedPreferences shared = getSharedPreferences("DNIDOCUMENTADOR", MODE_PRIVATE);
        String id_motorized = (shared.getString("VALOR01", "0"));


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

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
