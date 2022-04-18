package Adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import API.Vocabulary;

public class RecyclerViewAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Vocabulary> listVocabularies;
    private ArrayList<Vocabulary> listVocabulariesSearch;

    public RecyclerViewAdapter(ArrayList<Vocabulary> listVocabularies, ArrayList<Vocabulary> listVocabulariesSearch) {
        this.listVocabularies = listVocabularies;
        this.listVocabulariesSearch = listVocabulariesSearch;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listVocabularies.size();
    }
}
