package be.bf.android.vantal.fragments.Profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import be.bf.android.vantal.R;

public class ProfileFragment extends Fragment {
    private CardView edit_profile_card;
    private NavController navController;
    private ImageView back_arrow;
    private CardView delete_account_card;
    private CardView logout_card;
    AlertDialog.Builder builder;

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

        back_arrow = view.findViewById(R.id.backArrow);
        back_arrow.setOnClickListener(this::goBack);

        delete_account_card = view.findViewById(R.id.delete_account_card);
        delete_account_card.setOnClickListener(this::showAlert);

        logout_card = view.findViewById(R.id.logout_card);
        logout_card.setOnClickListener(this::logOut);

        builder = new AlertDialog.Builder(getContext());


        return view;
    }

    private void logOut(View view) {
        navController.navigate(R.id.action_profileFragment_to_login_fragment);
    }

    private void showAlert(View view) {
        builder.setTitle("Delete Account")
                .setMessage("Are you sure? Once you delete your account, there is no getting it back.")
                .setCancelable(true)
                .setPositiveButton("Yes", (dialog, which) -> deleteAccount())
                .setNegativeButton("No", (dialog, which) -> dialog.cancel())
                .show();
    }

    private void deleteAccount() {
        //delete acoount from DB
        //load sign in page
        navController.navigate(R.id.action_profileFragment_to_registerFragment);
        //show snackbar
        Toast.makeText(getContext(), "You successfully deleted your account", Toast.LENGTH_LONG).show();
    }

    private void goBack(View view) {
        navController.navigate(R.id.action_profileFragment_to_accountFragment);
    }

    private void editProfile(View view) {
        navController.navigate(R.id.action_profileFragment_to_editProfileFragment);
    }

}