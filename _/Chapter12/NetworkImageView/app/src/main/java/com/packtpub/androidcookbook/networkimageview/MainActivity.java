package com.packtpub.androidcookbook.networkimageview;

import android.graphics.Bitmap;

import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetworkImageView networkImageView = (NetworkImageView)findViewById(R.id.networkImageView);
        String url="http://www.android.com/static/img/logos-2x/android-wordmark-8EC047.png";
        RequestQueue queue = Volley.newRequestQueue(this);
        ImageLoader imageLoader = new ImageLoader(queue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
        networkImageView.setImageUrl(url,imageLoader);
    }
}
