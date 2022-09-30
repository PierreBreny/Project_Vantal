package be.bf.android.vantal.fragments.Details;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import be.bf.android.vantal.R;
import be.bf.android.vantal.api.RetrofitClient;
import be.bf.android.vantal.api.UserAPI;
import be.bf.android.vantal.api.dto.User;
import be.bf.android.vantal.api.dto.Van;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsFragment extends Fragment {

    private RecyclerView rvAmenities;
    ImageSlider imageSlider;
    TextView title;
    TextView description;
    TextView rating;
    TextView price;
    Button availability_btn;
    String value = "key";
    ImageView owner_img;
    TextView firstname_owner;
    TextView lastname_owner;
    private AmenitiesAdapter amenitiesAdapter;
    private UserAPI api;
    private NavController navController;
    SharedPreferences sharedPreferences;



    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment newInstance(String param1, String param2) {
        DetailsFragment fragment = new DetailsFragment();
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
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        navController = NavHostFragment.findNavController(this);

        imageSlider = (ImageSlider) view.findViewById(R.id.slider);

        availability_btn = view.findViewById(R.id.check_av_btn);
        availability_btn.setOnClickListener(this::logUser);

        ArrayList<SlideModel> slideModels = new ArrayList<>();

        api = RetrofitClient.client.create(UserAPI.class);

        //Get van images
        Van van = (Van) getArguments().getSerializable("van");

        //Put them in a List
        List<String> imageList = van.getImages();

        //Loop over List and add to slideModels
        for(int i = 0; i < imageList.size(); i++) {
            slideModels.add(new SlideModel(imageList.get(i), ScaleTypes.CENTER_CROP));
        }
        //Set ImageList
        imageSlider.setImageList(slideModels);

        // Get van title
        title = view.findViewById(R.id.van_title);
        title.setText(van.getTitle());

        //Get van description
        description = view.findViewById(R.id.van_description);
        description.setText(van.getDescription());

        //Get van price
        price = view.findViewById(R.id.price);
        price.setText(String.valueOf(van.getPrice()));

        //Get van rating
        rating = view.findViewById(R.id.rating);
        rating.setText(String.valueOf(van.getRating()));

        //Get van Amenities
        rvAmenities = view.findViewById(R.id.rv_amenities);
        rvAmenities.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        List<String> amenities = van.getAmenities();

        amenitiesAdapter = new AmenitiesAdapter(amenities, requireContext());
        rvAmenities.setAdapter(amenitiesAdapter);

        firstname_owner = view.findViewById(R.id.tv_owner_firstname);
        lastname_owner = view.findViewById(R.id.tv_owner_lastname);
        owner_img = view.findViewById(R.id.iv_profile_image);

        //GET OWNER INFO

        // 1. Get van userId
        int userid = van.getUserId();

        // 2. Get user with userId
        api.getUserByID(userid).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.body() != null) {
                    List<User> users = response.body();
                    User owner = users.get(0);
                    Log.d("DetailsFrag", owner.toString());
                    firstname_owner.setText(owner.getFirstName());
                    lastname_owner.setText(owner.getLastName());
                    Glide.with(requireContext()).load(owner.getImage()).centerCrop().into(owner_img);
                }
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("DetailsFrag", t.getMessage());
            }
        });


        return view;
    }

    private void logUser(View view) {
        // Get ID of logged User
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        Integer userID = sharedPreferences.getInt(value, 0);



        if (userID <= 0) {
            navController.navigate(R.id.login_fragment);
        } else {
            // 1. Get van
            Van van = (Van) getArguments().getSerializable("van");
            // 2. Send van in Bundle
            Bundle bundle = new Bundle();
            bundle.putSerializable("van", van);
            // 3. Go Booking
            navController.navigate(R.id.action_detailsFragment_to_booking_fragment, bundle);
        }
    }
}