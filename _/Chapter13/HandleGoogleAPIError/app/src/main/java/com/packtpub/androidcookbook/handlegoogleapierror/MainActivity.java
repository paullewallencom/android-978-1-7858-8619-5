package com.packtpub.androidcookbook.handlegoogleapierror;

import android.app.Dialog;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.ErrorDialogFragment;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_RESOLVE_GOOGLE_CLIENT_ERROR=1;
    boolean mResolvingError;
    GoogleApiClient mGoogleApiClient;
    TextView mTextView;
    Button mButton;


    GoogleApiClient.ConnectionCallbacks mConnectionCallbacks = new GoogleApiClient.ConnectionCallbacks() {
        @Override
        public void onConnected(Bundle bundle) {
            mButton.setEnabled(true);
        }
        @Override
        public void onConnectionSuspended(int i) {}
    };

    GoogleApiClient.OnConnectionFailedListener mOnConnectionFailedListener = new GoogleApiClient.OnConnectionFailedListener() {
        @Override
        public void onConnectionFailed(ConnectionResult connectionResult) {
            Toast.makeText(MainActivity.this, connectionResult.toString(), Toast.LENGTH_LONG).show();
            if (mResolvingError) {
                return;
            } else if (connectionResult.hasResolution()) {
                mResolvingError = true;
                try {
                    connectionResult.startResolutionForResult(MainActivity.this, REQUEST_RESOLVE_GOOGLE_CLIENT_ERROR);
                } catch (IntentSender.SendIntentException e) {
                    mGoogleApiClient.connect();
                }
            } else {
                showGoogleAPIErrorDialog(connectionResult.getErrorCode());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView);
        mButton = (Button) findViewById(R.id.button);
        mButton.setEnabled(false);
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

    public void getLocation(View view) {
        try {
            Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
            if (lastLocation != null) {
                mTextView.setText(
                    "Latitude="+lastLocation.getLatitude()+"\n"+
                    "Longitude="+lastLocation.getLongitude());
            }
        }
        catch (SecurityException e) {}
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_RESOLVE_GOOGLE_CLIENT_ERROR) {
            mResolvingError = false;
            if (resultCode == RESULT_OK &&
                    !mGoogleApiClient.isConnecting() &&
                    !mGoogleApiClient.isConnected()) {
                mGoogleApiClient.connect();
            }
        }
    }

    private void showGoogleAPIErrorDialog(int errorCode) {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        Dialog errorDialog = googleApiAvailability.getErrorDialog(this, errorCode, REQUEST_RESOLVE_GOOGLE_CLIENT_ERROR);
        errorDialog.show();
    }

}
