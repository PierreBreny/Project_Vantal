package be.bf.android.vantal.fragments.Profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import be.bf.android.vantal.R;
import be.bf.android.vantal.api.RetrofitClient;
import be.bf.android.vantal.api.UserAPI;
import be.bf.android.vantal.api.dto.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    private CardView edit_profile_card;
    private NavController navController;
    private ImageView back_arrow;
    private CardView delete_account_card;
    private CardView logout_card;
    private ImageView profile_image;
    private TextView firstname;
    AlertDialog.Builder builder;
    private String value = "key";
    SharedPreferences sharedPreferences;
    private UserAPI userAPI;

    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(getContext());
        Integer userID = sharedPreferences.getInt(value, 0);

        navController = NavHostFragment.findNavController(this);

        edit_profile_card = view.findViewById(R.id.edit_profile_card);
        edit_profile_card.setOnClickListener(this::editProfile);

        profile_image = view.findViewById(R.id.iv_profile_image);
        firstname = view.findViewById(R.id.tv_firstname);


        back_arrow = view.findViewById(R.id.backArrow);
        back_arrow.setOnClickListener(this::goBack);

        delete_account_card = view.findViewById(R.id.delete_account_card);
        delete_account_card.setOnClickListener(this::showAlert);

        logout_card = view.findViewById(R.id.logout_card);
        logout_card.setOnClickListener(this::logOut);

        builder = new AlertDialog.Builder(getContext());

        userAPI = RetrofitClient.client.create(UserAPI.class);

        loadUserdata();

        return view;
    }

    private void loadUserdata() {
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
                    firstname.setText(user.getFirstName());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("LoginFrag", "Cannot check user");
            }
        });
    }

    private void logOut(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("key", 0);
        editor.apply();
        navController.navigate(R.id.action_profileFragment_to_accountFragment);
    }

    private void showAlert(View view) {
        builder.setTitle("Delete Account")
                .setMessage("Are you sure? Once you delete your account, there is no getting it back.")
                .setCancelable(true)
                .setPositiveButton("Yes", (dialog, which) -> deleteAccount())
                .setNegativeButton("No", (dialog, which) -> dialog.cancel())
                .show();
    }

    private void deleteAccount() {
        //delete acoount from DB
        //load sign in page
        navController.navigate(R.id.action_profileFragment_to_registerFragment);
        //show snackbar
        Toast.makeText(getContext(), "You successfully deleted your account", Toast.LENGTH_LONG).show();
    }

    private void goBack(View view) {
        navController.navigate(R.id.action_profileFragment_to_accountFragment);
    }

    private void editProfile(View view) {
        navController.navigate(R.id.action_profileFragment_to_editProfileFragment);
    }

}