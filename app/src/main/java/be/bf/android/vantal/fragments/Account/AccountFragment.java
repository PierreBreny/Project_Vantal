package be.bf.android.vantal.fragments.Account;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import be.bf.android.vantal.R;


public class AccountFragment extends Fragment {
    private CardView profile_card;
    private CardView help_card;
    private CardView contact_card;
    private CardView payment_methods_card;
    private CardView rent_card;
    private String value = "key";
    private NavController navController;
    SharedPreferences sharedPreferences;

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

        // Get ID of logged User
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        Integer userID = sharedPreferences.getInt(value, 0);



        Log.d("AccountFrag", String.valueOf(userID));

        navController = NavHostFragment.findNavController(this);

        if (userID <= 0) {
            navController.navigate(R.id.popFragment);
        }


        profile_card = view.findViewById(R.id.profile_card);
        profile_card.setOnClickListener(this::goToProfile);

        help_card = view.findViewById(R.id.help_card);
        help_card.setOnClickListener(this::callHelp);

        contact_card = view.findViewById(R.id.contact_card);
        contact_card.setOnClickListener(this::goToContact);

        payment_methods_card = view.findViewById(R.id.payment_methods_card);
        payment_methods_card.setOnClickListener(this::goToPayMethod);

        rent_card = view.findViewById(R.id.rent_card);
        rent_card.setOnClickListener(this::rentMyVan);

        return view;
    }

    private void rentMyVan(View view) {
        navController.navigate(R.id.action_accountFragment_to_addVanFragment);
    }

    private void goToPayMethod(View view) {
        navController.navigate(R.id.action_accountFragment_to_payMethodFragment);
    }

    private void goToContact(View view) {
        navController.navigate(R.id.action_accountFragment_to_contactFragment);
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