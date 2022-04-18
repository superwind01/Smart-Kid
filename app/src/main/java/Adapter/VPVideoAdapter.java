package Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import Fragments.CategoriesFrag;
import Fragments.FamousFrag;

public class VPVideoAdapter extends FragmentStateAdapter {


    public VPVideoAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            default:
                return new FamousFrag();
            case 0:
                return new FamousFrag();
            case 1:
                return new CategoriesFrag();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
