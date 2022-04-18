package Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyclass.R;
import com.example.easyclass.VideoShowActivity;

import java.util.ArrayList;
import java.util.List;

import API.ListVideo;
import API.ModelCommon;
import API.Song;
import API.Topic;

public class ListRecyclerVideoAdapter extends RecyclerView.Adapter<ListRecyclerVideoAdapter.MyViewHolder> {

    public static final int TYPE_TOPIC= 1;
    public static final int TYPE_SONG= 2;

    private ArrayList<ListVideo> listVideos;
    private Context context;


    public ListRecyclerVideoAdapter(Context context,ArrayList<ListVideo> listVideos) {
        this.context = context;
        this.listVideos = listVideos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return listVideos.get(position).getType();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_video_by_topic,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ArrayList<ListVideo> listVideoArrayList = listVideos;
        if(holder.getItemViewType() == TYPE_SONG)
        {
            LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            holder.recyclerVideoItem.setLayoutManager(layoutManager);

            RecyclerVideoAdapter videoAdapter = new RecyclerVideoAdapter(context,listVideoArrayList.get(position).getSongs());
            holder.recyclerVideoItem.setAdapter(videoAdapter);
            videoAdapter.setOnItemClickListener(new RecyclerVideoAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int i) {
                    Intent intent = new Intent(context.getApplicationContext(), VideoShowActivity.class);
                    intent.putExtra("name",listVideoArrayList.get(position).getSongs().get(i).getName());
                    intent.putExtra("path","http://resource.bkt.net.vn/AudioMP4/"+listVideoArrayList.get(position).getSongs().get(i).getName()+".mp4");
                    String a = intent.getStringExtra("name");
                    String b = intent.getStringExtra("path");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startActivity(intent);
                }
            });
        }else if(holder.getItemViewType() == TYPE_TOPIC)
        {
            LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            holder.recyclerVideoItem.setLayoutManager(layoutManager);

            RecyclerTopicAdapter topicAdapter = new RecyclerTopicAdapter(context,listVideoArrayList.get(position).getTopics());
            holder.recyclerVideoItem.setAdapter(topicAdapter);
        }

    }

    @Override
    public int getItemCount() {
        return listVideos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView recyclerVideoItem;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerVideoItem = itemView.findViewById(R.id.recycler_video_item);
        }
    }
}
