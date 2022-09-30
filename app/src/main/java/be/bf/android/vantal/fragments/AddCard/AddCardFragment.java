package be.bf.android.vantal.fragments.AddCard;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import be.bf.android.vantal.R;
import be.bf.android.vantal.dal.CardDB;
import be.bf.android.vantal.dal.CardDao;
import be.bf.android.vantal.entities.Card;

public class AddCardFragment extends Fragment {

    private TextView tv_card_digits_1, tv_card_digits_2, tv_card_digits_3, tv_card_digits_4, tv_cardholder, tv_exp_month, tv_exp_year, tv_cvv;
    private EditText et_enter_digits_1, et_enter_digits_2, et_enter_digits_3, et_enter_digits_4, et_cardholder, et_expiry_month, et_expiry_year, et_cvv;
    private Button save_btn;
    private TextView tv_cancel;
    private NavController navController;

    private CardDao cardDao;

    public AddCardFragment() {
        // Required empty public constructor
    }


    public static AddCardFragment newInstance(String param1, String param2) {
        AddCardFragment fragment = new AddCardFragment();
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
        View view = inflater.inflate(R.layout.fragment_add_card, container, false);

        cardDao = CardDB.instance(requireContext()).cardDao();

        navController = NavHostFragment.findNavController(this);

        // Textview
        tv_card_digits_1 = view.findViewById(R.id.tv_card_digits_1);
        tv_card_digits_2 = view.findViewById(R.id.tv_card_digits_2);
        tv_card_digits_3 = view.findViewById(R.id.tv_card_digits_3);
        tv_card_digits_4 = view.findViewById(R.id.tv_card_digits_4);
        tv_cardholder = view.findViewById(R.id.tv_cardholder);
        tv_exp_month = view.findViewById(R.id.tv_exp_month);
        tv_exp_year = view.findViewById(R.id.tv_exp_year);
        tv_cvv = view.findViewById(R.id.tv_cvv);
        tv_cancel = view.findViewById(R.id.tv_cancel);

        // EditText
        et_enter_digits_1 = view.findViewById(R.id.et_enter_digits_1);
        et_enter_digits_2 = view.findViewById(R.id.et_enter_digits_2);
        et_enter_digits_3 = view.findViewById(R.id.et_enter_digits_3);
        et_enter_digits_4 = view.findViewById(R.id.et_enter_digits_4);
        et_cardholder = view.findViewById(R.id.et_cardholder);
        et_expiry_month = view.findViewById(R.id.et_expiry_month);
        et_expiry_year = view.findViewById(R.id.et_expiry_year);
        et_cvv = view.findViewById(R.id.et_cvv);

        // Button
        save_btn = view.findViewById(R.id.save_btn);

        save_btn.setOnClickListener(this::saveCard);

        tv_cancel.setOnClickListener(this::goBack);

        return view;
    }

    private void goBack(View view) {
        navController.navigate(R.id.action_addCardFragment_to_payMethodFragment);
    }

    private void saveCard(View view) {
        String digits1, digits2, digits3, digits4, cardNumber;

        // Get digit values
        digits1 = et_enter_digits_1.getText().toString();
        digits2 = et_enter_digits_2.getText().toString();
        digits3 = et_enter_digits_3.getText().toString();
        digits4 = et_enter_digits_4.getText().toString();

        // Set card values
        tv_card_digits_1.setText(digits1);
        tv_card_digits_2.setText(digits2);
        tv_card_digits_3.setText(digits3);
        tv_card_digits_4.setText(digits4);

        // Set value on card
        tv_cardholder.setText(et_cardholder.getText().toString());
        tv_exp_month.setText(et_expiry_month.getText().toString());
        tv_exp_year.setText(et_expiry_year.getText().toString());
        tv_cvv.setText(et_cvv.getText().toString());

        //Set value for DB
        String cardHolder = et_cardholder.getText().toString();
        Integer cvv = Integer.valueOf(et_cvv.getText().toString());
        Integer exp_month = Integer.valueOf(et_expiry_month.getText().toString());
        Integer exp_year = Integer.valueOf(et_expiry_year.getText().toString());
        cardNumber = digits1 + digits2 + digits3 + digits4;

        // Save cardNumber in DB
        Card card = new Card(cardHolder, cardNumber, cvv, exp_month, exp_year);
        cardDao.insert(card);

    }
}