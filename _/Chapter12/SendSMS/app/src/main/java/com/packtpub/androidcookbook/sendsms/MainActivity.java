package com.packtpub.androidcookbook.sendsms;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final int SEND_SMS_PERMISSION_REQUEST_CODE=1;
    Button mButtonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonSend = (Button)findViewById(R.id.buttonSend);
        mButtonSend.setEnabled(false);

        if (checkPermission(Manifest.permission.SEND_SMS)) {
            mButtonSend.setEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    SEND_SMS_PERMISSION_REQUEST_CODE);
        }
    }

    private boolean checkPermission(String permission) {
        int permissionCheck = ContextCompat.checkSelfPermission(
                this,permission);
        return (permissionCheck == PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case SEND_SMS_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mButtonSend.setEnabled(true);
                }
                return;
            }
        }
    }

    public void send(View view) {
        String phoneNumber = ((EditText)findViewById(R.id.editTextNumber)).getText().toString();
        String msg = ((EditText)findViewById(R.id.editTextMsg)).getText().toString();

        if (phoneNumber==null || phoneNumber.length()==0 || msg==null || msg.length()==0 ) { return;}

        if (checkPermission(Manifest.permission.SEND_SMS)) {
            SmsManager smsManager = SmsManager.getDefault();
            if (msg.length()>160) {
                ArrayList<String> messages=smsManager.divideMessage(msg);
                smsManager.sendMultipartTextMessage(phoneNumber, null, messages, null, null);
            } else {
                smsManager.sendTextMessage(phoneNumber, null, msg, null, null);
            }
        } else {
            Toast.makeText(MainActivity.this, "No Permission", Toast.LENGTH_SHORT).show();
        }
    }

}
