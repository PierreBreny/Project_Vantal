package be.bf.android.vantal.fragments.AddVan;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import be.bf.android.vantal.R;

public class AddVanFragment extends Fragment {


    public AddVanFragment() {
        // Required empty public constructor
    }

    public static AddVanFragment newInstance(String param1, String param2) {
        AddVanFragment fragment = new AddVanFragment();
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
        View view = inflater.inflate(R.layout.fragment_add_van, container, false);


        return view;
    }

    private void onClick(View view) {
        // change color if one click
        // change back to previous color with second click
    }
}