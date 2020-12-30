package com.packtpub.androidcookbook.homescreenshortcut;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createShortcut(View view) {
        Intent shortcutIntent = new Intent(this, MainActivity.class);
        shortcutIntent.setAction(Intent.ACTION_MAIN);
        Intent installIntent = new Intent();
        installIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        installIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, getString(R.string.app_name));
        installIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(this, R.mipmap.ic_launcher));
        installIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        sendBroadcast(installIntent);
    }
}
