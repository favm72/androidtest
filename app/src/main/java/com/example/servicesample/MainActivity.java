package com.example.servicesample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Context ctx;
    TextView lat;
    TextView lng;
    Button btn_start;
    Button btn_stop;
//    MyLocation gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ctx = this;
        lat = findViewById(R.id.tv_lat);
        lng = findViewById(R.id.tv_lng);
        btn_start = findViewById(R.id.btn_start);
        btn_stop = findViewById(R.id.btn_stop);
//        gps = new MyLocation(ctx) {
//            @Override
//            public void onLocationChanged(Location location) {
//                Toast.makeText(ctx, "Hola", Toast.LENGTH_SHORT).show();
//            }
//        };
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickStart(v);
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickStop(v);
            }
        });

        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION }, 22);

        startService(new Intent(this, MyLocation.class));
        //startService(new Intent(this, BgService.class));
        /*
        //startService(new Intent(this, BgService.class));
        OFfilneGPS GPS =  new OFfilneGPS(this){
            @Override
            public void doSomething() {
                lat.setText(this.LAT);
                lng.setText(this.LNG);
            }
        };*/
    }

    public void onClickStart(View view) {
//        gps.start();
    }
    public void onClickStop(View view) {
//        gps.stop();
    }
}
