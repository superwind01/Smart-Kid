package Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartkid.R;

import API.ModelCommon;
import Adapter.TopicLessonFragAdapter;
import VolleyService.*;


public class TopicLessonFrag extends Fragment {


    public TopicLessonFrag() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topic_lesson, container, false);

        RecyclerView recyclerStudy = view.findViewById(R.id.recycler_study);
        recyclerStudy.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));

        TextView txtLessonTopic = view.findViewById(R.id.txt_lesson_topic);
        ImageView imgStudy = view.findViewById(R.id.img_study);
        LinearLayout linearLayoutTopic= view.findViewById(R.id.linearLayout_topic);
        LinearLayout linearLayoutStudy = view.findViewById(R.id.linearLayout_study);

        //DECLARE SHARE PREFERENCES
        SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("shareClass", Context.MODE_PRIVATE);
        //GET SHARE PREFERENCES FROM CLASS FRAGMENT
        String idclass = sharedPreferences.getString("classKey","");
        String idbook = sharedPreferences.getString("bookKey","");

        VolleyService.getRequest(view.getContext(), "Topic/GetByIDClassAndIDBook/" + idbook + "/" + idclass, new VolleyResponseListener() {
            @Override
            public void onError(String message) {

            }

            @Override
            public void onResponse(ModelCommon response) {

                TopicLessonFragAdapter mAdapter = new TopicLessonFragAdapter(view.getContext(),response.getTopics());
                recyclerStudy.setAdapter(mAdapter);
            }
        });

        return view;
    }
}