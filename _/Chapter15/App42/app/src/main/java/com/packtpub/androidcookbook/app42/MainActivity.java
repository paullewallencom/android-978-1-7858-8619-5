package com.packtpub.androidcookbook.app42;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.shephertz.app42.paas.sdk.android.App42API;
import com.shephertz.app42.paas.sdk.android.App42CallBack;
import com.shephertz.app42.paas.sdk.android.user.User;
import com.shephertz.app42.paas.sdk.android.user.UserService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App42API.initialize(this, "YOUR_API_KEY", "YOUR_SECRET_KEY");

        UserService userService = App42API.buildUserService();
        userService.createUser("userName", "password", "email", new App42CallBack() {
            public void onSuccess(Object response) {
                User user = (User)response;
                Log.i("UserService","userName is " + user.getUserName());
                Log.i("UserService", "emailId is " + user.getEmail());
            }
            public void onException(Exception ex) {
                System.out.println("Exception Message"+ex.getMessage());
            }
        });
    }
}
