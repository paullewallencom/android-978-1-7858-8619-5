package com.packtpub.androidcookbook.runtimewidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AnalogClock;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextClock;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout layout = (RelativeLayout)findViewById(R.id.layout);
        DatePicker datePicker = new DatePicker(this);
        layout.addView(datePicker);
    }
}
