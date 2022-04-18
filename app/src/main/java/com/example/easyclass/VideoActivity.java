package com.example.easyclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import Adapter.VPVideoAdapter;

public class VideoActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        addControls();
    }

    private void addControls() {
        tabLayout = findViewById(R.id.tab_video);
        viewPager = findViewById(R.id.vp_video);

        viewPager.setAdapter(new VPVideoAdapter(this));
        viewPager.setUserInputEnabled(false);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Famous");
                    break;
                case 1:
                    tab.setText("Categories");
            }
        }).attach();

    }
}