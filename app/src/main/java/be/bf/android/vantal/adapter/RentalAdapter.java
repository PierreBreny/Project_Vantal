package be.bf.android.vantal.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import be.bf.android.vantal.R;

public class RentalAdapter extends RecyclerView.Adapter<RentalAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView rental_image;
        public TextView rental_city;
        public TextView rental_country;
        public TextView start_date;
        public TextView end_date;
        public TextView nbr_nights;
        public TextView total_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rental_image = itemView.findViewById(R.id.iv_rental_image);
            rental_city = itemView.findViewById(R.id.tv_rental_city);
            rental_country = itemView.findViewById(R.id.tv_rental_country);
            start_date = itemView.findViewById(R.id.tv_start_date);
            end_date = itemView.findViewById(R.id.tv_end_date);
            nbr_nights = itemView.findViewById(R.id.tv_nbr_nights);
            total_price = itemView.findViewById(R.id.tv_total_price);
        }
    }

    @NonNull
    @Override
    public RentalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RentalAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
