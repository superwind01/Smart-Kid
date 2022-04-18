package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyclass.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import API.ListVideo;
import API.Topic;

public class TopicFragAdapter extends RecyclerView.Adapter<TopicFragAdapter.MyView>{
    public final static int TYPE_TOPIC = 1;
    public final static  int TYPE_SONG = 2;
    private Context context;
    private ArrayList<ListVideo> listVideos;

    public TopicFragAdapter(Context context, ArrayList<ListVideo> listVideos) {
        this.context = context;
        this.listVideos = listVideos;
    }

    @Override
    public int getItemViewType(int position) {
        return listVideos.get(position).getType();
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_video_by_topic,parent,false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        if(holder.getItemViewType() == TYPE_TOPIC)
        {
            LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            holder.recyclerVideoItem.setLayoutManager(layoutManager);

            RecyclerTopicAdapter mAdapter = new RecyclerTopicAdapter(context,listVideos.get(position).getTopics());
            holder.recyclerVideoItem.setAdapter(mAdapter);
        }
        else if(holder.getItemViewType() == TYPE_SONG)
        {
            LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            holder.recyclerVideoItem.setLayoutManager(layoutManager);

            ListSongAdapter mAdapter = new ListSongAdapter(context,listVideos.get(position).getSongs());
            holder.recyclerVideoItem.setAdapter(mAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return listVideos.size();
    }

    public class MyView extends RecyclerView.ViewHolder {
        RecyclerView recyclerVideoItem;
        public MyView(@NonNull View itemView) {
            super(itemView);
            recyclerVideoItem  = itemView.findViewById(R.id.recycler_video_item);
        }
    }
}
