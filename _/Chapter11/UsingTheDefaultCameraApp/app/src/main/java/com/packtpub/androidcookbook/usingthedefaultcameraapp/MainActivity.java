package com.packtpub.androidcookbook.usingthedefaultcameraapp;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import java.io.File;
import java.text.SimpleDateFormat;


public class MainActivity extends AppCompatActivity {

    final int PHOTO_RESULT=1;
    private Uri mLastPhotoURI=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private Uri createFileURI() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(System.currentTimeMillis());
        String fileName = "PHOTO_" + timeStamp + ".jpg";
        return Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),fileName));
    }

    public void takePicture(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            mLastPhotoURI = createFileURI();
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mLastPhotoURI);
            startActivityForResult(takePictureIntent, PHOTO_RESULT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_RESULT && resultCode == RESULT_OK ) {
            ImageView imageView=(ImageView)findViewById(R.id.imageView);
            imageView.setImageBitmap(BitmapFactory.decodeFile(mLastPhotoURI.getPath()));

/*
            if (data != null) {
                //Thumbnail image returned in Intent
                imageView.setImageBitmap((Bitmap) data.getExtras().get("data"));

                //Full Size Image
                try {
                    imageView.setImageBitmap(
                            MediaStore.Images.Media.getBitmap(getContentResolver(),
                            Uri.parse(data.toUri(Intent.URI_ALLOW_UNSAFE))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
*/


        }
    }
}
