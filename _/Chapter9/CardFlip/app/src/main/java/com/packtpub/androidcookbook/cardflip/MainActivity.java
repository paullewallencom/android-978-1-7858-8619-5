package com.packtpub.androidcookbook.cardflip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    boolean mShowingBack=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout frameLayout = (FrameLayout)findViewById(R.id.frameLayout);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard();
            }
        });

        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.frameLayout, new CardFrontFragment())
                    .commit();
        }
    }

    private void flipCard() {
        if (mShowingBack) {
            mShowingBack = false;
            getFragmentManager().popBackStack();
        } else {
            mShowingBack = true;
            getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_enter, R.animator.card_flip_right_exit,
                        R.animator.card_flip_left_enter, R.animator.card_flip_left_exit)
                .replace(R.id.frameLayout, new CardBackFragment())
                .addToBackStack(null)
                .commit();
        }
    }
}
