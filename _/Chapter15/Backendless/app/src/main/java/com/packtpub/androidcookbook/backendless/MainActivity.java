package com.packtpub.androidcookbook.backendless;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.backendless.Backendless;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String appVersion = "v1";
        Backendless.initApp( this, "YOUR_APP_ID", "YOUR_SECRET_KEY", appVersion );

/*        BackendlessUser user = new BackendlessUser();
        user.setEmail( "<user@email>");
        user.setPassword( "<password>" );
        Backendless.UserService.register(user, new BackendlessCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser backendlessUser) {
                Log.i("Registration", backendlessUser.getEmail() + " successfully registered");
            }
        } );
*/
    }
}
