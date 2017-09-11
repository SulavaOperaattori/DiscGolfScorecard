package com.example.nks.discgolfscorecard;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainScreen extends Activity implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    TextView scoreCount, numberOfHole, ParValue, locationText;
    private Button counter, reset, lastHole, nextHole, courseAdder;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    int hole = 1;
    public static final String TAG = MainScreen.class.getSimpleName();
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10000)        // 10 seconds, in milliseconds
                .setFastestInterval(1000); // 1 second, in milliseconds

        locationText = findViewById(R.id.locText);
        nextHole = findViewById(R.id.nextHole);
        nextHole.setOnClickListener(MainScreen.this);
        lastHole = findViewById(R.id.lastHole);
        lastHole.setOnClickListener(MainScreen.this);
        counter = findViewById(R.id.counterUpID);
        counter.setOnClickListener(MainScreen.this);
        reset = findViewById(R.id.resetBtnID);
        courseAdder = findViewById(R.id.courseAdder);
        courseAdder.setOnClickListener(MainScreen.this);
        reset.setOnClickListener(MainScreen.this);
        scoreCount = findViewById(R.id.scoreCountID);
        numberOfHole = findViewById(R.id.numberOfHoleID);
        ParValue = findViewById(R.id.ParValue);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            default: break;

            case R.id.counterUpID:
                String score = scoreCount.getText().toString();
                int holePar = Integer.parseInt(score);
                holePar++;
                scoreCount.setText(String.valueOf(holePar));
                break;

            case R.id.resetBtnID:
                scoreCount.setText("0");
                hole = 0;
                break;

            case R.id.nextHole:
                if (hole < 18) {
                    hole++;
                    numberOfHole.setText(String.valueOf(hole));
                    holePar = 3;
                    scoreCount.setText(String.valueOf(holePar));
                    ParValue.setText(String.valueOf(holePar));
                    break;
                }

            case R.id.lastHole:
                if (hole > 0) {
                    hole--;
                    numberOfHole.setText(String.valueOf(hole));
                    holePar = 3;
                    scoreCount.setText(String.valueOf(holePar));
                    ParValue.setText(String.valueOf(holePar));
                    break;
                }

            case R.id.courseAdder:
                Intent CourseBuilder = new Intent(MainScreen.this, CourseBuilderActivity.class);
                startActivity(CourseBuilder);
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        // Check for locational permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + BuildConfig.APPLICATION_ID)));
        } else {
            Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (location == null) {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
            } else {
                newLocation(location);
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (android.content.IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            Log.i(TAG, "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    public void newLocation(Location location) {
        Log.d(TAG, location.toString());
        Geocoder geo = new Geocoder(getBaseContext(), Locale.getDefault());
        List<Address> addresses;
        try {
            // Get the locality from coordinates
            addresses = geo.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            if (addresses.size() > 0 ) {
                locationText.setText(addresses.get(0).getLocality());
            }
        } catch (IOException e) {
            locationText.setText("error");
            e.printStackTrace();
        }

    }

    public void onLocationChanged(Location location) {
        newLocation(location);
    }



}



