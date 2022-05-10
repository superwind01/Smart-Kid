package Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import com.example.smartkid.ExpandableHeightGridView;
import com.example.smartkid.R;
import com.example.smartkid.StudyActivity;
import com.example.smartkid.VideoActivity;
import com.example.smartkid.VocabularyActivity;

import Adapter.GridViewAdapter;

public class HomeFrag extends Fragment {
    String[] nameLogo = {"Rank","Mission","Game","Route","Video","Activated","Connect"};
    int[] Logo ={R.drawable.rank ,
            R.drawable.mission,
            R.drawable.game,
            R.drawable.route,
            R.drawable.youtube,
            R.drawable.activated,
            R.drawable.connect};
    String[] nameFormal = {"Vocabulary","Conversation","Gramma","Listen"};
    int[] formalLogo ={R.drawable.vocabulary,
            R.drawable.conversation,
            R.drawable.gramma,
            R.drawable.listen};
    String[] nameSupplement = {"Study","Excercise","Examination"};
    int[] supplementLogo ={R.drawable.study,
            R.drawable.excercise,
            R.drawable.examination};

    public HomeFrag() {
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Bundle bundle = getActivity().getIntent().getExtras();

        // EXPANDABLE HEIGHT GRIDVIEW
        // MUST USING THIS CLASS BECAUSE GRIDVIEW CAN'T INSIDE SCROLL VIEW
        ExpandableHeightGridView gridItem = (ExpandableHeightGridView) view.findViewById(R.id.grid_item);
        gridItem.setExpanded(true);
        ExpandableHeightGridView gridFormalStudy = (ExpandableHeightGridView) view.findViewById(R.id.grid_formalStudy);
        gridFormalStudy.setExpanded(true);
        ExpandableHeightGridView gridSupplementStudy = (ExpandableHeightGridView) view.findViewById(R.id.grid_SupplementStudy);
        gridSupplementStudy.setExpanded(true);

        gridItem.setAdapter(new GridViewAdapter(getActivity(), nameLogo, Logo, null,0));
        gridFormalStudy.setAdapter(new GridViewAdapter(getActivity(), nameFormal, formalLogo,null, 1));
        gridSupplementStudy.setAdapter(new GridViewAdapter(getActivity(), nameSupplement, supplementLogo, null,2));

        gridFormalStudy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0)
                {
                    Intent intent = new Intent(getActivity(), VocabularyActivity.class);
                    startActivity(intent);
                }
                else if(i == 1)
                {
                    Intent intent = new Intent(getActivity(), VocabularyActivity.class);
                    startActivity(intent);
                }
                else if(i == 2)
                {
                    Intent intent = new Intent(getActivity(), VocabularyActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(getActivity(), VocabularyActivity.class);
                    startActivity(intent);
                }
            }
        });

        gridItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 4)
                {
                    Intent intent = new Intent(getActivity(), VideoActivity.class);
                    startActivity(intent);
                }
            }
        });

        gridSupplementStudy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i ==0)
                {
                    Intent intent = new Intent(getActivity(), StudyActivity.class);
                    startActivity(intent);
                }
            }
        });

        Button btnClass = view.findViewById(R.id.btn_chose_class);
        if(bundle != null)
        {
            btnClass.setText((String) bundle.get("class"));
        }
        //ADD CLASS FRAGMENT INTO MAIN ACTIVITY
        btnClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

                ft.add(R.id.frame_class,  new ClassFrag());
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return view;
    }
}