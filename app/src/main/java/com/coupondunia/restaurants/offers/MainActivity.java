package com.coupondunia.restaurants.offers;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.coupondunia.restaurants.R;
import com.coupondunia.restaurants.offers.adapter.ViewPagerAdapter;
import com.coupondunia.restaurants.offers.views.SlidingTabLayout;


public class MainActivity extends ActionBarActivity {


    ViewPager pager;
    private String titles[] = new String[]{"IN YOUR CITY", "NEAR BY", "BEST OFFERS"};

    SlidingTabLayout slidingTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Restaurant Offers");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        pager = (ViewPager) findViewById(R.id.viewpager);
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        pager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), titles));
        pager.setCurrentItem(1);
        slidingTabLayout.setViewPager(pager);
        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return Color.BLUE;
            }
        });
    }


}
