package be.bf.android.vantal.fragments.Home;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import be.bf.android.vantal.R;
import be.bf.android.vantal.adapter.VanAdapter;
import be.bf.android.vantal.api.RetrofitClient;
import be.bf.android.vantal.api.VanAPI;
import be.bf.android.vantal.api.dto.Van;
import be.bf.android.vantal.databinding.FragmentHomeBinding;
import be.bf.android.vantal.helper.LocationHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class HomeFragment extends Fragment {

    private TextInputLayout textInputLayout;
    private AutoCompleteTextView autoComplete;
    private FragmentHomeBinding binding;
    private VanAdapter adapter;
    private VanAPI api;
    String query = null;

    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        LocationHelper.setActivity(requireActivity());
        LocationHelper.setContext(requireContext());
        LocationHelper.setLocationRequest();
        LocationHelper.setCallBack(map -> {

            return null;
        });

        binding.searchBar.setEndIconOnClickListener(this::loadMap);

        adapter = new VanAdapter(requireContext(), new ArrayList<>());
        binding.rvVanHome.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        binding.rvVanHome.setAdapter(adapter);

        api = RetrofitClient.client.create(VanAPI.class);

        getVans(null);

        binding.autoComplete.setOnItemClickListener((parent, view1, position, id) -> {
            query = binding.autoComplete.getText().toString();
            getVans(query);
            closeKeyboard();
        });

        return view;
    }

    private void closeKeyboard() {
        InputMethodManager inputManager = (InputMethodManager)getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void getVans(String q) {
        api.getVan(q).enqueue(new Callback<List<Van>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Van>> call, Response<List<Van>> response) {
                if (response.body() != null) {
                    List<Van> vans = response.body();
                    // Autocomplete feature
                    if (q == null) {
                        List<String> vanCities = vans.stream().map(Van::getCity).collect(Collectors.toList());
                        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_activated_1, vanCities);
                        binding.autoComplete.setAdapter(cityAdapter);
                    }
                    adapter.setVans(vans);
                    binding.autoComplete.setText("");
                }
            }

            @Override
            public void onFailure(Call<List<Van>> call, Throwable t) {
                Log.d("HomeFrag", t.getMessage());
            }
        });
    }

    private void loadMap(View view) {
        NavHostFragment.findNavController(this).navigate(R.id.action_homeFragment_to_mapFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}