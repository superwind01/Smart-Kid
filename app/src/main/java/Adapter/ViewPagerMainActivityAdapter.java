package Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import Fragments.ChatFrag;
import Fragments.HomeFrag;
import Fragments.NotificationFrag;
import Fragments.PersonalFrag;
import Fragments.StoreFrag;

public class ViewPagerMainActivityAdapter extends FragmentStateAdapter {
    public ViewPagerMainActivityAdapter(@NonNull FragmentActivity fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            default:
                return new HomeFrag();
            case 0:
                return  new HomeFrag();
            case 1:
                return  new PersonalFrag();
            case 2:
                return  new StoreFrag();
            case 3:
                return  new ChatFrag();
            case 4:
                return  new NotificationFrag();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
