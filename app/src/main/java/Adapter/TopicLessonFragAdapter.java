package Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartkid.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import API.Topic;
import Fragments.LearnVocabularyFrag;
import Fragments.OnlineLectureFrag;
import Fragments.SongByTopicFrag;

public class TopicLessonFragAdapter extends  RecyclerView.Adapter<TopicLessonFragAdapter.MyView>{
    private Context context;

    public ArrayList<Topic> getTopics() {
        return topics;
    }

    public void setTopics(ArrayList<Topic> topics) {
        this.topics = topics;
    }

    private ArrayList<Topic> topics;

    int[] previousPosition = {-1};

    //DECLARE LISTENER WHEN ITEM CLICKED (CREATE INTERFACE BELOW)
    private OnItemClickListener mListener;

    public TopicLessonFragAdapter(Context context, ArrayList<Topic> topics) {
        this.context = context;
        this.topics = topics;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_topic_lesson,parent,false);
        return  new MyView(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, @SuppressLint("RecyclerView") int position) {
        holder.linearLayoutTopic.setVisibility(View.GONE);
        holder.linearLayoutStudy.setVisibility(View.GONE);

        Uri uri = Uri.parse("http://resource.bkt.net.vn/ImagesPNG/"+topics.get(position).getImage()+".png");
        Picasso.get().load(uri).into(holder.imgTopic);

        holder.txtLessonTopic.setText(topics.get(position).getName());
        if(previousPosition[0] ==position)
        {
            holder.linearLayoutTopic.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.linearLayoutTopic.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.linearLayoutTopic.setVisibility(View.VISIBLE);
                previousPosition[0] = position;
                notifyDataSetChanged();
            }
        });
        holder.linearLayoutTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.linearLayoutStudy.setVisibility(View.VISIBLE);

            }
        });

        holder.txtOnlineLectures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //GET ACTIVITY FROM VIEW
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();

                //PASS DATA FROM TOPIC LESSON FRAGMENT TO LESSON VOCABULARY FRAGMENT
                Bundle bundle = new Bundle();
                bundle.putString("idtopicKey", String.valueOf(topics.get(position).getIdTopic()));
                bundle.putString("nametopicKey",topics.get(position).getName());
                OnlineLectureFrag lessonVocabularyFrag = new OnlineLectureFrag();
                lessonVocabularyFrag.setArguments(bundle);
                ft.addToBackStack(null);
                ft.replace(R.id.frame_study, lessonVocabularyFrag);
                ft.commit();
            }
        });
        holder.txtStudyVocabulary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //GET ACTIVITY FROM VIEW
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();

                //PASS DATA FROM TOPIC LESSON FRAGMENT TO LESSON VOCABULARY FRAGMENT
                Bundle bundle = new Bundle();
                bundle.putString("idtopicKey", String.valueOf(topics.get(position).getIdTopic()));
                LearnVocabularyFrag learnVocabularyFrag = new LearnVocabularyFrag();
                learnVocabularyFrag.setArguments(bundle);
                ft.addToBackStack(null);
                ft.replace(R.id.frame_study, learnVocabularyFrag);
                ft.commit();
            }
        });
        holder.txtSongByTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //WHEN CLICK Song button, Image of Study Activity will be changed to Image Song
                holder.imgStudy.setImageResource(R.drawable.img_song);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();

                //PASS DATA FROM TOPIC LESSON FRAGMENT TO LESSON VOCABULARY FRAGMENT
                Bundle bundle = new Bundle();
                bundle.putString("idtopicKey", String.valueOf(topics.get(position).getIdTopic()));
                bundle.putString("nametopicKey",topics.get(position).getName());
                SongByTopicFrag songByTopicFrag = new SongByTopicFrag();
                songByTopicFrag.setArguments(bundle);
                ft.addToBackStack(null);
                ft.replace(R.id.frame_study, songByTopicFrag);
                ft.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }

    public class MyView extends RecyclerView.ViewHolder{

        TextView txtLessonTopic = itemView.findViewById(R.id.txt_lesson_topic);
        LinearLayout linearLayoutTopic= itemView.findViewById(R.id.linearLayout_topic);
        LinearLayout linearLayoutStudy = itemView.findViewById(R.id.linearLayout_study);
        TextView txtOnlineLectures = itemView.findViewById(R.id.txt_onlineLectures);
        TextView txtStudyVocabulary = itemView.findViewById(R.id.txt_studyVocabulary);
        TextView txtSongByTopic = itemView.findViewById(R.id.txt_song_by_topic);
        ImageView imgTopic = itemView.findViewById(R.id.img_topic);

        AppCompatActivity activity = (AppCompatActivity) itemView.getContext();
        ImageView imgStudy = activity.findViewById(R.id.img_study);

        public MyView(@NonNull View itemView,OnItemClickListener listener) {
            super(itemView);

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
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
}
