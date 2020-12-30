package com.packtpub.androidcookbook.geofence;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final int MINIMUM_RECOMENDED_RADIUS=100;
    GoogleApiClient mGoogleApiClient;
    PendingIntent mGeofencePendingIntent;

    ResultCallback mResultCallback = new ResultCallback() {
        @Override
        public void onResult(Result result) {
            Log.i("onResult()", "result: " + result.getStatus().toString());
        }
    };

    GoogleApiClient.ConnectionCallbacks mConnectionCallbacks = new GoogleApiClient.ConnectionCallbacks() {
        @Override
        public void onConnected(Bundle bundle) {
            try {
                LocationServices.GeofencingApi.addGeofences(
                    mGoogleApiClient,
                    createGeofencingRequest(),
                    getGeofencePendingIntent()
                ).setResultCallback(mResultCallback);
            } catch (SecurityException e) {
                Log.i("onConnected()", "SecurityException: " + e.getMessage());
            }
        }
        @Override
        public void onConnectionSuspended(int i) {}
    };

    GoogleApiClient.OnConnectionFailedListener mOnConnectionFailedListener = new GoogleApiClient.OnConnectionFailedListener() {
        @Override
        public void onConnectionFailed(ConnectionResult connectionResult) {
            Log.i("onConnectionFailed()", "connectionResult: " +connectionResult.toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupGoogleApiClient();
    }

    protected synchronized void setupGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
            .addConnectionCallbacks(mConnectionCallbacks)
            .addOnConnectionFailedListener(mOnConnectionFailedListener)
            .addApi(LocationServices.API)
            .build();
        mGoogleApiClient.connect();
    }

    private PendingIntent getGeofencePendingIntent() {
        if (mGeofencePendingIntent != null) {
            return mGeofencePendingIntent;
        }
        Intent intent = new Intent(this, GeofenceIntentService.class);
        return PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private List createGeofenceList() {
        List<Geofence> geofenceList = new ArrayList<Geofence>();
        geofenceList.add(new Geofence.Builder()
                .setRequestId("GeofenceLocation")
                .setCircularRegion(
                        37.422006, //Latitude
                        -122.084095, //Longitude
                        MINIMUM_RECOMENDED_RADIUS)
                .setLoiteringDelay(30000)
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_DWELL)
        .build());
        return geofenceList;
    }

    private GeofencingRequest createGeofencingRequest() {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_DWELL);
        builder.addGeofences(createGeofenceList());
        return builder.build();
    }

    @Override
    protected void onStop() {
        super.onStop();

        LocationServices.GeofencingApi.removeGeofences(
                mGoogleApiClient,
                getGeofencePendingIntent()
        ).setResultCallback(mResultCallback); // Result processed in onResult().
    }
}
