package com.example.easyclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import API.ModelCommon;
import API.Vocabulary;
import Adapter.ListViewAdapter;
import VolleyService.*;

public class VocabularyLessonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_lesson);

        Bundle bundle = getIntent().getExtras();

        final int[] position = {(int) bundle.get("position")};

        ImageButton btnNext = findViewById(R.id.btn_next);
        ImageButton btnPrevious = findViewById(R.id.btn_previous);

        VolleyService.getRequest(this,"Vocalbulary/GetAll", new VolleyResponseListener() {
            @Override
            public void onError(String message) {
            }
            @Override
            public void onResponse(ModelCommon response) {
                if(response!=null)
                {
                    final ArrayList<Vocabulary> vocabularies = response.getVocabularies();;
                    Collections.sort(vocabularies, new Comparator<Vocabulary>() {
                        @Override
                        public int compare(Vocabulary o1, Vocabulary o2) {
                            return o1.getName().toUpperCase().compareTo(o2.getName().toUpperCase());
                        }
                    });
                    addData(vocabularies,position[0]);
                    btnPrevious.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (position[0]>0) {
                                position[0] = position[0] - 1;
                                addData(vocabularies,position[0]);
                            }
                        }
                    });

                    btnNext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (position[0] < vocabularies.size())
                                position[0] = position[0] + 1;
                                addData(vocabularies,position[0]);
                        }
                    });
                }
            }
        });

    }

    private void addData(ArrayList<Vocabulary> vocabularies,int position) {

        VideoView videoView = findViewById(R.id.videoView);
        String videoPath = "http://resource.bkt.net.vn/AudioMP3/";
        MediaController mediaController = new MediaController(this);

        TextView txtPronounce = findViewById(R.id.txt_pronounce);
        TextView txtTranslate = findViewById(R.id.txt_translate);
        TextView txtName = findViewById(R.id.txt_nameVocabulary);

        txtPronounce.setText(vocabularies.get(position).getPronounce());
        txtTranslate.setText(vocabularies.get(position).getVnName());
        txtName.setText(vocabularies.get(position).getName().toUpperCase());

        Uri uri = Uri.parse(videoPath+vocabularies.get(position).getName()+".mp3");
        videoView.setVideoURI(uri);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
    }

}