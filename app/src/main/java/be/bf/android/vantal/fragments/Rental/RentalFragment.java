package be.bf.android.vantal.fragments.Rental;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.widget.ViewPager2;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

import be.bf.android.vantal.R;


public class RentalFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    VPAdapter vpAdapter;
    SharedPreferences sharedPreferences;
    private String value = "key";
    NavController navController;

    public RentalFragment() {
        // Required empty public constructor
    }

    public static RentalFragment newInstance(String param1, String param2) {
        RentalFragment fragment = new RentalFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_rental, container, false);

       tabLayout = view.findViewById(R.id.tabLayout);
       viewPager2 = view.findViewById(R.id.viewPager);
       vpAdapter = new VPAdapter(getActivity());
       viewPager2.setAdapter(vpAdapter);

       navController = NavHostFragment.findNavController(this);

        // Get ID of logged User
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        Integer userID = sharedPreferences.getInt(value, 0);

        if (userID <= 0) {
            navController.navigate(R.id.login_fragment);
        }

       tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
                public void onTabSelected(TabLayout.Tab tab) {
                   viewPager2.setCurrentItem(tab.getPosition());
                }
                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }
                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });

       viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
           @Override
           public void onPageSelected(int position) {
               super.onPageSelected(position);
               tabLayout.getTabAt(position).select();
           }
       });


        return view;
    }
}