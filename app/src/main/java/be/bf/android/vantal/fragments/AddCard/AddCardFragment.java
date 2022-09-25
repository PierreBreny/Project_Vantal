package be.bf.android.vantal.fragments.AddCard;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import be.bf.android.vantal.R;

public class AddCardFragment extends Fragment {

    private TextView tv_card_digits_1, tv_card_digits_2, tv_card_digits_3, tv_card_digits_4, tv_cardholder, tv_exp_month, tv_exp_year, tv_cvv;
    private EditText et_enter_digits_1, et_enter_digits_2, et_enter_digits_3, et_enter_digits_4, et_cardholder, et_expiry_month, et_expiry_year, et_cvv;
    private Button save_btn;

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

        // Textview
        tv_card_digits_1 = view.findViewById(R.id.tv_card_digits_1);
        tv_card_digits_2 = view.findViewById(R.id.tv_card_digits_2);
        tv_card_digits_3 = view.findViewById(R.id.tv_card_digits_3);
        tv_card_digits_4 = view.findViewById(R.id.tv_card_digits_4);
        tv_cardholder = view.findViewById(R.id.tv_cardholder);
        tv_exp_month = view.findViewById(R.id.tv_exp_month);
        tv_exp_year = view.findViewById(R.id.tv_exp_year);
        tv_cvv = view.findViewById(R.id.tv_cvv);

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

        return view;
    }

    private void saveCard(View view) {
        String digits1, digits2, digits3, digits4, cardNumber;

        Animation animation = AnimationUtils.loadAnimation(getContext().getApplicationContext(), R.anim.push_down);

        CardView profile_card = view.findViewById(R.id.profile_card);
        profile_card.setAnimation(animation);

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

        tv_cardholder.setText(et_cardholder.getText().toString());
        tv_exp_month.setText(String.valueOf(et_expiry_month));
        tv_exp_year.setText(String.valueOf(et_expiry_year));
        tv_cvv.setText(String.valueOf(et_cvv));

        // Save cardNumber in DB
        cardNumber = digits1 + digits2 + digits3 + digits4;
    }
}