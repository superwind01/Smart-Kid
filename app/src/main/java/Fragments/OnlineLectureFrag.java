package Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartkid.LessonVideoActivity;
import com.example.smartkid.R;

import API.ModelCommon;
import Adapter.RecyclerViewLessonAdapter;
import VolleyService.*;

public class OnlineLectureFrag extends Fragment {

    public OnlineLectureFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_online_lecture, container, false);
        TextView txtTopicStudy = view.findViewById(R.id.txt_topicStudy);
        RecyclerView recyclerLesson = view.findViewById(R.id.recycler_lesson);

        Bundle bundle = getArguments();

        String idTopic = bundle.getString("idtopicKey");
        String nameTopic = bundle.getString("nametopicKey");

        txtTopicStudy.setText(nameTopic);

        VolleyService.getRequest(container.getContext(), "Lesson/GetByIdTopic/"+ idTopic, new VolleyResponseListener() {
            @Override
            public void onError(String message) {

            }

            @Override
            public void onResponse(ModelCommon response) {
                recyclerLesson.setLayoutManager(new LinearLayoutManager(container.getContext(),LinearLayoutManager.VERTICAL,false));
                RecyclerViewLessonAdapter mAdapter = new RecyclerViewLessonAdapter(container.getContext(),response.getLessons());
                recyclerLesson.setAdapter(mAdapter);

                mAdapter.setOnItemClickListener(new RecyclerViewLessonAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(getActivity(), LessonVideoActivity.class);
                        intent.putExtra("idtopicKey",idTopic);
                        intent.putExtra("idlessonKey",String.valueOf(response.getLessons().get(position).getIdlesson()));
                        intent.putExtra("nametopicKey",nameTopic);
                        startActivity(intent);
                    }
                });

            }
        });
        return view;
    }
}