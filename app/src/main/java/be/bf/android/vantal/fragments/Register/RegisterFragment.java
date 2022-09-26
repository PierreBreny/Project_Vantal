package be.bf.android.vantal.fragments.Register;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;

import be.bf.android.vantal.R;

public class RegisterFragment extends Fragment {

    private TextView skip, tv_sign_in, tv_birthdate;
    private Button register_btn;
    private NavController navController;
    private TextInputLayout birthdate_field;
    

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        skip = view.findViewById(R.id.skip);
        skip.setOnClickListener(this::skipToHome);
        
        birthdate_field = view.findViewById(R.id.et_birthdate);
        birthdate_field.setEndIconOnClickListener(this::openDatePicker);

        tv_birthdate = view.findViewById(R.id.tv_birthdate);

        navController = NavHostFragment.findNavController(this);

        register_btn = view.findViewById(R.id.register_btn);
        register_btn.setOnClickListener(this::saveUser);

        tv_sign_in = view.findViewById(R.id.tv_sign_in);
        tv_sign_in.setOnClickListener(this::goToLogin);


        return view;
    }

    private void openDatePicker(View view) {

        // create instance of the material date picker
        MaterialDatePicker.Builder dateBuilder = MaterialDatePicker.Builder.datePicker();

        // define the properties of the materialDateBuilder
        dateBuilder.setTitleText("Select a Birthdate");

        // create instance of date picker
        final MaterialDatePicker datePicker = dateBuilder.build();

        datePicker.show(getChildFragmentManager(), "MATERIAL_DATE_PICKER");

        // now handle the positive button click
        datePicker.addOnPositiveButtonClickListener(
                selection -> {
                    // set selected date
                    tv_birthdate.setText(datePicker.getHeaderText());
                });

    }

    private void goToLogin(View view) {
        navController.navigate(R.id.action_registerFragment_to_login_fragment);
    }

    private void saveUser(View view) {
        // Save user in DB
        Toast.makeText(getContext(), "Welcome to Vantal, user!", Toast.LENGTH_SHORT).show();
        // Redirects to home
        navController.navigate(R.id.action_registerFragment_to_homeFragment);
    }

    private void skipToHome(View view) {
        navController.navigate(R.id.action_registerFragment_to_homeFragment);
    }
}