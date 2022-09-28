package be.bf.android.vantal.api;


import java.util.List;

import be.bf.android.vantal.api.dto.Rental;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RentalAPI {

    @GET("rental")
    Call<List<Rental>> getRentalByUser(@Query("userId") int userId);

    @POST("rental")
    Call<Rental> createRental(@Body Rental rental);
}
