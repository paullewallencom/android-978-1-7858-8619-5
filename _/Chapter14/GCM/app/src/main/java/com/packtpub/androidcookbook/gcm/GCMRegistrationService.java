package com.packtpub.androidcookbook.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;


public class GCMRegistrationService extends IntentService {

    private final String SENT_TOKEN="SENT_TOKEN";

    public GCMRegistrationService() {
        super("GCMRegistrationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        super.onCreate();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        try {
            InstanceID instanceID = InstanceID.getInstance(this);
            String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            Log.i("GCMRegistrationService", "GCM Registration Token: " + token);
            //sendTokenToServer(token);
            sharedPreferences.edit().putBoolean(SENT_TOKEN, true).apply();
        } catch (Exception e) {
            sharedPreferences.edit().putBoolean(SENT_TOKEN, false).apply();
        }
    }
}
