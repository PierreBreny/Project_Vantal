package be.bf.android.vantal.fragments.Profile;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.transition.Scene;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import be.bf.android.vantal.R;

public class EditProfileFragment extends Fragment {
    private Button cancel_btn;
    private NavController navController;
    private ImageView back_arrow;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    public static EditProfileFragment newInstance(String param1, String param2) {
        EditProfileFragment fragment = new EditProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        navController = NavHostFragment.findNavController(this);

        cancel_btn = view.findViewById(R.id.btn_cancel);
        cancel_btn.setOnClickListener(this::clickCancel);

        back_arrow = view.findViewById(R.id.backArrow);
        back_arrow.setOnClickListener(this::goBack);

        return view;
    }

    private void goBack(View view) {
        navController.navigate(R.id.action_editProfileFragment_to_profileFragment);
    }

    private void clickCancel(View view) {
        navController.navigate(R.id.action_editProfileFragment_to_profileFragment);
    }
}