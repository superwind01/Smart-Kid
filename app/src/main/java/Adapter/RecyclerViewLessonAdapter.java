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

import API.Lesson;

public class RecyclerViewLessonAdapter extends RecyclerView.Adapter<RecyclerViewLessonAdapter.MyView>{
    private ArrayList<Lesson> lessonArrayList;
    private Context context;
    private OnItemClickListener mListener;

    public RecyclerViewLessonAdapter(Context context,ArrayList<Lesson> lessonArrayList) {
        this.lessonArrayList = lessonArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_lesson,parent,false);
        return new MyView(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        holder.txtLessonName.setText(lessonArrayList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return lessonArrayList.size();
    }

    public class MyView extends RecyclerView.ViewHolder{
        TextView txtLessonName;
        public MyView(@NonNull View itemView,OnItemClickListener listener) {
            super(itemView);

            txtLessonName = itemView.findViewById(R.id.txt_lessonName);

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
