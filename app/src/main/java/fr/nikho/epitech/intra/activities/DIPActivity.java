package fr.nikho.epitech.intra.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

import java.util.Random;

import fr.nikho.epitech.intra.R;

public class DIPActivity extends AppCompatActivity {

    private final int[] animations = {R.raw.astronaut_light_theme, R.raw.error_not_found,
            R.raw.maintenance, R.raw.maintenance_web, R.raw.maintenance_cyber_security, R.raw.network,
            R.raw.no_data_error, R.raw.page, R.raw.questioning, R.raw.under_construction, R.raw.under_maintenance};

    private LottieAnimationView animationView;
    private String dipText;
    private TextView dipTextView, dipTitle;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_IntraEpitechAndroid_DIP);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dip);
        init();

        Intent intent = getIntent();
        if (intent == null)
            return;
        Bundle bundle = intent.getExtras();
        if (bundle == null)
            return;
        dipText = bundle.getString("dip_text");
    }

    private void init() {
        animationView = findViewById(R.id.dip_animation);
        dipTextView = findViewById(R.id.dip_text);
        dipTitle = findViewById(R.id.dip_title);
        swipeRefreshLayout = findViewById(R.id.dip_refresh_view);

        dipText = "Epitech - Operation services, The server returned an invalid or incomplete response.";
        dipTextView.setText(dipText);

        dipTitle.setText("Deployment in progress");

        int animationId = new Random().nextInt(animations.length);
        animationId = animations[animationId];
        animationView.setAnimation(animationId);
        animationView.setRepeatCount(LottieDrawable.INFINITE);
        animationView.playAnimation();
        animationView.setScale(0.2f);

        swipeRefreshLayout.setOnRefreshListener(this::refresh);
    }

    private void refresh() {
        int animationId = new Random().nextInt(animations.length);
        animationId = animations[animationId];
        animationView.setAnimation(animationId);
        animationView.setRepeatCount(LottieDrawable.INFINITE);
        animationView.playAnimation();
        swipeRefreshLayout.setRefreshing(false);
    }
}