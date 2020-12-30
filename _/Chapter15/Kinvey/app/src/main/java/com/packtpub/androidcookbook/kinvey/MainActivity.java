package com.packtpub.androidcookbook.kinvey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyPingCallback;

public class MainActivity extends AppCompatActivity {

    final Client mKinveyClient = new Client.Builder("your_app_key", "your_app_secret", this.getApplicationContext()).build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mKinveyClient.ping(new KinveyPingCallback() {
            public void onFailure(Throwable t) {
                Log.d("KinveyPingCallback", "Kinvey Ping Failed", t);
            }

            public void onSuccess(Boolean b) {
                Log.d("KinveyPingCallback", "Kinvey Ping Success");
            }
        });
    }
}
