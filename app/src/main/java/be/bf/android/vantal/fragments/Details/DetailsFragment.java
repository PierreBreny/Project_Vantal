package be.bf.android.vantal.fragments.Details;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import be.bf.android.vantal.api.dto.Van;
import be.bf.android.vantal.databinding.FragmentDetailsBinding;
import be.bf.android.vantal.databinding.FragmentHomeBinding;

public class DetailsFragment extends Fragment {

    ImageSlider imageSlider;
    TextView title;
    TextView description;
    TextView rating;
    TextView price;
    private NavController navController;



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

        return view;
    }
}