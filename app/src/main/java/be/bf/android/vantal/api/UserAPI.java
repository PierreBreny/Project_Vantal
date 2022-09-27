package be.bf.android.vantal.api;

import java.util.List;

import be.bf.android.vantal.api.dto.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserAPI {

    @GET("user")
    Call<List<User>> getUser(@Query("email") String email, @Query("password") String password);

    @POST("user")
    Call<User> createUser(@Body User user);

}
