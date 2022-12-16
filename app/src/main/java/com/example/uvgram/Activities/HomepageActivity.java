package com.example.uvgram.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.uvgram.Adapters.HomeViewPagerAdapter;
import com.example.uvgram.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;

public class HomepageActivity extends AppCompatActivity {

    ViewPager2 homepageViewPager;
    TabLayout homepageTabLayout;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        homepageTabLayout = findViewById(R.id.homepageTabLayout);
        homepageViewPager = findViewById(R.id.homepageviewPager);
        toolbar = findViewById(R.id.topAppBar);

        HomeViewPagerAdapter pagerAdapter = new HomeViewPagerAdapter(this);
        homepageViewPager.setAdapter(pagerAdapter);

        toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.followRequestsButton:
                    Intent myIntent = new Intent(this, FollowRequestsActivity.class);
                    startActivity(myIntent);
                    return true;
                case R.id.createPostButton:
                    Intent requestsIntent = new Intent(this, CreatePostActivity.class);
                    startActivity(requestsIntent);
                    return true;
            }
            return false;
        });

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

    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.blocked_users_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.blockedUsersOption:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}