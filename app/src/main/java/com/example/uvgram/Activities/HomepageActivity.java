package com.example.uvgram.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.uvgram.Adapters.HomeViewPagerAdapter;
import com.example.uvgram.Models.Message;
import com.example.uvgram.R;
import com.google.android.material.tabs.TabLayout;

public class HomepageActivity extends AppCompatActivity {

    ViewPager2 homepageViewPager;
    TabLayout homepageTabLayout;
    Message signedInUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        homepageTabLayout = findViewById(R.id.homepageTabLayout);
        homepageViewPager = findViewById(R.id.homepageviewPager);
        HomeViewPagerAdapter pagerAdapter = new HomeViewPagerAdapter(this);
        homepageViewPager.setAdapter(pagerAdapter);

        signedInUser = (Message) getIntent().getSerializableExtra("SIGNED_IN_USER");


        homepageTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                homepageViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        homepageViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                homepageTabLayout.getTabAt(position).select();
            }
        });
    }




}