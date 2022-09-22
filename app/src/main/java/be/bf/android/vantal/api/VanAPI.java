package be.bf.android.vantal.api;

import java.util.List;

import be.bf.android.vantal.api.dto.Van;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VanAPI {

    @GET("van")
    Call<List<Van>> getVan(@Query("city") String city);
}
