package Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.easyclass.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import API.Topic;
import API.Vocabulary;

public class ListTopicAdapter extends BaseAdapter {
    private Context context;
    final ArrayList<Topic> topicArrayList;

    public ListTopicAdapter(Context context,ArrayList<Topic> topics) {
        this.context = context;
        this.topicArrayList = topics;
    }

    @Override
    public int getCount() {
        return topicArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return topicArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return topicArrayList.get(i).getIdTopic();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = View.inflate(view.getContext(), R.layout.list_name_by_topic, null);

        Topic topic = this.topicArrayList.get(i);
        TextView txtTopic = view.findViewById(R.id.txt_topic);

        txtTopic.setText(topic.getName());

        return view;
    }
}
