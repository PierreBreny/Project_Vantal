package be.bf.android.vantal.fragments.Rental;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import be.bf.android.vantal.R;


public class EndedFragment extends Fragment {


    public EndedFragment() {
        // Required empty public constructor
    }

    public static EndedFragment newInstance(String param1, String param2) {
        EndedFragment fragment = new EndedFragment();
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
        return inflater.inflate(R.layout.fragment_ended, container, false);
    }
}