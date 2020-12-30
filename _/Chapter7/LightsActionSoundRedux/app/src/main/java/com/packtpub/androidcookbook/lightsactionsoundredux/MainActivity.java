package com.packtpub.androidcookbook.lightsactionsoundredux;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v4.app.NotificationCompat;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void clickLightsActionSound(View view) {

        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        Uri notificationSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("LightsActionSoundRedux")
                .setContentText("Lights, Action & Sound")
                .setSound(notificationSoundUri)
                .setLights(Color.BLUE, 500, 500)
                .setVibrate(new long[]{250,500,250,500,250,500});
//        Intent activityIntent = new Intent(this,MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, activityIntent, 0);
//        notificationBuilder.addAction(android.R.drawable.ic_dialog_email, "Email", pendingIntent);
        notificationManager.notify(0, notificationBuilder.build());


/*
        NotificationCompat.Builder notificationBuilderInboxStyle = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher);
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle("InboxStyle - Big Content Title")
                .addLine("Line 1")
                .addLine("Line 2");
        notificationBuilderInboxStyle.setStyle(inboxStyle);
        notificationManager.notify(0, notificationBuilderInboxStyle.build());
*/

/*
        NotificationCompat.Builder notificationBuilderBigPictureStyle = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("LightsActionSoundRedux")
                .setContentText("BigPictureStyle");
        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
        bigPictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        notificationBuilderBigPictureStyle.setStyle(bigPictureStyle);
        notificationManager.notify(0, notificationBuilderBigPictureStyle.build());
*/

/*
        NotificationCompat.Builder notificationBuilderBigTextStyle = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("LightsActionSoundRedux");
        NotificationCompat.BigTextStyle BigTextStyle = new NotificationCompat.BigTextStyle();
        BigTextStyle.bigText("This is an example of the BigTextStyle expanded notification.");
        notificationBuilderBigTextStyle.setStyle(BigTextStyle);
        notificationManager.notify(0, notificationBuilderBigTextStyle.build());
*/

    }

}
