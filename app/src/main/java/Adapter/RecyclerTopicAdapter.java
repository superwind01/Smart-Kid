package Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartkid.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import API.Topic;
import API.Vocabulary;

//THIS IS CHILD ADAPTER OF LIST RECYCLER VIDEO ADAPTER
//CAN USING AGAIN FOR OTHER PURPOSE
public class RecyclerTopicAdapter extends RecyclerView.Adapter<RecyclerTopicAdapter.ViewHolder> {
    private Context context;

    //DECLARE LISTENER WHEN ITEM CLICKED (CREATE INTERFACE BELOW)
    private OnTopicItemClickListener mListener;

    private ArrayList<Topic> topics;

    public RecyclerTopicAdapter(Context context, ArrayList<Topic> topics) {
        this.context = context;
        this.topics = topics;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameTopic;
        //MUST ADD OnTopicItemClickListener listener INTO FUNCTION public MyView(@NonNull View itemView)
        public ViewHolder(@NonNull View itemView, OnTopicItemClickListener listener) {
            super(itemView);
            txtNameTopic = itemView.findViewById(R.id.txt_topic);

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

    @NonNull
    @Override
    public RecyclerTopicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //SHOW IN LAYOUT list_name_by_topic
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

    //DECLARE INTERFACE FOR CLICK LISTENER
    public interface OnTopicItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnTopicItemClickListener listener){
        mListener = listener;
    }
}
