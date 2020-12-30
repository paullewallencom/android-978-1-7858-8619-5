package com.packtpub.androidcookbook.buddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.buddy.sdk.Buddy;
import com.buddy.sdk.BuddyCallback;
import com.buddy.sdk.BuddyResult;
import com.buddy.sdk.models.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Buddy.init(this, "appId", "appKey");

        Buddy.createUser("someUser", "somePassword", null, null, null, null, null, null, new BuddyCallback<User>(User.class) {
            @Override
            public void completed(BuddyResult<User> result) {
                if (result.getIsSuccess()) {
                    Log.w("Buddy", "User created: " + result.getResult().userName);
                }
            }
        });

    }
}
