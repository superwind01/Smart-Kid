package Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartkid.R;
import com.example.smartkid.VideoShowActivity;

import java.util.ArrayList;

import API.ListVideo;
import API.ModelCommon;
import API.Song;
import API.Topic;
import Adapter.ListRecyclerVideoAdapter;
import Adapter.RecyclerVideoAdapter;
import VolleyService.*;

public class FamousFrag extends Fragment {

    public FamousFrag() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_famous, container, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);

        RecyclerView recyclerFamous =  view.findViewById(R.id.recycler_famous);
        recyclerFamous.setLayoutManager(layoutManager);

        //Need to Request Topic/GetAll Api to get idTopic and nameTopic
        //Using idTopic and nameTopic insert to ListRecyclerVideoAdapter
        //The code below is not for get Topic
        VolleyService.getRequest(getContext(), "Song/GetByIdTopic/58", new VolleyResponseListener() {

            @Override
            public void onError(String message) {

            }

            @Override
            public void onResponse(ModelCommon response) {
                if(response != null)
                {
                    ArrayList<Song> songs = response.getSongs();
                    RecyclerVideoAdapter mAdapter = new RecyclerVideoAdapter(getContext(),songs);
                    recyclerFamous.setAdapter(mAdapter);
                    mAdapter.setOnItemClickListener(new RecyclerVideoAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Intent intent = new Intent(getContext(), VideoShowActivity.class);
                            intent.putExtra("name",songs.get(position).getName());
                            intent.putExtra("path","http://resource.bkt.net.vn/AudioMP4/"+songs.get(position).getName()+".mp4");
                            startActivity(intent);
                        }
                    });
                }
            }
        });

        ArrayList<ListVideo> listVideos = new ArrayList<>();
//        Get Id topic (include get Song inside)
            VolleyService.getRequest(getContext(), "Topic/GetAll", new VolleyResponseListener() {
                @Override
                public void onError(String message) {

                }

                @Override
                public void onResponse(ModelCommon response) {
                    if(response != null)
                    {
                        ArrayList<Topic> topic = response.getTopics();
                        for(int i = 0; i < 3;i++)
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
                                        listVideos.add(new ListVideo(ListRecyclerVideoAdapter.TYPE_TOPIC, topic1, null));
                                        listVideos.add(new ListVideo(ListRecyclerVideoAdapter.TYPE_SONG, null, songs));
                                    }
                                    RecyclerView recyclerVideoByTopic = requireView().findViewById(R.id.recycler_video_by_topic);
                                    recyclerVideoByTopic.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false));
                                    ListRecyclerVideoAdapter mAdapter = new ListRecyclerVideoAdapter(getContext(),listVideos);
                                    recyclerVideoByTopic.setAdapter(mAdapter);
                                }
                            });
                        }
                    }
                }
            });

        return view;
    }



}