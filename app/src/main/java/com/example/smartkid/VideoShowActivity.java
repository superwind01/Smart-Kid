package com.example.smartkid;

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

        //GET INTENT
        Bundle bundle = getIntent().getExtras();

        VideoView videoView = findViewById(R.id.video_allSong);
        TextView textView = findViewById(R.id.txt_nameSong);
        //DECLARE MEDIA CONTROLLER TO CONTROL VIDEO
        MediaController mediaController = new MediaController(VideoShowActivity.this);

        textView.setText(bundle.get("name").toString());
        //SET VIDEO SOURCE
        Uri uri = Uri.parse(bundle.get("path").toString());
        videoView.setVideoURI(uri);
        //SET MEDIA CONTROLLER
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
    }
}