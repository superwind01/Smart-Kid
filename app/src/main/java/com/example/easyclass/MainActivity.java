package com.example.easyclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import Adapter.ViewPagerMainActivityAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 mviewPager;
    private BottomNavigationView mbottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

    mviewPager = findViewById(R.id.vp_mainActivity);
    mbottomNavigationView = findViewById(R.id.bottomNavigationView);

    mviewPager.setAdapter(new ViewPagerMainActivityAdapter(this));
    mviewPager.setUserInputEnabled(false);
        //Tao su kien khi chon vao icon o bottom navigation thi se gan gia tri cho mViewPager
        mbottomNavigationView.setOnItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();
            if(id == R.id.nav_home){
                mviewPager.setCurrentItem(0);
            }
            else if(id == R.id.nav_personal){
                mviewPager.setCurrentItem(1);
            }
            else if(id == R.id.nav_store){
                mviewPager.setCurrentItem(2);
            }
            else if(id == R.id.nav_chat){
                mviewPager.setCurrentItem(3);
            }
            else
                mviewPager.setCurrentItem(4);
            return true;
        });

        //Tao su kien khi Chuyen sang trang khac thi Checked item o bottom navigation
        mviewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        mbottomNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
                        break;

                    case 1:
                        mbottomNavigationView.getMenu().findItem(R.id.nav_personal).setChecked(true);
                        break;

                    case 2:
                        mbottomNavigationView.getMenu().findItem(R.id.nav_store).setChecked(true);
                        break;
                    case 3:
                        mbottomNavigationView.getMenu().findItem(R.id.nav_chat).setChecked(true);
                        break;
                    case 4:
                        mbottomNavigationView.getMenu().findItem(R.id.nav_notification).setChecked(true);
                        break;
                }
            }
        });

    }


}