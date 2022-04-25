package Adapter;

import android.content.Context;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import android.widget.TextView;


import com.example.smartkid.R;

public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private String[] nameLogo;
    private int[] Logo;
    private int grid;

    public GridViewAdapter(Context context, String[] nameLogo, int[] logo, int grid) {
        this.context = context;
        this.nameLogo = nameLogo;
        Logo = logo;
        this.grid = grid;
    }


    @Override
    public int getCount() {
        return nameLogo.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        switch (grid) {

            //SHOW ITEM BELOW SCHOOL FUNCTION IN HOME FRAGMENT
            case 0: {
                view = layoutInflater.inflate(R.layout.grid_item, null);
                TextView textView = (TextView) view.findViewById(R.id.txt_item);
                ImageView imageView = (ImageView) view.findViewById(R.id.img_item);

                textView.setText(nameLogo[i]);
                imageView.setImageResource(Logo[i]);
                break;
            }

            //SHOW GRID LAYOUT FORMAL STUDY IN HOME FRAGMENT
            case 1: {
                view = layoutInflater.inflate(R.layout.grid_formalstudy, null);
                TextView textView = (TextView) view.findViewById(R.id.txt_formal);
                ImageView imageView = (ImageView) view.findViewById(R.id.img_formal);

                textView.setText(nameLogo[i]);
                imageView.setImageResource(Logo[i]);
                break;
            }

            //SHOW GRID LAYOUT SUPPLEMENT STUDY IN HOME FRAGMENT
            case 2: {
                view = layoutInflater.inflate(R.layout.grid_supplementstudy, null);
                TextView textView = (TextView) view.findViewById(R.id.txt_supplement);
                ImageView imageView = (ImageView) view.findViewById(R.id.img_supplement);

                textView.setText(nameLogo[i]);
                imageView.setImageResource(Logo[i]);
                break;
            }

            //SHOW GRID LAYOUT GRID BOOK IN CLASS FRAGMENT
            case 3:
            {
                view = layoutInflater.inflate(R.layout.grid_book, null);
                TextView textView = (TextView) view.findViewById(R.id.txt_book);
                ImageView imageView = (ImageView) view.findViewById(R.id.img_book);

                textView.setText(nameLogo[i]);
                break;
            }
        }
        return view;
    }
}
