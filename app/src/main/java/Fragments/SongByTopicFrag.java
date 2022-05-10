package Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartkid.R;
import com.example.smartkid.SongDetailActivity;

import API.ModelCommon;
import Adapter.SongByTopicFragAdapter;
import VolleyService.*;


public class SongByTopicFrag extends Fragment {

   String idTopic, nameTopic;
    public SongByTopicFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        idTopic = (String) bundle.get("idtopicKey");
        nameTopic = (String) bundle.get("nametopicKey");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_song_by_topic, container, false);

        TextView txtTitleSongByTopicFrag = view.findViewById(R.id.txt_title_SongByTopicFrag);


        RecyclerView recyclerSong = view.findViewById(R.id.recycler_song);
        recyclerSong.setLayoutManager(new LinearLayoutManager(container.getContext(),LinearLayoutManager.VERTICAL,false));

        //DECLARE SHARE PREFERENCES
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("shareClass", Context.MODE_PRIVATE);
        //GET SHARE PREFERENCES FROM CLASS FRAGMENT
        String nameBook = sharedPreferences.getString("namebookKey","");
        txtTitleSongByTopicFrag.setText(nameBook + " > " + nameTopic );

        VolleyService.getRequest(container.getContext(), "Song/GetByIdTopic/" + idTopic, new VolleyResponseListener() {
            @Override
            public void onError(String message) {
            }

            @Override
            public void onResponse(ModelCommon response) {
                SongByTopicFragAdapter mAdapter = new SongByTopicFragAdapter(getContext(),response.getSongs());
                recyclerSong.setAdapter(mAdapter);

                mAdapter.setOnItemClickListener(new SongByTopicFragAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(container.getContext(), SongDetailActivity.class);
                        intent.putExtra("tittleKey",txtTitleSongByTopicFrag.getText());
//                intent.putExtra("path","http://resource.bkt.net.vn/AudioMP4/"+listVideos.get(position).getSongs().get(i).getName()+".mp4");
                        intent.putExtra("idtopicKey", idTopic);
                        intent.putExtra("nametopicKey",nameTopic);
                        intent.putExtra("positionKey",position);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        container.getContext().startActivity(intent);
                    }
                });
            }
        });
        return view;
    }
}