package be.bf.android.vantal.api;


import java.util.List;

import be.bf.android.vantal.api.dto.Rental;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RentalAPI {

    @GET("rentals")
    Call<List<Rental>> getRentalByUser(@Query("userId") int userId, @Query("_expand") String expand);

    @POST("rentals")
    Call<Rental> createRental(@Body Rental rental);
}
