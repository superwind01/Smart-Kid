 package Fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easyclass.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import API.ListVideo;
import API.ModelCommon;
import API.Song;
import API.Topic;
import Adapter.ListRecyclerVideoAdapter;
import Adapter.TopicFragAdapter;
import VolleyService.*;

public class CategoriesFrag extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categories, container, false);

        ArrayList<ListVideo> listVideos = new ArrayList<>();
        //Get Id topic (include get Song inside)
        VolleyService.getRequest(getContext(), "Topic/GetAll", new VolleyResponseListener() {
            @Override
            public void onError(String message) {

            }

            @Override
            public void onResponse(ModelCommon response) {
                if(response != null)
                {
                    ArrayList<Topic> topic = response.getTopics();

                    Collections.sort(topic, new Comparator<Topic>() {
                        @Override
                        public int compare(Topic o1, Topic o2) {
                            return o1.getName().toUpperCase().compareTo(o2.getName().toUpperCase());
                        }
                    });

                    for(int i = 0; i < topic.size();i++)
                    {
                        ArrayList<Topic> topic1 = new ArrayList<>();
                        topic1.add(topic.get(i));

                        //Get video Song by IdTopic
                        int finalI = i;
                        VolleyService.getRequest(getContext(), "Song/GetByIdTopic/" + topic.get(i).getIdTopic(), new VolleyResponseListener() {
                            @Override
                            public void onError(String message) {
                                Log.e("get topic url"+topic.get(finalI).getIdTopic(),message);
                            }

                            @Override
                            public void onResponse(ModelCommon response) {
                                if(response != null)
                                {
                                    ArrayList<Song> songs = response.getSongs();
                                    Collections.sort(songs, new Comparator<Song>() {
                                        @Override
                                        public int compare(Song o1, Song o2) {
                                            return o1.getName().toUpperCase().compareTo(o2.getName().toUpperCase());
                                        }
                                    });
                                    listVideos.add(new ListVideo(TopicFragAdapter.TYPE_TOPIC, topic1, null));
                                    listVideos.add(new ListVideo(TopicFragAdapter.TYPE_SONG, null, songs));
                                }

                                RecyclerView recyclerVideoTopic = requireView().findViewById(R.id.recycler_video_topic);
                                recyclerVideoTopic.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));

                                recyclerVideoTopic.setAdapter(new TopicFragAdapter(getContext(), listVideos));
                            }
                        });
                    }
                }
            }
        });

        return view;
    }

}