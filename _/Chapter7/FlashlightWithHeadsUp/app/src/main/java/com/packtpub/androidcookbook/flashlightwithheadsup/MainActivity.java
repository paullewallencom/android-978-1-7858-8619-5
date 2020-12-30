package com.packtpub.androidcookbook.flashlightwithheadsup;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

import static android.app.Notification.*;

public class MainActivity extends AppCompatActivity {

    private static final String ACTION_STOP="STOP";
    private CameraManager mCameraManager;
    private String mCameraId=null;
    private ToggleButton mButtonLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonLight = (ToggleButton)findViewById(R.id.buttonLight);

        mCameraManager = (CameraManager) this.getSystemService(Context.CAMERA_SERVICE);
        mCameraId = getCameraId();
        if (mCameraId==null) {
            mButtonLight.setEnabled(false);
        } else {
            mButtonLight.setEnabled(true);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (ACTION_STOP.equals(intent.getAction())) {
            setFlashlight(false);
        }
    }

    private String getCameraId()  {
        try {
            String[] ids = mCameraManager.getCameraIdList();
            for (String id : ids) {
                CameraCharacteristics c = mCameraManager.getCameraCharacteristics(id);
                Boolean flashAvailable = c.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                Integer facingDirection = c.get(CameraCharacteristics.LENS_FACING);
                if (flashAvailable != null && flashAvailable
                        && facingDirection != null
                        && facingDirection == CameraCharacteristics.LENS_FACING_BACK) {
                    return id;
                }
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void clickLight(View view) {
        setFlashlight(mButtonLight.isChecked());
        if (mButtonLight.isChecked()) {
            showNotification();
        }
    }

    private void setFlashlight(boolean enabled) {
        mButtonLight.setChecked(enabled);
        try {
            mCameraManager.setTorchMode(mCameraId, enabled);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void showNotification() {
        Intent activityIntent = new Intent(this,MainActivity.class);
        activityIntent.setAction(ACTION_STOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,activityIntent,0);
        final Builder notificationBuilder = new Builder(this)
                .setContentTitle("Flashlight")
                .setContentText("Press to turn off the flashlight")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent)
                .setVibrate(new long[]{DEFAULT_VIBRATE})
                .setPriority(PRIORITY_MAX);
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }

}
