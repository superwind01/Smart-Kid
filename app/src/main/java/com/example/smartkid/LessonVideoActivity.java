package com.example.smartkid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleObserver;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import API.ModelCommon;
import Adapter.LessonVideoActivityAdapter;
import VolleyService.*;

public class LessonVideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_video);
        TextView txtTopicStudyLesson = findViewById(R.id.txt_topicStudyLesson);

        RecyclerView recyclerVocabularyByLesson = findViewById(R.id.recycler_vocabulary_by_lesson);

        YouTubePlayerView youTubePlayerView = findViewById(R.id.videoView_youtube);
        getLifecycle().addObserver(youTubePlayerView);


        Bundle  bundle = getIntent().getExtras();
        String idtopic = (String) bundle.get("idtopicKey");
        String nametopic = (String) bundle.get("nametopicKey");
        String idlesson = (String) bundle.get("idlessonKey");

        txtTopicStudyLesson.setText(nametopic + " - " +"Lesson " + idlesson);

        VolleyService.getRequest(this, "VocabularyByTopicLesson/GetLinkByTopicLesson/" + idtopic + "/" + idlesson, new VolleyResponseListener() {
            @Override
            public void onError(String message) {

            }

            @Override
            public void onResponse(ModelCommon response) {
                youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        super.onReady(youTubePlayer);
                        String videoID = response.getVocabularyByTopicLessons().get(0).getLink();
                        youTubePlayer.loadVideo(videoID,0);
                    }
                });
            }
        });

        VolleyService.getRequest(this, "Vocalbulary/GetByIDTopicAndIDLesson/" + idtopic + "/" + idlesson, new VolleyResponseListener() {
            @Override
            public void onError(String message) {

            }

            @Override
            public void onResponse(ModelCommon response) {

                recyclerVocabularyByLesson.setLayoutManager(new LinearLayoutManager(LessonVideoActivity.this,LinearLayoutManager.VERTICAL,false));
                recyclerVocabularyByLesson.setAdapter(new LessonVideoActivityAdapter(LessonVideoActivity.this,response.getVocabularies()));

            }
        });

    }
}