package be.bf.android.vantal.fragments.Details;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import be.bf.android.vantal.R;
import be.bf.android.vantal.api.VanAPI;
import be.bf.android.vantal.api.dto.Van;
import be.bf.android.vantal.databinding.FragmentDetailsBinding;
import be.bf.android.vantal.databinding.FragmentHomeBinding;

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
    private VanAPI api;
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
        Log.d("DetailFrag", amenities.toString());
        amenitiesAdapter = new AmenitiesAdapter(amenities, requireContext());
        rvAmenities.setAdapter(amenitiesAdapter);

        firstname_owner = view.findViewById(R.id.tv_owner_firstname);
        lastname_owner = view.findViewById(R.id.tv_owner_lastname);

        firstname_owner.setText(van.getUser().getFirstName());
        lastname_owner.setText(van.getUser().getLastName());


        return view;
    }

    private void logUser(View view) {
        // Get ID of logged User
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        Integer userID = sharedPreferences.getInt(value, 0);

        Log.d("AccountFrag", String.valueOf(userID));

        if (userID <= 0) {
            navController.navigate(R.id.login_fragment);
        }
    }
}