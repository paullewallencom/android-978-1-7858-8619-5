package com.packtpub.androidcookbook.cameraapi;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.TextureView;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity
        implements TextureView.SurfaceTextureListener {

    private Camera mCamera;
    private TextureView mTextureView;

    Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
        try {
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(System.currentTimeMillis());
                String fileName = "PHOTO_" + timeStamp + ".jpg";
                File pictureFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),fileName);

                FileOutputStream fileOutputStream =new FileOutputStream(pictureFile.getPath());
                fileOutputStream.write(data);
                fileOutputStream.close();
                Toast.makeText(MainActivity.this, "Picture Taken", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextureView = (TextureView)findViewById(R.id.textureView);
        mTextureView.setSurfaceTextureListener(this);
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        mCamera = Camera.open();
        if (mCamera!=null) {
            try {
                mCamera.setPreviewTexture(surface);
                mCamera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        if (mCamera!=null) {
            mCamera.stopPreview();
            mCamera.release();
        }
        return true;
    }
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        // Unused
    }
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        // Unused
    }

    public void takePicture(View view) {
        if (mCamera!=null) {
            mCamera.takePicture(null, null, pictureCallback);
        }
    }
}
