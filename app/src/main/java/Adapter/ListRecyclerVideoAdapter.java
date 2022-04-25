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

//ALMOST SAME WITH TopicFragAdapter EXCEPT LAYOUT OF SONG
//BOTH USE LAYOUT list_video_by_topic LIKE A PARENT RECYCLER VIEW
public class ListRecyclerVideoAdapter extends RecyclerView.Adapter<ListRecyclerVideoAdapter.MyViewHolder> {

    // DECLARE TYPE OF LIST VIEOS
    //PUBLIC WILL BE ABLE TO USED IN OTHER CLASS (WHEN INSERT LIST VIDEO IN FAMOUS FRAGMENT)
    public static final int TYPE_TOPIC= 1;
    public static final int TYPE_SONG= 2;

    private ArrayList<ListVideo> listVideos;
    private Context context;


    public ListRecyclerVideoAdapter(Context context,ArrayList<ListVideo> listVideos) {
        this.context = context;
        this.listVideos = listVideos;

        //UPDATE LIST IF CHANGE
        notifyDataSetChanged();
    }

    //GET TYPE OF INSERTED LIST VIDEO
    @Override
    public int getItemViewType(int position) {
        return listVideos.get(position).getType();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //THIS LAYOUT WILL INSERT LIST SONG OR TOPIC INTO RECYCLERVIEW
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_video_by_topic,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ArrayList<ListVideo> listVideoArrayList = listVideos;
        if(holder.getItemViewType() == TYPE_SONG)
        {
            //DECLARE LINENEAR LAYOUT MANAGER TO MANAGE recyler_video_item (IN LAYOUT list_video_by_topic )
            LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            holder.recyclerVideoItem.setLayoutManager(layoutManager);

            //CREATE ADAPTER FOR SONG (RecyclerVideoAdapter(ADAPTER FOLDER) WILL  DO THIS)
            RecyclerVideoAdapter videoAdapter = new RecyclerVideoAdapter(context,listVideoArrayList.get(position).getSongs());
            holder.recyclerVideoItem.setAdapter(videoAdapter);

            //SET ON VIDEO ITEM IN LIST VIDEO WAS CLICKED
            //START NEW VideoShowActivity  TO SHOW VIDEO
            videoAdapter.setOnItemClickListener(new RecyclerVideoAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int i) {
                    Intent intent = new Intent(context.getApplicationContext(), VideoShowActivity.class);

                    //INPUT INTENT TO PASS DATA FROM FAMOUS FRAG TO VideoShowActivity
                    intent.putExtra("name",listVideoArrayList.get(position).getSongs().get(i).getName());
                    intent.putExtra("path","http://resource.bkt.net.vn/AudioMP4/"+listVideoArrayList.get(position).getSongs().get(i).getName()+".mp4");

                    //MUST USING THIS FLAG IF WANNA START NEW ACTIVITY (BECAUSE TOO MUCH THREAD RUN ON UI)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startActivity(intent);
                }
            });
        }else if(holder.getItemViewType() == TYPE_TOPIC)
        {
            //DECLARE LINENEAR LAYOUT MANAGER TO MANAGE recyler_video_item (THIS ID list_video_by_topic )
            LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            holder.recyclerVideoItem.setLayoutManager(layoutManager);

            //CREATE ADAPTER FOR TOPIC (RecyclerTopicAdapter(ADAPTER FOLDER) WILL  DO THIS)
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
