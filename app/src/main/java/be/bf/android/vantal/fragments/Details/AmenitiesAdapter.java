package be.bf.android.vantal.fragments.Details;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import be.bf.android.vantal.R;
import be.bf.android.vantal.helper.AmenitiesHelper;

public class AmenitiesAdapter extends RecyclerView.Adapter<AmenitiesAdapter.ViewHolder> {

    private List<String> amenities;
    private Context context;

    public AmenitiesAdapter(List<String> amenities, Context context) {
        Log.d("Adapter", amenities.toString());
        this.amenities = amenities;
        this.context = context;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView amenity_image;
        public TextView amenity_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            amenity_image = itemView.findViewById(R.id.amenity_img);
            amenity_name = itemView.findViewById(R.id.tv_amenity_name);
        }
    }

    @NonNull
    @Override
    public AmenitiesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.amenities_layout_item, parent, false);
        return new AmenitiesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AmenitiesAdapter.ViewHolder holder, int position) {

        String amenity = amenities.get(position);


        String amenityText = AmenitiesHelper.getAmenitiesString(amenity);
        int amenityRessource = AmenitiesHelper.getAmenitiesRessource(amenity);

        holder.amenity_image.setImageResource(amenityRessource);
        holder.amenity_name.setText(amenityText);

    }

    @Override
    public int getItemCount() {
        return this.amenities.size();
    }
}
