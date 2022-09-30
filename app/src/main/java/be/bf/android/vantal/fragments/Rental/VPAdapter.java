package be.bf.android.vantal.fragments.Rental;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import be.bf.android.vantal.R;

public class VPAdapter extends FragmentStateAdapter {

    public VPAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
       switch (position) {
           case 0:
               return new UpcomingFragment();
           case 1: return new EndedFragment();
           default: return new UpcomingFragment();
       }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
