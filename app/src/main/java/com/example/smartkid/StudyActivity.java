package com.example.smartkid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.ImageView;

import Fragments.TopicLessonFrag;

public class StudyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        ImageView imgStudy = findViewById(R.id.img_study);
        imgStudy.setImageResource(R.drawable.img_study);
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_study,  new TopicLessonFrag());
        ft.commit();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ImageView imgStudy = findViewById(R.id.img_study);
        imgStudy.setImageResource(R.drawable.img_study);
    }
}