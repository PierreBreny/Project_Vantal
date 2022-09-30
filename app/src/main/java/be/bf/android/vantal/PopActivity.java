package be.bf.android.vantal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class PopActivity extends AppCompatActivity {

    private TextView skip;
    Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Request no title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        window = PopActivity.this.getWindow();

        window.setStatusBarColor(getResources().getColor(R.color.black));

        setContentView(R.layout.activity_pop);

        skip = findViewById(R.id.tv_pop_skip);
        skip.setOnClickListener(this::goToMainActivity);

    }

    private void goToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}