package com.example.smartkid;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import API.ModelCommon;
import API.Speak;
import API.Vocabulary;
import VolleyService.*;

public class VocabularyLessonActivity extends AppCompatActivity {
    String url;
    String idtopic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_lesson);

        //GET INTENT
        Bundle bundle = getIntent().getExtras();

        idtopic = (String) bundle.get("idtopicKey");

        if(idtopic != null)
        {
            url = "Vocalbulary/GetByIDTopic/" + bundle.get("idtopicKey");
        }
        else
        {
            url = "Vocalbulary/GetAll";
        }
        //GET POSITION PICKED ITEM FROM INTENT
        //POSITION GET FROM LIST VOCABULARY (VOCABULARY FRAG)
        final int[] position = {(int) bundle.get("position")};

        ImageButton btnNext = findViewById(R.id.btn_next);
        ImageButton btnPrevious = findViewById(R.id.btn_previous);

        VolleyService.getRequest(this,url, new VolleyResponseListener() {
            @Override
            public void onError(String message) {
            }
            @Override
            public void onResponse(ModelCommon response) {
                if(response!=null)
                {
                    final ArrayList<Vocabulary> vocabularies = response.getVocabularies();


                    //SORT ARRAY LIST VOCABULARIES AFTER RESPONSE
                    Collections.sort(vocabularies, new Comparator<Vocabulary>() {
                        @Override
                        public int compare(Vocabulary o1, Vocabulary o2) {
                            return o1.getName().toUpperCase().compareTo(o2.getName().toUpperCase());
                        }
                    });

                    //CREATE NEW LIST NAME VOCABULARY WITH HEADER A-Z
                    ArrayList<Vocabulary> listNameVocabularies = new ArrayList<>();

                    //POSITION MUST BE 0,
                    //BUT API HAVE AN NULL OBJECT IN POSITION 0 SO IT'S START FROM 1
                    listNameVocabularies.add(new Vocabulary(0,vocabularies.get(0).getName().substring(0,1).toUpperCase(),null,null,null,false));
                    listNameVocabularies.add(new Vocabulary(vocabularies.get(0).getIdVocabulary(),vocabularies.get(0).getName().toUpperCase(),vocabularies.get(0).getPronounce(),vocabularies.get(0).getDescription(),vocabularies.get(0).getVnName(),vocabularies.get(0).isStatus()));
                    for(int i =1; i <vocabularies.size(); i++)
                    {
                        String a = vocabularies.get(i).getName().toUpperCase().substring(0,1);
                        String b = vocabularies.get(i-1).getName().toUpperCase().substring(0,1);
                        if( !a.equals(b) )
                        {
                            listNameVocabularies.add(new Vocabulary(0,vocabularies.get(i).getName().substring(0,1).toUpperCase(),null,null,null,false));
                        }
                        listNameVocabularies.add(new Vocabulary(vocabularies.get(i).getIdVocabulary(),vocabularies.get(i).getName().toUpperCase(),vocabularies.get(i).getPronounce(),vocabularies.get(i).getDescription(),vocabularies.get(i).getVnName(),vocabularies.get(i).isStatus()));
                    }

                    addData(listNameVocabularies,position[0]);

                    //CHECK POSITION OF ITEM LIST
                    //IF POSITION = 0, BUTTON PREVIOUS DISABLE
                    //IF POSITION = END OF LIST SIZE, BUTTON NEXT DISABLE
                    if(position[0] == 0)
                    {
                        btnPrevious.setEnabled(false);
                    }
                    if(position[0] == listNameVocabularies.size()-1)
                    {
                        btnNext.setEnabled(false);
                    }

                    //CREATE EVEN FOR BUTTON PREVIOUS
                    btnPrevious.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (position[0]>0) {
                                position[0] = position[0] - 1;
                                addData(listNameVocabularies,position[0]);

                                //SET ENABLE FOR BUTTON NEXT = TRUE
                                // POSITION = 0, BUTTON PREVIOUS SHOULD BE DISABLED
                                btnNext.setEnabled(true);
                                if(position[0] == 0)
                                {
                                    btnPrevious.setEnabled(false);
                                }
                            }
                        }
                    });

                    //CREATE EVEN FOR BUTTON NEXT
                    btnNext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (position[0] < listNameVocabularies.size()-1) {
                                position[0] = position[0] + 1;
                                addData(listNameVocabularies, position[0]);

                                //SET ENABLE FOR BUTTON PREVIOUS = TRUE
                                // POSITION = 0, BUTTON NEXT SHOULD BE DISABLED
                                btnPrevious.setEnabled(true);
                                if(position[0] == listNameVocabularies.size()-1)
                                {
                                    btnNext.setEnabled(false);
                                }
                            }
                        }
                    });
                }
            }
        });

    }

    private void addData(ArrayList<Vocabulary> vocabularies,int position) {

        if (vocabularies.get(position).getIdVocabulary() != 0) {
            VideoView videoView = findViewById(R.id.videoView);
            String videoPath = "http://resource.bkt.net.vn/AudioMP3/";
            MediaController mediaController = new MediaController(this);

            TextView txtPronounce = findViewById(R.id.txt_pronounce);
            TextView txtTranslate = findViewById(R.id.txt_translate);
            TextView txtName = findViewById(R.id.txt_nameVocabulary);
            TextView txtSampleEn = findViewById(R.id.txt_sampleEn);
            TextView txtSampleVn = findViewById(R.id.txt_sampleVn);
            Button btnPeviousExample = findViewById(R.id.btn_previousExample);
            Button btnNextExample = findViewById(R.id.btn_nextExample);
            Button btnExample = findViewById(R.id.btn_Example);
            ImageButton btnPronounce = findViewById(R.id.btn_pronounce);
            ImageView imageView = findViewById(R.id.videoView_thumbnail);
            ScrollView scrollView = findViewById(R.id.scrollView_Example);

            btnExample.setVisibility(View.VISIBLE);
            btnNextExample.setVisibility(View.GONE);
            btnPeviousExample.setVisibility(View.GONE);
            btnPronounce.setVisibility(View.GONE);
            scrollView.setVisibility(View.GONE);

            txtPronounce.setText(vocabularies.get(position).getPronounce());
            txtTranslate.setText(vocabularies.get(position).getVnName());
            txtName.setText(vocabularies.get(position).getName().toUpperCase());

            Uri uri = Uri.parse(videoPath + vocabularies.get(position).getName() + ".mp3");
            videoView.setVideoURI(uri);
            videoView.setMediaController(mediaController);
            mediaController.setAnchorView(videoView);

            btnPronounce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse(videoPath + vocabularies.get(position).getName() + ".mp3");
                    videoView.setVideoURI(uri);
                    videoView.start();
                }
            });

            //CALL VOLLEY SERVICE TO GET EXAMPLE
            VolleyService.getRequest(getApplicationContext(), "Speak/GetByIDVoCalbulary/" + vocabularies.get(position).getIdVocabulary(), new VolleyResponseListener() {
                @Override
                public void onError(String message) {

                }

                @Override
                public void onResponse(ModelCommon response) {

                    Picasso.get().load(Uri.parse("http://resource.bkt.net.vn/ImagesPNG/" + response.getSpeaks().get(0).getImages() + ".png")).into(imageView);


                    btnExample.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //SET VISIBLE FOR TWO BUTTON AFTER CLICK BUTTON EXAMPLE
                            btnNextExample.setVisibility(View.VISIBLE);
                            btnPeviousExample.setVisibility(View.VISIBLE);
                            scrollView.setVisibility(View.VISIBLE);
                            btnExample.setVisibility(View.GONE);
                            btnPronounce.setVisibility(View.VISIBLE);
                            //SET BUTTON PREVIOUS EXAMPLE ENABLE = FALSE
                            btnPeviousExample.setEnabled(false);
                            //SET BUTTON NEXT EXAMPLE ENABLE = TRUE
                            btnNextExample.setEnabled(true);

                            videoView.setVideoURI(Uri.parse(videoPath + response.getSpeaks().get(0).getSoundslow() + ".mp3"));
                            txtSampleEn.setText(response.getSpeaks().get(0).getSampleEn());
                            txtSampleVn.setText(response.getSpeaks().get(0).getSampleVn());
                        }
                    });
                    int[] exPosition = {0};

                    btnPeviousExample.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (exPosition[0] > 0) {
                                exPosition[0] = exPosition[0] - 1;

                                videoView.setVideoURI(Uri.parse(videoPath + response.getSpeaks().get(exPosition[0]).getSoundslow() + ".mp3"));
                                Picasso.get().load(Uri.parse("http://resource.bkt.net.vn/ImagesPNG/" + response.getSpeaks().get(exPosition[0]).getImages() + ".png")).into(imageView);
                                txtSampleEn.setText(response.getSpeaks().get(exPosition[0]).getSampleEn());
                                txtSampleVn.setText(response.getSpeaks().get(exPosition[0]).getSampleVn());

                                //IF BUTTON PREVIOUS EXAMPLE CAN ACTIVATED, BUTTON NEXT EXAMPLE SHOULD BE ENABLED
                                btnNextExample.setEnabled(true);
                                //IF EXAMPLE POSITION = 0, BUTTON PREVIOUS EXAMPLE SHOULD BE DISABLED
                                if (exPosition[0] == 0) {
                                    btnPeviousExample.setEnabled(false);
                                }
                            }
                        }
                    });

                    btnNextExample.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (exPosition[0] < response.getSpeaks().size() - 1) {
                                exPosition[0] = exPosition[0] + 1;

                                videoView.setVideoURI(Uri.parse(videoPath + response.getSpeaks().get(exPosition[0]).getSoundslow() + ".mp3"));
                                Picasso.get().load(Uri.parse("http://resource.bkt.net.vn/ImagesPNG/" + response.getSpeaks().get(exPosition[0]).getImages() + ".png")).into(imageView);
                                txtSampleEn.setText(response.getSpeaks().get(exPosition[0]).getSampleEn());
                                txtSampleVn.setText(response.getSpeaks().get(exPosition[0]).getSampleVn());

                                //IF BUTTON NEXT EXAMPLE CAN ACTIVATED, BUTTON PREVIOUS EXAMPLE  SHOULD BE ENABLED
                                btnPeviousExample.setEnabled(true);
                                //IF EXAMPLE POSITION = END OF SPEAK SIZE, BUTTON NEXT EXAMPLE SHOULD BE DISABLED
                                if (exPosition[0] == response.getSpeaks().size() - 1) {
                                    btnNextExample.setEnabled(false);
                                }
                            }
                        }
                    });

                }
            });


        }
    }

}