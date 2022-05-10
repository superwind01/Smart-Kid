package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartkid.R;
import com.example.smartkid.VocabularyLessonActivity;

import java.util.ArrayList;

import API.Vocabulary;
import API.VocabularyByTopicLesson;
import VolleyService.VolleyService;

public class LessonVideoActivityAdapter extends RecyclerView.Adapter<LessonVideoActivityAdapter.MyView>{
    private Context context;
    private ArrayList<Vocabulary> vocabularies;

    private OnItemClickListener mListener;
    public LessonVideoActivityAdapter(Context context, ArrayList<Vocabulary> vocabularies) {
        this.context = context;
        this.vocabularies = vocabularies;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_vocabulary_by_lesson,parent,false);
        return new MyView(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        holder.txtVocabularyByLesson.setText(vocabularies.get(position).getName() + " : " + vocabularies.get(position).getVnName());
    }

    @Override
    public int getItemCount() {
        return vocabularies.size();
    }

    public class MyView extends RecyclerView.ViewHolder {
        TextView txtVocabularyByLesson;
        public MyView(@NonNull View itemView,OnItemClickListener listener) {
            super(itemView);
            txtVocabularyByLesson = itemView.findViewById(R.id.txt_vocabulary_by_lesson);

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

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }
}
