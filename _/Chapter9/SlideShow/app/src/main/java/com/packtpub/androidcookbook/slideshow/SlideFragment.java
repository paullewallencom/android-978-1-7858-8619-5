package com.packtpub.androidcookbook.slideshow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class SlideFragment extends Fragment {

    private int mImageResourceID;

    public SlideFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_slide, container, false);
        ImageView imageView = (ImageView)rootView.findViewById(R.id.imageView);
        imageView.setImageResource(mImageResourceID);
        return rootView;
    }

    public void setImage(int resourceID) {
        mImageResourceID=resourceID;
    }
}

