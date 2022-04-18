package Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.easyclass.MainActivity;
import com.example.easyclass.R;

import java.util.ArrayList;

import API.Class;
import API.ModelCommon;
import VolleyService.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClassFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClassFrag extends Fragment {


    public static ClassFrag newInstance(String param1, String param2) {
        ClassFrag fragment = new ClassFrag();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_class, container, false);
        Button btnClass3_4 = view.findViewById(R.id.btn_class3_4);
        Button btnClass4_5 = view.findViewById(R.id.btn_class4_5);
        Button btnClass5_6 = view.findViewById(R.id.btn_class5_6);
        Button btnClass18_36 = view.findViewById(R.id.btn_class18_36);

        btnClass3_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.putExtra("class","3-4");
                getActivity().finish();
                startActivity(intent);
            }
        });
        btnClass4_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.putExtra("class","4-5");
                getActivity().finish();
                startActivity(intent);
            }
        });
        btnClass5_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.putExtra("class","5-6");
                getActivity().finish();
                startActivity(intent);
            }
        });
        btnClass18_36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.putExtra("class","18-36");
                getActivity().finish();
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
    }
}