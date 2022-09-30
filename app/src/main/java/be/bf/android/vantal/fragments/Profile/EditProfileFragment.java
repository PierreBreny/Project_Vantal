package be.bf.android.vantal.fragments.Profile;

import android.content.SharedPreferences;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.transition.Scene;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import be.bf.android.vantal.R;
import be.bf.android.vantal.api.RetrofitClient;
import be.bf.android.vantal.api.UserAPI;
import be.bf.android.vantal.api.dto.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileFragment extends Fragment {
    private Button cancel_btn;
    private NavController navController;
    private ImageView back_arrow, profile_image;
    private String value = "key";
    private EditText et_firstname, et_lastname, et_birthdate, et_phone, et_address, et_postcode, et_city, et_country, et_about;
    private UserAPI userAPI;
    SharedPreferences sharedPreferences;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    public static EditProfileFragment newInstance(String param1, String param2) {
        EditProfileFragment fragment = new EditProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        navController = NavHostFragment.findNavController(this);

        userAPI = RetrofitClient.client.create(UserAPI.class);

        cancel_btn = view.findViewById(R.id.btn_cancel);
        cancel_btn.setOnClickListener(this::clickCancel);

        back_arrow = view.findViewById(R.id.backArrow);
        back_arrow.setOnClickListener(this::goBack);

        profile_image = view.findViewById(R.id.profile_image);
        et_firstname = view.findViewById(R.id.et_firstname_value);
        et_lastname = view.findViewById(R.id.et_lastname_value);
        et_birthdate = view.findViewById(R.id.et_birthdate_value);
        et_phone = view.findViewById(R.id.et_phone_value);
        et_address = view.findViewById(R.id.et_address_value);
        et_postcode = view.findViewById(R.id.et_postcode_value);
        et_city = view.findViewById(R.id.et_postcode_value);
        et_country = view.findViewById(R.id.et_country_value);
        et_about = view.findViewById(R.id.et_about_value);

        loadUserData();

        return view;
    }

    private void loadUserData() {
        // Get ID of logged User
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        Integer userID = sharedPreferences.getInt(value, 0);

        // Get User by ID from API
        userAPI.getUserByID(userID).enqueue(new Callback<List<User>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.body() != null) {
                    List<User> userList = response.body();

                    User user = userList.get(0);

                    Log.d("EditProfileFrag", String.valueOf(user));
                    Glide.with(requireContext()).load(user.getImage()).centerCrop().into(profile_image);
                    et_firstname.setText(user.getFirstName());
                    et_lastname.setText(user.getLastName());
                    et_birthdate.setText(user.getBirthdate());
                    et_birthdate.setText(user.getBirthdate());
                    et_address.setText("");
                    et_postcode.setText("");
                    et_city.setText("");
                    et_country.setText("");

                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("LoginFrag", "Cannot check user");
            }
        });
    }


    private void goBack(View view) {
        navController.navigate(R.id.action_editProfileFragment_to_profileFragment);
    }

    private void clickCancel(View view) {
        navController.navigate(R.id.action_editProfileFragment_to_profileFragment);
    }
}