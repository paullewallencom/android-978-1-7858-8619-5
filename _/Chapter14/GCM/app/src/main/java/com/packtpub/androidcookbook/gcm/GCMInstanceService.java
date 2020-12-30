package com.packtpub.androidcookbook.gcm;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

public class GCMInstanceService extends InstanceIDListenerService {

    @Override
         public void onTokenRefresh() {
        Intent intent = new Intent(this, GCMRegistrationService.class);
        startService(intent);
    }
}
