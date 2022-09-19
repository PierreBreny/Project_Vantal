package be.bf.android.vantal.fragments.Map;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Map;

import be.bf.android.vantal.R;
import be.bf.android.vantal.api.RetrofitClient;
import be.bf.android.vantal.api.VanAPI;
import be.bf.android.vantal.api.dto.Van;
import be.bf.android.vantal.helper.LocationHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MapFragment extends Fragment {

    private GoogleMap mMap;

    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        LocationHelper.setActivity(requireActivity());
        LocationHelper.setContext(requireContext());
        LocationHelper.setLocationRequest();
        LocationHelper.setCallBack(map -> {
            Log.d("MapFrag", map.toString());
            for (Map.Entry<Double, Double> latlg : map.entrySet()) {
                mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(latlg.getKey(), latlg.getValue())));
            }

            return null;
        });

        View view = inflater.inflate(R.layout.fragment_map, container, false);

        //Initialize map fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        //Async map
        supportMapFragment.getMapAsync(googleMap -> {

            Log.d("Map", "ready");
            mMap = googleMap;
            LocationHelper.getCurrentLocation();

            // Add a marker in Sydney and move the camera
//            LatLng sydney = new LatLng(-34, 151);
//            mMap.addMarker(new MarkerOptions()
//                    .position(sydney)
//                    .title("Marker in Sydney"));

            //When map is loaded
//            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//                @Override
//                public void onMapClick(@NonNull LatLng latLng) {
//                    //Initialize marker position
//                    MarkerOptions markerOptions = new MarkerOptions();
//                    //Set position of marker
//                    markerOptions.position(latLng);
//                    markerOptions.title(latLng.latitude + " : " + latLng.longitude);
//
//                    googleMap.clear();
//                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
//
//                    //Add marker on map
//                    googleMap.addMarker(markerOptions);
//                }
//            });

        });
        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LocationHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LocationHelper.onActivityResult(requestCode, resultCode, data);
    }

}