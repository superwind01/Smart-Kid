package com.example.easyclass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import Adapter.VPAdapter;

public class VocabularyActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);
        addControls();
    }

    private void addControls() {
        tabLayout = findViewById(R.id.tab_vocabulary);
        viewPager = findViewById(R.id.vp_vocabulary);

        viewPager.setAdapter(new VPAdapter(this));

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