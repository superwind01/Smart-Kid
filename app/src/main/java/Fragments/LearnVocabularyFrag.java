package Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.smartkid.R;
import com.example.smartkid.VocabularyLessonActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import API.ListNameVocabulary;
import API.ModelCommon;
import API.Vocabulary;
import Adapter.ListViewAdapter;
import VolleyService.*;
public class LearnVocabularyFrag extends Fragment {


    ListView listView;
    String idtopic;
    String url;

///////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null)
        {
            url = "Vocalbulary/GetByIDTopic/" + bundle.get("idtopicKey");
            idtopic = (String) bundle.get("idtopicKey");
        }
        else
        {
            url = "Vocalbulary/GetAll";
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_learn_vocabulary, container, false);
        listView = view.findViewById(R.id.list_Vocabulary);

        VolleyService.getRequest(getActivity(),url, new VolleyResponseListener() {
            @Override
            public void onError(String message) {
            }
            @Override
            public void onResponse(ModelCommon response) {
                if(response!=null)
                {
                    final ArrayList<Vocabulary> vocabularies = response.getVocabularies();

                    //SORT VOCABULARIES LIST
                    Collections.sort(vocabularies, new Comparator<Vocabulary>() {
                        @Override
                        public int compare(Vocabulary o1, Vocabulary o2) {
                            return o1.getName().toUpperCase().compareTo(o2.getName().toUpperCase());
                        }
                    });
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            //CREATE NEW LIST NAME VOCABULARY WITH HEADER A-Z
                            ArrayList<Vocabulary> listNameVocabularies = new ArrayList<>();

                            //POSITION MUST BE 0,
                            //BUT API HAVE AN NULL OBJECT IN POSITION 0 SO IT'S START FROM 1
                            listNameVocabularies.add(new Vocabulary(0,vocabularies.get(0).getName().substring(0,1).toUpperCase(),null,null,null,false));
                            listNameVocabularies.add(new Vocabulary(vocabularies.get(0).getIdVocabulary(),vocabularies.get(0).getName().toUpperCase(),vocabularies.get(0).getPronounce(),vocabularies.get(0).getDescription(),vocabularies.get(0).getVnName(),vocabularies.get(0).isStatus()));
                            for(int i =1; i <vocabularies.size(); i++)
                            {
                                String a = vocabularies.get(i).getName().toUpperCase().substring(0,1);
                                String b = vocabularies.get(i-1).getName().toUpperCase().substring(0,1);
                                if( !a.equals(b) )
                                {
                                    listNameVocabularies.add(new Vocabulary(0,vocabularies.get(i).getName().substring(0,1).toUpperCase(),null,null,null,false));
                                }
                                listNameVocabularies.add(new Vocabulary(vocabularies.get(i).getIdVocabulary(),vocabularies.get(i).getName().toUpperCase(),vocabularies.get(i).getPronounce(),vocabularies.get(i).getDescription(),vocabularies.get(i).getVnName(),vocabularies.get(i).isStatus()));
                            }

                            //INSERT NEW LIST NAME VOCABULARY
                                listView.setAdapter(new ListViewAdapter(listNameVocabularies));
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    Intent intent = new Intent(getActivity(), VocabularyLessonActivity.class);
                                    intent.putExtra("name",vocabularies.get(i).getName());
                                    intent.putExtra("pronounce",vocabularies.get(i).getPronounce());
                                    intent.putExtra("vnname",vocabularies.get(i).getVnName());
                                    intent.putExtra("position",i);
                                    if(idtopic != null)
                                    {
                                         intent.putExtra("idtopicKey",idtopic);
                                    }
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