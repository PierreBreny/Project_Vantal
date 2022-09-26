package be.bf.android.vantal.fragments.Login;

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

import be.bf.android.vantal.R;


public class LoginFragment extends Fragment {

    private TextView skip, tv_register;
    private Button login_btn;
    private NavController navController;

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

        navController = NavHostFragment.findNavController(this);

        skip = view.findViewById(R.id.skip);
        skip.setOnClickListener(this::skipToHome);

        login_btn = view.findViewById(R.id.login_btn);
        login_btn.setOnClickListener(this::goLogin);

        tv_register = view.findViewById(R.id.tv_register);
        tv_register.setOnClickListener(this::goToRegister);

        return view;
    }

    private void goToRegister(View view) {
        navController.navigate(R.id.action_login_fragment_to_registerFragment);
    }

    private void goLogin(View view) {
        // Log user
        Toast.makeText(getContext(), "Welcome back, user!", Toast.LENGTH_SHORT).show();
        navController.navigate(R.id.action_login_fragment_to_homeFragment);
    }

    private void skipToHome(View view) {
        navController.navigate(R.id.action_login_fragment_to_homeFragment);
    }
}