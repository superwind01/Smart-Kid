package Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartkid.R;
import com.example.smartkid.SongDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import API.Song;
import VolleyService.VolleyService;

public class SongByTopicFragAdapter extends RecyclerView.Adapter<SongByTopicFragAdapter.MyView> {
    private Context context;
    private ArrayList<Song> songArrayList;

    private OnItemClickListener mListener;
    public SongByTopicFragAdapter(Context context, ArrayList<Song> songArrayList) {
        this.context = context;
        this.songArrayList = songArrayList;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_song_by_topic_frag,parent,false);
        return new MyView(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        holder.txtNameSong.setText(songArrayList.get(position).getName());
        Picasso.get().load(Uri.parse("http://resource.bkt.net.vn/ImagesPNG/"+ songArrayList.get(position).getImage() + ".png")).into(holder.imgSong);

    }

    @Override
    public int getItemCount() {
        return songArrayList.size();
    }

    public class MyView extends RecyclerView.ViewHolder {

        ImageView imgSong;
        TextView txtNameSong;
        TextView txtTitleSongByTopicFrag;

        public MyView(@NonNull View itemView,OnItemClickListener listener) {
            super(itemView);
            txtNameSong = itemView.findViewById(R.id.txt_name_song_by_topic_frag);
            txtTitleSongByTopicFrag = itemView.findViewById(R.id.txt_title_SongByTopicFrag);
            imgSong = itemView.findViewById(R.id.img_song);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener!= null)
                    {
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

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }
}
