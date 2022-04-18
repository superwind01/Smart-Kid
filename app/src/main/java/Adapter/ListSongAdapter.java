package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyclass.R;

import java.util.ArrayList;

import API.Song;

public class ListSongAdapter extends RecyclerView.Adapter<ListSongAdapter.MyView> {
    private Context context;
    private ArrayList<Song> songArrayList;

    public ListSongAdapter(Context context, ArrayList<Song> songArrayList) {
        this.context = context;
        this.songArrayList = songArrayList;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_song,parent,false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        holder.txtNameSong.setText(songArrayList.get(position).getName().toString());
    }

    @Override
    public int getItemCount() {
        return songArrayList.size();
    }

    public class MyView extends RecyclerView.ViewHolder {
        TextView txtNameSong;
        public MyView(@NonNull View itemView) {
            super(itemView);
            txtNameSong =itemView. findViewById(R.id.txt_nameSong_by_Topic);
        }
    }
}
