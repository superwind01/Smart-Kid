package com.example.easyclass;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class VideoShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_show);

        Bundle bundle = getIntent().getExtras();

        VideoView videoView = findViewById(R.id.video_allSong);
        TextView textView = findViewById(R.id.txt_nameSong);
        MediaController mediaController = new MediaController(VideoShowActivity.this);

        textView.setText(bundle.get("name").toString());
        Uri uri = Uri.parse(bundle.get("path").toString());
        videoView.setVideoURI(uri);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
    }
}