package fr.nikho.epitech.intra.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import fr.nikho.epitech.intra.R;

public class NewHomeActivity extends AppCompatActivity {

    BottomNavigationView nav;
    FragmentContainerView fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity_home);

        init();
    }

    private void init() {
        nav = findViewById(R.id.home_bottom_nav);
        fragment = findViewById(R.id.home_fragment_container);
    }
}
