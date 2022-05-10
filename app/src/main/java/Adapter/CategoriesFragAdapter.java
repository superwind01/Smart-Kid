package Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartkid.R;
import com.example.smartkid.VideoShowActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import API.ListVideo;
import API.Topic;
import API.Vocabulary;

//ALMOST SAME WITH ListRecyclerVideoAdapter EXCEPT LAYOUT OF SONG
//BOTH USE LAYOUT list_video_by_topic LIKE A PARENT RECYCLER VIEW
public class CategoriesFragAdapter extends RecyclerView.Adapter<CategoriesFragAdapter.MyView>{

    // DECLARE TYPE OF LIST TOPIC
    //PUBLIC WILL BE ABLE TO USED IN OTHER CLASS (WHEN INSERT LIST VIDEO IN CATEGORIES FRAGMENT)
    public final static int TYPE_TOPIC = 1;
    public final static  int TYPE_SONG = 2;
    private Context context;
    private ArrayList<ListVideo> listVideos;

    //DECLARE LISTENER WHEN ITEM CLICKED (CREATE INTERFACE BELOW)
    private OnClickListener mListener;

    //Set Expandable for layout list_song
    private boolean isExpandable = false;

    public CategoriesFragAdapter(Context context, ArrayList<ListVideo> listVideos) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_recyclerview,parent,false);
        return new MyView(view,mListener);
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
            holder.recyclerVideoItem.setVisibility(View.GONE);
            //DECLARE LINENEAR LAYOUT MANAGER TO MANAGE recyler_video_item (IN LAYOUT list_video_by_topic )
            LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            holder.recyclerVideoItem.setLayoutManager(layoutManager);

            //CREATE ADAPTER FOR SONG (RecyclerVideoAdapter(ADAPTER FOLDER) WILL  DO THIS)
            ListSongAdapter mAdapter = new ListSongAdapter(context,listVideos.get(position).getSongs());
            holder.recyclerVideoItem.setAdapter(mAdapter);

//            holder.itemView.setActivated(isExpandable);
                //EXPANDABLE RECYCLER SONGS
//            final int[] previousExpandedPosition = {-1};
//                            if (previousExpandedPosition[0] >=0)
//                            {
//                                holder.linearLayout.getChildAt(previousExpandedPosition[0]+1).setVisibility(View.GONE);
//                            }
//                            isExpandable = true;
//                            holder.linearLayout.setVisibility(isExpandable? View.VISIBLE : View.GONE);
//                            holder.linearLayout.setActivated(isExpandable);

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
//        RecyclerTopicAdapter mAdapter = new RecyclerTopicAdapter(context,listVideos.get(position).getTopics());
//        holder.recyclerVideoItem.setAdapter(mAdapter);
//
//        mAdapter.setOnItemClickListener(new RecyclerTopicAdapter.OnTopicItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                holder.recyclerVideoItem.getChildAt(position).setVisibility(View.VISIBLE);
//                holder.linearLayout.setVisibility(View.VISIBLE);
//                holder.linearLayout.setActivated(true);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return listVideos.size();
    }

    public class MyView extends RecyclerView.ViewHolder {
        RecyclerView recyclerVideoItem;
        LinearLayout linearLayout;
        public MyView(@NonNull View itemView, CategoriesFragAdapter.OnClickListener listener) {
            super(itemView);
            recyclerVideoItem  = itemView.findViewById(R.id.recycler_video_item);
            linearLayout = itemView.findViewById(R.id.linearLayout_expandable);

            //SET CLICK LISTENER FOR LIST TOPIC
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    //DECLARE INTERFACE FOR CLICK LISTENER
    public interface OnClickListener{
        void onItemClick(int position);
    }
    public void setOnClickListener(CategoriesFragAdapter.OnClickListener listener){
        mListener = listener;
    }
}
