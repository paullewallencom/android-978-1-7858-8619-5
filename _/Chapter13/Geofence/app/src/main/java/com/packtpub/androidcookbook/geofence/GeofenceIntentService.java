package com.packtpub.androidcookbook.geofence;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;

import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;


public class GeofenceIntentService extends IntentService {

    public GeofenceIntentService() {
        super("GeofenceIntentService");
    }

    protected void onHandleIntent(Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()) {
            Toast.makeText(getApplicationContext(), "Geofence error code= " + geofencingEvent.getErrorCode(), Toast.LENGTH_SHORT).show();
            return;
        }
        int geofenceTransition = geofencingEvent.getGeofenceTransition();
        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_DWELL) {
            sendNotification();
        }
    }

    private void sendNotification() {
        Log.i("GeofenceIntentService", "sendNotification()");
        Uri notificationSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Geofence Alert")
                .setContentText("GEOFENCE_TRANSITION_DWELL")
                .setSound(notificationSoundUri)
                .setLights(Color.BLUE, 500, 500);
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }
}
