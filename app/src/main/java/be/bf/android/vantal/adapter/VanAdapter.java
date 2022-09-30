package be.bf.android.vantal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

import be.bf.android.vantal.R;
import be.bf.android.vantal.api.dto.Van;

public class VanAdapter extends RecyclerView.Adapter<VanAdapter.ViewHolder> {

    private List<Van> vans;
    private Context context;
    private OnVanItemClick onVanItemClick;

    public interface OnVanItemClick {
        void onVanItemClick(Van van);
    }

    public VanAdapter(Context context, List<Van> vans, OnVanItemClick onVanItemClick) {
        this.context = context;
        this.vans = vans;
        this.onVanItemClick = onVanItemClick;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageVan;
        public TextView titleVan;
        public TextView ratingVan;
        public TextView price;
        public TextView city;
        public TextView country;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageVan = itemView.findViewById(R.id.thumbnail);
            titleVan = itemView.findViewById(R.id.titleVan);
            ratingVan = itemView.findViewById(R.id.rating);
            price = itemView.findViewById(R.id.price);
            city = itemView.findViewById(R.id.city);
            country = itemView.findViewById(R.id.country);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Van currentVan = vans.get(position);

        ImageView imgVan = holder.imageVan;
        Glide.with(context).load(currentVan.getImages()
                .get(0)).centerCrop().into(imgVan);

        TextView title = holder.titleVan;
        title.setText(currentVan.getTitle());

        TextView rating = holder.ratingVan;
        rating.setText(String.valueOf(currentVan.getRating()));

        TextView price = holder.price;
        price.setText(String.valueOf(currentVan.getPrice()));

        TextView city = holder.city;
        city.setText(currentVan.getCity());

        TextView country = holder.country;
        country.setText(currentVan.getCountry());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onVanItemClick.onVanItemClick(currentVan);
            }
        });


    }

    @Override
    public int getItemCount() {
        return vans.size();
    }

    public void setVans(List<Van> newVans) {
        this.vans = newVans;
        notifyDataSetChanged();
    }

}
