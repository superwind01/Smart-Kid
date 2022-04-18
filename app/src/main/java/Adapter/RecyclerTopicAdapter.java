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

import API.Topic;

public class RecyclerTopicAdapter extends RecyclerView.Adapter<RecyclerTopicAdapter.ViewHolder> {
    private Context context;

    private OnTopicItemClickListener mListener;

    private ArrayList<Topic> topics;

    public RecyclerTopicAdapter(Context context, ArrayList<Topic> topics) {
        this.context = context;
        this.topics = topics;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameTopic;
        public ViewHolder(@NonNull View itemView, OnTopicItemClickListener listener) {
            super(itemView);
            txtNameTopic = itemView.findViewById(R.id.txt_topic);
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

    @NonNull
    @Override
    public RecyclerTopicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_name_by_topic, parent, false);

        return new RecyclerTopicAdapter.ViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerTopicAdapter.ViewHolder holder, int position) {
        holder.txtNameTopic.setText(topics.get(position).getName());
//        holder.getTxtViewVideo().setText(viewVideo[position]);
//        holder.getImgVideo().setImageResource(image[position]);
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }

    //Set Click Listener
    public interface OnTopicItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnTopicItemClickListener listener){
        mListener = listener;
    }
}
