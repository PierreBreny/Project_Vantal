package be.bf.android.vantal.fragments.Rental;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import be.bf.android.vantal.R;
import be.bf.android.vantal.api.RentalAPI;
import be.bf.android.vantal.api.RetrofitClient;
import be.bf.android.vantal.api.VanAPI;
import be.bf.android.vantal.api.dto.Rental;
import be.bf.android.vantal.databinding.FragmentUpcomingRentalBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpcomingFragment extends Fragment implements RentalAdapter.OnRentalItemClick {

    private FragmentUpcomingRentalBinding binding;
    private RentalAdapter adapter;
    private VanAPI vanAPI;
    private RentalAPI rentalAPI;
    private NavController navController;
    SharedPreferences sharedPreferences;
    private String value = "key";

    public UpcomingFragment() {
        // Required empty public constructor
    }

    public static UpcomingFragment newInstance(String param1, String param2) {
        UpcomingFragment fragment = new UpcomingFragment();
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
        binding = FragmentUpcomingRentalBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        navController = NavHostFragment.findNavController(this);

        adapter = new RentalAdapter(requireContext(), new ArrayList<>(), this);
        binding.rvUpcomingRentals.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        binding.rvUpcomingRentals.setAdapter(adapter);

        vanAPI = RetrofitClient.client.create(VanAPI.class);
        rentalAPI = RetrofitClient.client.create(RentalAPI.class);

        // Get ID of logged User
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        Integer userID = sharedPreferences.getInt(value, 0);

        if (userID <= 0) {
            navController.navigate(R.id.popFragment);
        }

        getRentals(userID);

        return view;
    }

    private void getRentals(Integer userID) {
        rentalAPI.getRentalByUser(userID, "van").enqueue(new Callback<List<Rental>>() {
            @Override
            public void onResponse(Call<List<Rental>> call, Response<List<Rental>> response) {

                if (response.body() != null) {
                    List<Rental> rentals = response.body();

                    adapter.setRental(rentals);
                }
            }

            @Override
            public void onFailure(Call<List<Rental>> call, Throwable t) {
                Log.d("UpcomingFrag", "Oops");
            }
        });
    }

    @Override
    public void onRentalItemClick(Rental rental) {
    }
}