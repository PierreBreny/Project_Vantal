package be.bf.android.vantal.api;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static class RequestInterceptor implements Interceptor {
        @NonNull
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            // Je fais quelque chose avant toutes mes requêtes
            return chain.proceed(request);
        }
    }

    private static String TAG = "RetrofitClient";

    private static String BASE_URL = "http://172.26.23.246:3000/";

    private static OkHttpClient okHttpClient = new OkHttpClient()
            .newBuilder()
            .addInterceptor(new RequestInterceptor())
            .build();

    public static Retrofit client = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
