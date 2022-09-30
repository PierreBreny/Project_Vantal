package be.bf.android.vantal.fragments.Booking;

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
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import be.bf.android.vantal.R;
import be.bf.android.vantal.api.RentalAPI;
import be.bf.android.vantal.api.RetrofitClient;
import be.bf.android.vantal.api.dto.Rental;
import be.bf.android.vantal.api.dto.User;
import be.bf.android.vantal.api.dto.Van;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BookingFragment extends Fragment {

    private Button confirm_booking;
    private EditText nbr_guest;
    private NavController navController;
    private TextInputLayout startDate_field;
    private TextInputLayout endDate_field;
    private EditText startDate_value, endDate_value;
    private ImageView rental_img;
    private RentalAPI rentalAPI;
    private String value = "key";
    SharedPreferences sharedPreferences;


    public BookingFragment() {
        // Required empty public constructor
    }


    public static BookingFragment newInstance(String param1, String param2) {
        BookingFragment fragment = new BookingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booking, container, false);

        rentalAPI = RetrofitClient.client.create(RentalAPI.class);

        navController = NavHostFragment.findNavController(this);

        rental_img = view.findViewById(R.id.iv_rental_img);

        startDate_field = view.findViewById(R.id.et_startDate);
        endDate_field = view.findViewById(R.id.et_endDate);

        startDate_value = view.findViewById(R.id.et_startDate_value);
        endDate_value = view.findViewById(R.id.et_endDate_value);

        nbr_guest = view.findViewById(R.id.et_nbr_guest_value);

        confirm_booking = view.findViewById(R.id.confirm_btn);
        confirm_booking.setOnClickListener(this::saveRental);

        startDate_field.setEndIconOnClickListener(this::pickStartDate);
        endDate_field.setEndIconOnClickListener(this::pickEndDate);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        //Get van image
        Van van = (Van) getArguments().getSerializable("van");
        Log.d("BookingFrag", van.toString());

        Glide.with(requireContext()).load(van.getImages().get(0)).centerCrop().into(rental_img);



        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void pickEndDate(View view) {
        // create instance of the material date picker
        MaterialDatePicker.Builder dateBuilder = MaterialDatePicker.Builder.datePicker();

        // define the properties of the materialDateBuilder
        dateBuilder.setTitleText("Start Date");

        // create instance of date picker
        final MaterialDatePicker datePicker = dateBuilder.build();

        datePicker.show(getChildFragmentManager(), "MATERIAL_DATE_PICKER");

        // now handle the positive button click
        datePicker.addOnPositiveButtonClickListener(
                selection -> {
                    // set selected date
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
                    endDate_value.setText(LocalDate.parse(datePicker.getHeaderText(), formatter).toString());
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void pickStartDate(View view) {
        // create instance of the material date picker
        MaterialDatePicker.Builder dateBuilder = MaterialDatePicker.Builder.datePicker();

        // define the properties of the materialDateBuilder
        dateBuilder.setTitleText("Start Date");

        // create instance of date picker
        final MaterialDatePicker datePicker = dateBuilder.build();

        datePicker.show(getChildFragmentManager(), "MATERIAL_DATE_PICKER");

        // now handle the positive button click
        datePicker.addOnPositiveButtonClickListener(
                selection -> {
                    // set selected date
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");

                    startDate_value.setText(LocalDate.parse(datePicker.getHeaderText(), formatter).toString());
                });
    }

    private void saveRental(View view) {

        Van van = (Van) getArguments().getSerializable("van");
        int vanId = van.getId();
        String startDate = startDate_value.getText().toString();
        String endDate = endDate_value.getText().toString();
        Integer userID = sharedPreferences.getInt(value, 0);


        Rental rental = new Rental(vanId,endDate, userID, startDate, van);

        rentalAPI.createRental(rental).enqueue(new Callback<Rental>() {
            @Override
            public void onResponse(Call<Rental> call, Response<Rental> response) {
                Rental responseFromApi = response.body();
                navController.navigate(R.id.action_booking_fragment_to_rentalFragment);
            }

            @Override
            public void onFailure(Call<Rental> call, Throwable t) {
                Log.d("RegisterFrag", "An error occured with creating the booking");
            }
        });

    }

}