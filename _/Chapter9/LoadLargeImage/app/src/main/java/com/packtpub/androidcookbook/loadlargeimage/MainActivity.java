package com.packtpub.androidcookbook.loadlargeimage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView)findViewById(R.id.imageViewThumbnail);
        imageView.setImageBitmap(loadSampledResource(R.drawable.image_large, 100,100));
    }

    public Bitmap loadSampledResource(int imageID, int targetHeight, int targetWidth) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), imageID, options);
        final int originalHeight = options.outHeight;
        final int originalWidth = options.outWidth;
        int inSampleSize = 1;
        while ((originalHeight / (inSampleSize *2)) > targetHeight &&
                (originalWidth / (inSampleSize *2)) > targetWidth) {
            inSampleSize *= 2;
        }
        options.inSampleSize =inSampleSize;
        options.inJustDecodeBounds = false;
        return (BitmapFactory.decodeResource(getResources(), imageID, options));
    }

}
