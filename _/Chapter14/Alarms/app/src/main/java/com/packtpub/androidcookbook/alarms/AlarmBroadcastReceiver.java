package com.packtpub.androidcookbook.alarms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmBroadcastReceiver extends BroadcastReceiver {

    public static final String ACTION_ALARM="com.packtpub.androidcookbook.alarms.ACTION_ALARM";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ACTION_ALARM.equals(intent.getAction())) {
            Toast.makeText(context, ACTION_ALARM, Toast.LENGTH_SHORT).show();
        }
    }
}
