package be.bf.android.vantal.fragments.Profile;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import be.bf.android.vantal.R;

public class ProfileFragment extends Fragment {
    private CardView edit_profile_card;
    private NavController navController;

    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        navController = NavHostFragment.findNavController(this);

        edit_profile_card = view.findViewById(R.id.edit_profile_card);
        edit_profile_card.setOnClickListener(this::editProfile);


        return view;
    }

    private void editProfile(View view) {
        navController.navigate(R.id.action_profileFragment_to_editProfileFragment);
    }

}