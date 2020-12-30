package com.packtpub.androidcookbook.rotatewithuserinput;


import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView mGLSurfaceView;

    private float mCenterX=0;
    private float mCenterY=0;


    class CustomGLSurfaceView extends GLSurfaceView {

        private final GLRenderer mGLRenderer;

        public CustomGLSurfaceView(Context context){
            super(context);
            setEGLContextClientVersion(2);
            mGLRenderer = new GLRenderer();
            setRenderer(mGLRenderer);
            setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        }

        @Override
        public boolean onTouchEvent(MotionEvent e) {
            float x = e.getX();
            float y = e.getY();
            switch (e.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    double angleRadians = Math.atan2(y-mCenterY,x-mCenterX);
                    mGLRenderer.setAngle((float)Math.toDegrees(-angleRadians));
                    requestRender();
            }
            return true;
        }

    }

    class GLRenderer implements GLSurfaceView.Renderer {

        private Triangle mTriangle;

        private final float[] mMVPMatrix = new float[16];
        private final float[] mProjectionMatrix = new float[16];
        private final float[] mViewMatrix = new float[16];

        private float[] mRotationMatrix = new float[16];
        public volatile float mAngle;
        public void setAngle(float angle) {
            mAngle = angle;
        }

        public void onSurfaceCreated(GL10 unused, EGLConfig config) {
            mTriangle = new Triangle();
            GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        }
        public void onDrawFrame(GL10 unused) {
            Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
            Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

            float[] tempMatrix = new float[16];
            Matrix.setRotateM(mRotationMatrix, 0, mAngle, 0, 0, -1.0f);
            Matrix.multiplyMM(tempMatrix, 0, mMVPMatrix, 0, mRotationMatrix, 0);
            mTriangle.draw(tempMatrix);
            //mTriangle.draw(mMVPMatrix);
        }
        public void onSurfaceChanged(GL10 unused, int width, int height) {
            mCenterX=width/2;
            mCenterY=height/2;

            GLES20.glViewport(0, 0, width, height);
            float ratio = (float) width / height;
            Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGLSurfaceView = new CustomGLSurfaceView(this);
        setContentView(mGLSurfaceView);
    }
}