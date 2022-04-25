package Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

import Fragments.LearnVocabularyFrag;
import Fragments.LearningOutcomesFrag;

//VIEW PAGER WAS USED IN VOCABULARY ACTIVITY TO MANAGE FRAGMENT( TAB LAYOUT)
public class VPAdapter extends FragmentStateAdapter {

//    private final ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
//    private final ArrayList<String> fragmentTitle = new ArrayList<>();


    public VPAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){

            default:
                return new LearnVocabularyFrag();
            case 0:
                return new LearnVocabularyFrag();
            case 1:
                return new LearningOutcomesFrag();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
