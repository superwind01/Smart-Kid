package com.example.smartkid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import API.ModelCommon;
import Adapter.SongByTopicFragAdapter;
import VolleyService.*;

public class SongDetailActivity extends AppCompatActivity {
    String idTopic, nameTopic;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);

        Bundle bundle = getIntent().getExtras();
        idTopic = (String) bundle.get("idtopicKey");
        nameTopic = (String) bundle.get("nametopicKey");
        position = (int) bundle.get("positionKey");

        LinearLayout linearLayout_playList = findViewById(R.id.linearLayout_playList);
        LinearLayout  linearLayout_songDetail = findViewById(R.id.linearLayout_songDetail);
        TextView txtTitleSongByTopicFrag = findViewById(R.id.txt_title_SongByTopicFrag);
        TextView txtNameSongByTopic= findViewById(R.id.txt_nameSong_by_Topic);
        VideoView videoView = findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(SongDetailActivity.this);

        RecyclerView recyclerSong = findViewById(R.id.recycler_song);
        recyclerSong.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        //DECLARE SHARE PREFERENCES
        SharedPreferences sharedPreferences = this.getSharedPreferences("shareClass", Context.MODE_PRIVATE);
        //GET SHARE PREFERENCES FROM CLASS FRAGMENT
        String nameBook = sharedPreferences.getString("namebookKey","");
        txtTitleSongByTopicFrag.setText(nameBook + " > " + nameTopic );

        VolleyService.getRequest(this, "Song/GetByIdTopic/" + idTopic, new VolleyResponseListener() {
            @Override
            public void onError(String message) {
            }

            @Override
            public void onResponse(ModelCommon response) {
                txtNameSongByTopic.setText(response.getSongs().get(position).getName());

                Uri uri = Uri.parse("http://resource.bkt.net.vn/AudioMP4/" + response.getSongs().get(position).getName() + ".mp4");
                videoView.setVideoURI(uri);
                videoView.setMediaController(mediaController);
                mediaController.setAnchorView(videoView);

                SongByTopicFragAdapter mAdaper = new SongByTopicFragAdapter(SongDetailActivity.this,response.getSongs());
                recyclerSong.setAdapter(mAdaper);
                mAdaper.setOnItemClickListener(new SongByTopicFragAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        txtNameSongByTopic.setText(response.getSongs().get(position).getName());

                        Uri uri = Uri.parse("http://resource.bkt.net.vn/AudioMP4/" + response.getSongs().get(position).getName() + ".mp4");
                        videoView.setVideoURI(uri);
                        videoView.setMediaController(mediaController);
                        mediaController.setAnchorView(videoView);
                    }
                });
            }
        });

        linearLayout_playList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recyclerSong.getVisibility() == View.GONE)
                {
                    recyclerSong.setVisibility(View.VISIBLE);
                    linearLayout_songDetail.setVisibility(View.GONE);
                }
                else
                {
                    recyclerSong.setVisibility(View.GONE);
                    linearLayout_songDetail.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}