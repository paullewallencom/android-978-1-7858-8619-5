package com.packtpub.androidcookbook.slideshow;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentManager;



public class MainActivity extends FragmentActivity {

    private final int PAGE_COUNT=4;
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;

    private class SlideAdapter extends FragmentStatePagerAdapter {
        public SlideAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            SlideFragment slideFragment = new SlideFragment();
            switch (position) {
                case 0:
                    slideFragment.setImage(R.drawable.slide_0);
                    break;
                case 1:
                    slideFragment.setImage(R.drawable.slide_1);
                    break;
                case 2:
                    slideFragment.setImage(R.drawable.slide_2);
                    break;
                case 3:
                    slideFragment.setImage(R.drawable.slide_3);
                    break;
            }
            return slideFragment;
        }
        @Override
        public int getCount() {
            return PAGE_COUNT;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mPagerAdapter = new SlideAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (mViewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
        }
    }

}
