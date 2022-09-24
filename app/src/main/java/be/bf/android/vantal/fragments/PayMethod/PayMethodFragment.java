package be.bf.android.vantal.fragments.PayMethod;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import be.bf.android.vantal.R;

public class PayMethodFragment extends Fragment {

    private CardView cardView;
    private NavController navController;

    public PayMethodFragment() {
        // Required empty public constructor
    }

    public static PayMethodFragment newInstance(String param1, String param2) {
        PayMethodFragment fragment = new PayMethodFragment();

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
        View view =  inflater.inflate(R.layout.fragment_pay_method, container, false);

        navController = NavHostFragment.findNavController(this);

        cardView = view.findViewById(R.id.card_card);
        cardView.setOnClickListener(this::addCard);

        return view;
    }

    private void addCard(View view) {
        navController.navigate(R.id.action_payMethodFragment_to_addCardFragment);
    }
}