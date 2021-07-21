package fr.nikho.epitech.intra.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import fr.nikho.epitech.intra.R;
import fr.nikho.epitech.intra.services.ClientService;
import fr.nikho.epitech.intra.services.UserService;

public class SettingsActivity extends AppCompatActivity {

    private LinearLayout languageItem, logoutItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_IntraEpitechAndroid_DIP);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        init();
    }

    private void init() {
        languageItem = findViewById(R.id.settings_language_item);
        logoutItem = findViewById(R.id.settings_disconnect_item);

        languageItem.setOnClickListener(this::onLanguageItemClicked);
        logoutItem.setOnClickListener(this::onLogoutItemClicked);
    }

    private void onLanguageItemClicked(View view) {
        startActivity(new Intent(this, LanguageSettingsActivity.class));
    }

    private void onLogoutItemClicked(View view) {
        UserService.reset(this);
        ClientService.reset(this);
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }
}