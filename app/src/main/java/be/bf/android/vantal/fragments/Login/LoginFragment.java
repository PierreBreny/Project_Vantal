package be.bf.android.vantal.fragments.Login;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import be.bf.android.vantal.R;
import be.bf.android.vantal.api.RetrofitClient;
import be.bf.android.vantal.api.UserAPI;
import be.bf.android.vantal.api.dto.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment {

    private Button login_btn;
    private NavController navController;
    private UserAPI userAPI;
    private EditText et_email, et_password;
    SharedPreferences sharedPreferences;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        userAPI = RetrofitClient.client.create(UserAPI.class);

        navController = NavHostFragment.findNavController(this);

        TextView skip = view.findViewById(R.id.skip);
        skip.setOnClickListener(this::skipToHome);

        login_btn = view.findViewById(R.id.login_btn);
        login_btn.setOnClickListener(this::logUser);

        TextView tv_register = view.findViewById(R.id.tv_register);
        tv_register.setOnClickListener(this::goToRegister);

        et_email = view.findViewById(R.id.login_email);
        et_password = view.findViewById(R.id.login_password);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        return view;
    }

    private void goToRegister(View view) {
        navController.navigate(R.id.action_login_fragment_to_registerFragment);
    }

    private void logUser(View view) {
        // Log user
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();
        userAPI.getUser(email, password).enqueue(new Callback<List<User>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.body() != null) {
                    List<User> userList = response.body();

                    User user = userList.get(0);

                    // Save userID in shared preference
                    int userid = user.getId();

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("key", userid);
                    editor.apply();

                    Log.d("LoginFrag", String.valueOf(user));
//                    navController.navigate(R.id.action_login_fragment_to_homeFragment);
                    navController.popBackStack();
                    Toast.makeText(getContext(), "Welcome back, "+user.getFirstName()+"!", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("LoginFrag", "Cannot check user");
            }
        });



    }
    private void skipToHome(View view) {
        navController.navigate(R.id.action_login_fragment_to_homeFragment);
    }

}