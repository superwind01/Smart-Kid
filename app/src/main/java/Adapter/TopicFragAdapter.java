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

import com.example.smartkid.R;
import com.example.smartkid.VideoShowActivity;

import java.util.ArrayList;

import API.ListVideo;

//TopicFragAdapter IS CATEGORIES FRAGMENT
//ALMOST SAME WITH ListRecyclerVideoAdapter EXCEPT LAYOUT OF SONG
//BOTH USE LAYOUT list_video_by_topic LIKE A PARENT RECYCLER VIEW
public class TopicFragAdapter extends RecyclerView.Adapter<TopicFragAdapter.MyView>{

    // DECLARE TYPE OF LIST TOPIC
    //PUBLIC WILL BE ABLE TO USED IN OTHER CLASS (WHEN INSERT LIST VIDEO IN CATEGORIES FRAGMENT)
    public final static int TYPE_TOPIC = 1;
    public final static  int TYPE_SONG = 2;
    private Context context;
    private ArrayList<ListVideo> listVideos;

    public TopicFragAdapter(Context context, ArrayList<ListVideo> listVideos) {
        this.context = context;
        this.listVideos = listVideos;

        //UPDATE LIST IF CHANGE
        notifyDataSetChanged();
    }

    //GET TYPE OF INSERTED LIST TOPIC
    @Override
    public int getItemViewType(int position) {
        return listVideos.get(position).getType();
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //THIS LAYOUT WILL INSERT LIST SONG OR TOPIC INTO RECYCLERVIEW
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_video_by_topic,parent,false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, @SuppressLint("RecyclerView") int position) {
        if(holder.getItemViewType() == TYPE_TOPIC)
        {
            //DECLARE LINENEAR LAYOUT MANAGER TO MANAGE recyler_video_item (THIS ID list_video_by_topic )
            LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            holder.recyclerVideoItem.setLayoutManager(layoutManager);

            RecyclerTopicAdapter mAdapter = new RecyclerTopicAdapter(context,listVideos.get(position).getTopics());
            holder.recyclerVideoItem.setAdapter(mAdapter);
        }
        else if(holder.getItemViewType() == TYPE_SONG)
        {
            //DECLARE LINENEAR LAYOUT MANAGER TO MANAGE recyler_video_item (IN LAYOUT list_video_by_topic )
            LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            holder.recyclerVideoItem.setLayoutManager(layoutManager);

            //CREATE ADAPTER FOR SONG (RecyclerVideoAdapter(ADAPTER FOLDER) WILL  DO THIS)
            ListSongAdapter mAdapter = new ListSongAdapter(context,listVideos.get(position).getSongs());
            holder.recyclerVideoItem.setAdapter(mAdapter);

            //SET ON SONG ITEM IN LIST SONG WAS CLICKED
            //START NEW VideoShowActivity  TO SHOW VIDEO
            mAdapter.setOnItemClickListener(new ListSongAdapter.OnTopicItemClickListener() {
                @Override
                public void onItemClick(int i) {
                    Intent intent = new Intent(context.getApplicationContext(), VideoShowActivity.class);
                    intent.putExtra("name",listVideos.get(position).getSongs().get(i).getName());
                    intent.putExtra("path","http://resource.bkt.net.vn/AudioMP4/"+listVideos.get(position).getSongs().get(i).getName()+".mp4");
                    String a = intent.getStringExtra("name");
                    String b = intent.getStringExtra("path");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startActivity(intent);
                }
            });
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
