package Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easyclass.R;
import com.example.easyclass.VocabularyLessonActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import API.Class;
import API.ModelCommon;
import API.Vocabulary;
import Adapter.ListViewAdapter;
import VolleyService.*;
public class LearnVocabularyFrag extends Fragment {


    ListView listView;


///////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_learn_vocabulary, container, false);
        listView = view.findViewById(R.id.list_Vocabulary);

        VolleyService.getRequest(getActivity(),"Vocalbulary/GetAll", new VolleyResponseListener() {
            @Override
            public void onError(String message) {
            }
            @Override
            public void onResponse(ModelCommon response) {
                if(response!=null)
                {
                    final ArrayList<Vocabulary> vocabularies = response.getVocabularies();
                    Collections.sort(vocabularies, new Comparator<Vocabulary>() {
                        @Override
                        public int compare(Vocabulary o1, Vocabulary o2) {
                            return o1.getName().toUpperCase().compareTo(o2.getName().toUpperCase());
                        }
                    });
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                                listView.setAdapter(new ListViewAdapter(vocabularies));
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    Intent intent = new Intent(getActivity(), VocabularyLessonActivity.class);
                                    intent.putExtra("name",vocabularies.get(i).getName());
                                    intent.putExtra("pronounce",vocabularies.get(i).getPronounce());
                                    intent.putExtra("vnname",vocabularies.get(i).getVnName());
                                    intent.putExtra("position",i);
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                }

            }
        });
        return view;
    }
}