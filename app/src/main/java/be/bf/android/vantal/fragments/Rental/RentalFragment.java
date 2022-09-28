package be.bf.android.vantal.fragments.Rental;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

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

       //TODO
       tabLayout.setOnTabSelectedListener();

       return view;
    }
}