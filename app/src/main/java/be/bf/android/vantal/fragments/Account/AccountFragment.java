package be.bf.android.vantal.fragments.Account;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import be.bf.android.vantal.R;


public class AccountFragment extends Fragment {
    private CardView profile_card;
    private NavController navController;

    public AccountFragment() {
        // Required empty public constructor
    }

    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
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
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        navController = NavHostFragment.findNavController(this);

        profile_card = view.findViewById(R.id.profile_card);
        profile_card.setOnClickListener(this::goToProfile);

        return view;
    }

    private void goToProfile(View view) {
        navController.navigate(R.id.action_accountFragment_to_profileFragment);
    }
}