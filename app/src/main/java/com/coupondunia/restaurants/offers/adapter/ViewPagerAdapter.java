package com.coupondunia.restaurants.offers.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.coupondunia.restaurants.offers.fragment.NearByRestaurantFragment;
import com.coupondunia.restaurants.offers.fragment.SampleFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private String titles[] ;

    public ViewPagerAdapter(FragmentManager fm, String[] titles2) {
        super(fm);
        titles=titles2;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            // Open FragmentTab1.java
            case 0:
                return SampleFragment.newInstance(position);
            case 1:
                return NearByRestaurantFragment.newInstance(position);
            case 2:
                return SampleFragment.newInstance(position);

        }
        return null;
    }

    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return titles.length;
    }

}