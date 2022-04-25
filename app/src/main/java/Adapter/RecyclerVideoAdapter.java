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

//THIS IS CHILD ADAPTER OF LIST RECYCLER VIDEO ADAPTER
//CAN USING AGAIN FOR OTHER PURPOSE
public class RecyclerVideoAdapter extends RecyclerView.Adapter<RecyclerVideoAdapter.ViewHolder>{
    private Context context;

    //DECLARE LISTENER WHEN ITEM CLICKED (CREATE INTERFACE BELOW)
    private OnItemClickListener mListener;

    private ArrayList<Song> songs;

    public RecyclerVideoAdapter(Context context, ArrayList<Song> songs) {
        this.context = context;
        this.songs = songs;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNameVideo;
//        private TextView txtViewVideo;
//        private ImageView imgVideo;

        //MUST ADD OnTopicItemClickListener listener INTO FUNCTION public MyView(@NonNull View itemView)
        public ViewHolder(@NonNull View itemView,OnItemClickListener listener) {
            super(itemView);

            txtNameVideo = itemView.findViewById(R.id.txt_nameVideo);
//            txtViewVideo = itemView.findViewById(R.id.txt_viewVideo);
//            imgVideo = itemView.findViewById(R.id.img_video);

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

        public TextView getTxtNameVideo() {
            return txtNameVideo;
        }

//        public TextView getTxtViewVideo() {
//            return txtViewVideo;
//        }

//        public ImageView getImgVideo() {
////            return imgVideo;
//        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //SHOW IN LAYOUT cardview_video
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_video, parent, false);

        return new ViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerVideoAdapter.ViewHolder holder, int position) {
        holder.getTxtNameVideo().setText(songs.get(position).getName());
//        holder.getTxtViewVideo().setText(viewVideo[position]);
//        holder.getImgVideo().setImageResource(image[position]);
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    //DECLARE INTERFACE FOR CLICK LISTENER
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
}
