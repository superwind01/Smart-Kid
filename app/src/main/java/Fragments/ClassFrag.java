package Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.example.easyclass.ExpandableHeightGridView;
import com.example.easyclass.MainActivity;
import com.example.easyclass.R;

import java.util.ArrayList;

import API.Book;
import API.Class;
import API.ModelCommon;
import Adapter.GridViewAdapter;
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

        SharedPreferences sharedPref = getActivity().getSharedPreferences("shareClass",getContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        ExpandableHeightGridView gridBook = (ExpandableHeightGridView) view.findViewById(R.id.grid_book);
        gridBook.setExpanded(true);


        VolleyService.getRequest(getContext(), "Book/GetAll", new VolleyResponseListener() {
            @Override
            public void onError(String message) {

            }

            @Override
            public void onResponse(ModelCommon response) {
                ArrayList<Book> books = new ArrayList<>();
                books = response.getBooks();
                ArrayList<String> book = new ArrayList<>();
                for (int i =0; i <books.size() ; i++)
                {
                    book.add(books.get(i).getNameBook());
                }
                gridBook.setAdapter(new GridViewAdapter(getContext(),book.toArray(new String[0]),null,3));

                ArrayList<Book> finalBooks = books;
                gridBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        editor.putString("bookKey", String.valueOf(finalBooks.get(i).getIdBook())).apply();
                    }
                });
            }
        });

        btnClass3_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.putExtra("class","3-4");
                sharedPref.edit().putString("classKey","2").apply();
                getActivity().finish();
                startActivity(intent);
            }
        });
        btnClass4_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.putExtra("class","4-5");
                sharedPref.edit().putString("classKey","3").apply();
                getActivity().finish();
                startActivity(intent);
            }
        });
        btnClass5_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.putExtra("class","5-6");
                sharedPref.edit().putString("classKey","4").apply();
                getActivity().finish();
                startActivity(intent);
            }
        });
        btnClass18_36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.putExtra("class","18-36");
                sharedPref.edit().putString("classKey","1").apply();
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