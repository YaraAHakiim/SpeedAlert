package com.code95.speedalert;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

/**
 * Created by YaRa on 3/4/2018.
 */

/**
 * Speed Alert class is plays an alert when driving with maximum
 * speed.
 *
 * @Author Yara Abdelhakim
 * @Version 1.0.0
 * @Since 18/3/2018
 */

public class SpeedAlert implements LocationListener {

    private LocationManager mLocationManager;
    private Context mContext;
    private double mMaxSpeed = 30;
    private int mVoiceNoteResId ;
    private String mVoiceNoteUrl;
    private VoiceNotePlayer.Mode mMode;

    /**
     * Constructor
     * @param context
     * @param maxSpeed The max speed at which the alert is played (default value = 30km/h)
     *
     */
    public SpeedAlert(Context context, double maxSpeed) {
        mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        mContext = context;
        if(maxSpeed != 0) {
            mMaxSpeed = maxSpeed;
        }
    }

    /**
     * Method used to start getting location updates
     *
     * @param minUpdateTime Minimum time before getting new updated location.
     * @param minUpdateDistance Minimum distance before getting new updated location.
     */
    public void startUpdates(int minUpdateTime, int minUpdateDistance) {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minUpdateTime, minUpdateDistance, this);
        }
    }

    /**
     * Method used to stop getting location updates
     */
    public void stopUpdates() {
        mLocationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        playAlert(location);
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

    /**
     * Method used to get speed from location, convert it to km/h then play the alert
     * if speed = maxSpeed.
     *
     * @param location used to get speed.
     */
    private void playAlert(Location location) {
        double speedInKmH = location.getSpeed() * 3.6;
        if (speedInKmH >= mMaxSpeed) {
            VoiceNotePlayer.playAudio(mContext, mVoiceNoteUrl, mVoiceNoteResId, mMode);
        }
    }

    public void setVoiceNoteResId(int voiceNoteResId) {
        this.mVoiceNoteResId = voiceNoteResId;
    }

    public void setVoiceNoteUrl(String voiceNoteUrl) {
        this.mVoiceNoteUrl = voiceNoteUrl;
    }

    public void setMode(VoiceNotePlayer.Mode mode) {
        this.mMode = mode;
    }

}
