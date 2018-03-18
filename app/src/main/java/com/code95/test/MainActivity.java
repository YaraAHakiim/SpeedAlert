package com.code95.test;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.code95.speedalert.SpeedAlert;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_LOCATION_PERMISSION_CODE = 101;

    private SpeedAlert mSpeedAlert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpeedAlert = new SpeedAlert(this, 0.2);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mSpeedAlert.startUpdates(1, 1);
        } else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_LOCATION_PERMISSION_CODE) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mSpeedAlert.startUpdates(1, 1);
            }
        }
    }
}
