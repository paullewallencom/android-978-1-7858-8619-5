package com.packtpub.androidcookbook.alertdialog;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void confirmDelete(View view) {

//        String[] countries = new String[]{"China", "France", "Germany", "India", "Russia", "United Kingdom", "United States"};
//        ListAdapter countryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete")
                //.setIcon(R.mipmap.ic_launcher)
                .setMessage("Are you sure you?")
//                .setAdapter(countryAdapter, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this, countries[which] + " clicked", Toast.LENGTH_SHORT).show();
//                    }
//                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(MainActivity.this, "OK Pressed", Toast.LENGTH_SHORT).show();
                    }})
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(MainActivity.this, "Cancel Pressed", Toast.LENGTH_SHORT).show();
                    }});
        builder.create().show();
    }
}
