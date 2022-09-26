package be.bf.android.vantal.fragments.PayMethod;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.util.List;

import be.bf.android.vantal.R;
import be.bf.android.vantal.dal.CardDB;
import be.bf.android.vantal.dal.CardDao;
import be.bf.android.vantal.entities.Card;

public class PayMethodFragment extends Fragment {

    private CardView cardView;
    private ImageView backArrow;
    private NavController navController;
    private ConstraintLayout no_card_layout;
    private ConstraintLayout yes_card_layout;

    private CardDao cardDao;

    private Card card;

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

        cardDao = CardDB.instance(requireContext()).cardDao();

        navController = NavHostFragment.findNavController(this);

        no_card_layout = view.findViewById(R.id.no_card_layout);
        yes_card_layout = view.findViewById(R.id.yes_card_layout);


        if (isCardSaved() == false) {
            no_card_layout.setVisibility(View.VISIBLE);
            yes_card_layout.setVisibility(View.INVISIBLE);


        } else {
            no_card_layout.setVisibility(View.INVISIBLE);
            yes_card_layout.setVisibility(View.VISIBLE);
        }


        cardView = view.findViewById(R.id.card_card);
        cardView.setOnClickListener(this::addCard);

        backArrow = view.findViewById(R.id.backArrow);
        backArrow.setOnClickListener(this::goBack);

        return view;
    }

    private boolean isCardSaved() {
        List<Card> cardList = cardDao.getAllCard();
        if (cardList.size() <= 0) {
            return false;
        }
        this.card = cardList.get(0);

        return true;
    }

    private void goBack(View view) {
        navController.navigate(R.id.action_payMethodFragment_to_accountFragment);
    }

    private void addCard(View view) {
        navController.navigate(R.id.action_payMethodFragment_to_addCardFragment);
    }
}