package com.packtpub.androidcookbook.cancelvolleyrequest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRequestQueue = Volley.newRequestQueue(this);
    }

    public void sendRequest(View view) {
        final TextView textView = (TextView)findViewById(R.id.textView);

        String url ="https://www.packtpub.com/";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textView.setText(response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("onErrorResponse(): "+ error.getMessage());
            }
        });
        stringRequest.setTag(this);
        mRequestQueue.add(stringRequest);
        finish();
    }

    public void close(View view){
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mRequestQueue.cancelAll(this);
    }
}
