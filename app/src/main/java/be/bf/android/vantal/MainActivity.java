package be.bf.android.vantal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.UUID;

import be.bf.android.vantal.helper.LocationHelper;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomTab;
    private FragmentContainerView fragmentContainerView;
    NavController navController;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Request no title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        String id = UUID.randomUUID().toString();

        setContentView(R.layout.activity_main);

        bottomTab = findViewById(R.id.bottom_navigation);
        fragmentContainerView = findViewById(R.id.fragmentContainerView);

        // Check if app is installed for the first time
        SharedPreferences preferences = getSharedPreferences("PREFERENCES", MODE_PRIVATE);
        String FirstTime = preferences.getString("First_Time_Install", "");
        Log.d("MainActivity", FirstTime);

        if (FirstTime.equals("")) {

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("First_Time_Install", "No");
            editor.apply();

            // If app open for the first time
            Intent intent = new Intent(this, PopActivity.class);
            startActivity(intent);

        } else {

            NavHostFragment navHostFragment =
                    (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
            navController = navHostFragment.getNavController();

            NavigationUI.setupWithNavController(bottomTab, navController);
        }

    }


}