package com.example.easyclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import java.util.zip.Inflater;

import Adapter.GridViewAdapter;
import Fragments.ClassFrag;
import Fragments.LearnVocabularyFrag;

public class MainActivity extends AppCompatActivity {
    String[] nameLogo = {"Rank","Mission","Game","Route","Video","Activated","Connect"};
    int[] Logo ={R.drawable.rank ,
            R.drawable.mission,
            R.drawable.game,
            R.drawable.route,
            R.drawable.youtube,
            R.drawable.activated,
            R.drawable.connect};
    String[] nameFormal = {"Vocabulary","Conversation","Gramma","Listen"};
    int[] formalLogo ={R.drawable.vocabulary,
            R.drawable.conversation,
            R.drawable.gramma,
            R.drawable.listen};
    String[] nameSupplement = {"Study","Excercise","Examination"};
    int[] supplementLogo ={R.drawable.study,
            R.drawable.excercise,
            R.drawable.examination};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();

        ExpandableHeightGridView gridItem = (ExpandableHeightGridView) findViewById(R.id.grid_item);
        gridItem.setExpanded(true);
        ExpandableHeightGridView gridFormalStudy = (ExpandableHeightGridView) findViewById(R.id.grid_formalStudy);
        gridFormalStudy.setExpanded(true);
        ExpandableHeightGridView gridSupplementStudy = (ExpandableHeightGridView) findViewById(R.id.grid_SupplementStudy);
        gridSupplementStudy.setExpanded(true);

        gridItem.setAdapter(new GridViewAdapter(this, nameLogo, Logo, 0));
        gridFormalStudy.setAdapter(new GridViewAdapter(this, nameFormal, formalLogo, 1));
        gridSupplementStudy.setAdapter(new GridViewAdapter(this, nameSupplement, supplementLogo, 2));

        gridItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 4)
                {
                    Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                    startActivity(intent);
                }
            }
        });

        gridFormalStudy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0)
                {
                    Intent intent = new Intent(MainActivity.this, VocabularyActivity.class);
                    startActivity(intent);
                }
                else if(i == 1)
                {
                    Intent intent = new Intent(MainActivity.this, VocabularyActivity.class);
                    startActivity(intent);
                }
                else if(i == 2)
                {
                    Intent intent = new Intent(MainActivity.this, VocabularyActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(MainActivity.this, VocabularyActivity.class);
                    startActivity(intent);
                }
            }
        });

        gridItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 4)
                {
                    Intent intent = new Intent(MainActivity.this,VideoActivity.class);
                    startActivity(intent);
                }
            }
        });

        Button btnClass = findViewById(R.id.btn_chose_class);
        if(bundle != null)
        {
            btnClass.setText((String) bundle.get("class"));
        }
        else {
            btnClass.setText("Class");
        }
        btnClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = MainActivity.this.getSupportFragmentManager().beginTransaction();

                ft.add(R.id.frame_class,  new ClassFrag());
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }

    //    @Override
//    public void onBackPressed() {
//        if (getFragmentManager().getBackStackEntryCount() == 0) {
//            this.finish();
//        } else {
//            getFragmentManager().popBackStack();
//        }
//    }

}