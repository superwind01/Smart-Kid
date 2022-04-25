package Adapter;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.smartkid.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import API.Vocabulary;

//USED IN FRAGMENT LEARN VOCABULARY
//REUSABLE
public class ListViewAdapter extends BaseAdapter {

    final ArrayList<Vocabulary> listVocabulary;

    public ListViewAdapter(ArrayList<Vocabulary> listVocabulary) {
        this.listVocabulary = listVocabulary;
    }


    @Override
    public int getCount() {
        return listVocabulary.size();
    }

    @Override
    public Object getItem(int i) {
        return listVocabulary.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listVocabulary.get(i).getIdVocabulary();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //CONVERTVIEW ÍS LISTVIEW VIEW
        // IF CONVERTVIEW != NULL, IT'S MEAN VIEW WAS USED AGAIN, ONLY UPDATE NEW DATA
        //IF CONVERTVIEW = NULL, CREATE NEW VIEW
        View view = View.inflate(parent.getContext(), R.layout.list_vocabulary, null);

        //SORT LIST VOCABULARY BEFORE INSERT INTO LISTVIEW
        Collections.sort(listVocabulary, new Comparator<Vocabulary>() {
            @Override
            public int compare(Vocabulary o1, Vocabulary o2) {
                return o1.getName().toUpperCase().compareTo(o2.getName().toUpperCase());
            }
        });
        //BIND DATA INTO VIEW
        Vocabulary vocabulary = this.listVocabulary.get(position);
        TextView txtName = view.findViewById(R.id.txt_nameVocabulary);
        txtName.setText(vocabulary.getName().toUpperCase());

        return view;
    }
}
