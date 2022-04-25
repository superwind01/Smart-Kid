package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartkid.R;

import java.util.ArrayList;

import API.Song;

//THIS ADAPTER WILL GET DATA TO SHOW A LIST SONG
//JUST ONLY SHOW IN CATEGORIES FRAGMENT
// CAN DO WITH OTHER THINGS IF ADD IN CLASS MyView
public class ListSongAdapter extends RecyclerView.Adapter<ListSongAdapter.MyView> {
    private Context context;
    private ArrayList<Song> songArrayList;

    //DECLARE LISTENER WHEN ITEM CLICKED (CREATE INTERFACE BELOW)
    private OnTopicItemClickListener mListener;

    public ListSongAdapter(Context context, ArrayList<Song> songArrayList) {
        this.context = context;
        this.songArrayList = songArrayList;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //THIS LAYOUT WILL INSERT LIST SONG INTO LAYOUT  list_song
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_song,parent,false);
        return new MyView(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        //SET TEXT FOR txt_nameSong_by_Topic
        holder.txtNameSong.setText(songArrayList.get(position).getName().toString());
    }

    @Override
    public int getItemCount() {
        return songArrayList.size();
    }

    public class MyView extends RecyclerView.ViewHolder {
        TextView txtNameSong;
        //MUST ADD OnTopicItemClickListener listener INTO FUNCTION public MyView(@NonNull View itemView)
        public MyView(@NonNull View itemView, OnTopicItemClickListener listener) {
            super(itemView);
            txtNameSong =itemView. findViewById(R.id.txt_nameSong_by_Topic);

            //SET CLICK LISTENER FOR LIST SONG
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
    public interface OnTopicItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnTopicItemClickListener listener){
        mListener = listener;
    }
}
