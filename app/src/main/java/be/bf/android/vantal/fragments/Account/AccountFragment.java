package be.bf.android.vantal.fragments.Account;

import android.content.Intent;
import android.net.Uri;
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
    private CardView help_card;
    private NavController navController;

    // Help number
    private String number;

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

        help_card = view.findViewById(R.id.help_card);
        help_card.setOnClickListener(this::callHelp);

        return view;
    }

    private void callHelp(View view) {

        // Set help number
        number = "+32497241148";

        // Dial intent
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.decode(number)));
        startActivity(intent);
    }

    private void goToProfile(View view) {
        navController.navigate(R.id.action_accountFragment_to_profileFragment);
    }
}