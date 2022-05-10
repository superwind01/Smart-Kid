package Adapter;

import android.graphics.Color;
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

import API.ListNameVocabulary;
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
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //CONVERTVIEW ÍS LISTVIEW VIEW
        // IF CONVERTVIEW != NULL, IT'S MEAN VIEW WAS USED AGAIN, ONLY UPDATE NEW DATA
        //IF CONVERTVIEW = NULL, CREATE NEW VIEW
        View view = View.inflate(parent.getContext(), R.layout.list_vocabulary, null);
        TextView txtName = view.findViewById(R.id.txt_nameVocabulary);
                //BIND DATA INTO VIEW
            Vocabulary vocabulary = this.listVocabulary.get(position);
                if(vocabulary.getIdVocabulary() == 0)
                {
                    txtName.setText(vocabulary.getName());
                    txtName.setBackgroundColor(Color.parseColor("#dff6e6"));
                    txtName.setTextSize(24);
                    view.setClickable(false);
                }
                else
                {
                    txtName.setText(vocabulary.getName());
                }
        return view;
    }
}
