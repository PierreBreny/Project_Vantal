package be.bf.android.vantal.fragments.Register;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import be.bf.android.vantal.R;
import be.bf.android.vantal.api.RetrofitClient;
import be.bf.android.vantal.api.UserAPI;
import be.bf.android.vantal.api.VanAPI;
import be.bf.android.vantal.api.dto.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {

    private TextView skip, tv_sign_in, tv_birthdate;
    private Button register_btn;
    private NavController navController;
    private TextInputLayout birthdate_field;
    private EditText firstname_value, lastname_value, email_value, password_value, birthdate_value;
    private UserAPI userAPI;
    

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

        firstname_value = view.findViewById(R.id.firstname_value);
        lastname_value = view.findViewById(R.id.lastname_value);
        email_value = view.findViewById(R.id.email_value);
        password_value = view.findViewById(R.id.password_value);
        birthdate_value = view.findViewById(R.id.birthdate_value);

        navController = NavHostFragment.findNavController(this);

        register_btn = view.findViewById(R.id.register_btn);
        register_btn.setOnClickListener(this::onClick);

        tv_sign_in = view.findViewById(R.id.tv_sign_in);
        tv_sign_in.setOnClickListener(this::goToLogin);

        userAPI = RetrofitClient.client.create(UserAPI.class);


        return view;
    }

    public void onClick(View view) {
        saveUser();
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
                    birthdate_value.setText(datePicker.getHeaderText());
                });

    }

    private void goToLogin(View view) {
        navController.navigate(R.id.action_registerFragment_to_login_fragment);
    }

    private void saveUser () {

        String firstname = firstname_value.getText().toString();
        String lastname = lastname_value.getText().toString();
        String email = email_value.getText().toString();
        String password = password_value.getText().toString();
        String birthdate = birthdate_value.getText().toString();

        User user = new User(firstname, lastname, password, birthdate, email);

        // Save user in DB
        userAPI.createUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                // this method is called when we get response from our api.
                Toast.makeText(getContext(), "Data added to API", Toast.LENGTH_SHORT).show();

                // we are getting response from our body
                // and passing it to our modal class.
                User responseFromAPI = response.body();
                Log.d("RegisterFrag", String.valueOf(responseFromAPI));
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("RegisterFrag", "An error occured with API");
            }
        });

        //Greet user
        String userName = firstname_value.getText().toString();
        Toast.makeText(getContext(), "Welcome to Vantal, "+ userName+"!", Toast.LENGTH_SHORT).show();
        // Redirects to home
        navController.navigate(R.id.action_registerFragment_to_homeFragment);
    }

    private void skipToHome(View view) {
        navController.navigate(R.id.action_registerFragment_to_homeFragment);
    }
}