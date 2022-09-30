package be.bf.android.vantal.fragments.Pop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import be.bf.android.vantal.R;

public class PopFragment extends Fragment {

    private TextView pop_exit;
    private Button register_btn;
    NavController navController;

    public PopFragment() {
        // Required empty public constructor
    }

    public static PopFragment newInstance(String param1, String param2) {
        PopFragment fragment = new PopFragment();

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
        View view = inflater.inflate(R.layout.fragment_pop, container, false);

        pop_exit = view.findViewById(R.id.tv_pop_skip);
        register_btn = view.findViewById(R.id.pop_register_btn);

        navController = NavHostFragment.findNavController(this);

        pop_exit.setOnClickListener(this::skipToHome);
        register_btn.setOnClickListener(this::goToRegister);

        return view;
    }

    private void goToRegister(View view) {
        navController.navigate(R.id.action_popFragment_to_registerFragment);
    }

    private void skipToHome(View view) {
        navController.navigate(R.id.action_popFragment_to_homeFragment);
    }
}