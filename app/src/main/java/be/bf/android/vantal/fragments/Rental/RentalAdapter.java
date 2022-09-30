package be.bf.android.vantal.fragments.Rental;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import be.bf.android.vantal.R;
import be.bf.android.vantal.api.dto.Rental;

public class RentalAdapter extends RecyclerView.Adapter<RentalAdapter.ViewHolder> {

    private List<Rental> rentals;
    private Context context;
    private OnRentalItemClick onRentalItemClick;

    public interface OnRentalItemClick {
        void onRentalItemClick(Rental rental);
    }

    public RentalAdapter(Context context, List<Rental> rentals, OnRentalItemClick onRentalItemClick){
        this.context = context;
        this.rentals = rentals;
        this.onRentalItemClick = onRentalItemClick;
    }

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rental_layout_item, parent, false);
        return new RentalAdapter.ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull RentalAdapter.ViewHolder holder, int position) {

        Rental currentRental = rentals.get(position);

        ImageView imgRental = holder.rental_image;
        Glide.with(context).load(currentRental.getVan().getImages().get(0)).centerCrop().into(imgRental);

        TextView cityRental = holder.rental_city;
        cityRental.setText(currentRental.getVan().getCity());

        TextView countryRental = holder.rental_country;
        countryRental.setText(currentRental.getVan().getCountry());

        TextView startRental = holder.start_date;
        startRental.setText(String.valueOf(currentRental.getStartDate()));

        TextView endRental = holder.end_date;
        endRental.setText(currentRental.getEndDate());


        // Get number of nights between two dates (String: "dd/MM/yyyy")
        TextView nightRental = holder.nbr_nights;
        String startDate = currentRental.getStartDate();
        String endDate = currentRental.getEndDate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate startLocalDate = LocalDate.parse(startDate, formatter);
        LocalDate endLocalDate = LocalDate.parse(endDate, formatter);


        long nights = ChronoUnit.DAYS.between(startLocalDate, endLocalDate);
        nightRental.setText(String.valueOf(nights));

        TextView priceRental = holder.total_price;

        int price = currentRental.getVan().getPrice();
        Log.d("Test", String.valueOf(nights));


        priceRental.setText(String.valueOf(nights * price));

    }

    @Override
    public int getItemCount() {
        return rentals.size();
    }

    public void setRental(List<Rental> newRentals){
        this.rentals = newRentals;
        notifyDataSetChanged();

    }
}
