package fr.nikho.epitech.intra.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import fr.nikho.epitech.intra.data.AvailableLanguage;
import fr.nikho.epitech.intra.R;
import fr.nikho.epitech.intra.services.LanguageService;
import info.androidhive.fontawesome.FontTextView;

public class LanguageSettingsActivity extends AppCompatActivity {

    private TextView currentLanguageTextView;
    private FontTextView currentLanguageImage;

    private CardView frenchCard, englishCard, chinaCard;

    private AvailableLanguage currentLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_IntraEpitechAndroid_DIP);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_settings);
        init();
    }

    private void changeLanguage(View view, AvailableLanguage availableLanguage) {
        LanguageService.saveCurrentLanguage(this, availableLanguage);
        Toast.makeText(this, "App need to be restart", Toast.LENGTH_LONG).show();
        Locale locale = new Locale(availableLanguage.getCode());
        Locale.setDefault(locale);
        Resources resources = this.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

    private void init() {
        currentLanguage = LanguageService.getCurrentLanguage(this);

        frenchCard = findViewById(R.id.language_settings_french_card);
        englishCard = findViewById(R.id.language_settings_english_card);
        chinaCard = findViewById(R.id.language_settings_china_card);
        currentLanguageTextView = findViewById(R.id.language_settings_current_textview);
        currentLanguageImage = findViewById(R.id.language_settings_current_fonttextview);

        frenchCard.setOnClickListener(v -> changeLanguage(v, AvailableLanguage.FRENCH));
        englishCard.setOnClickListener(v -> changeLanguage(v, AvailableLanguage.ENGLISH));
        chinaCard.setOnClickListener(v -> changeLanguage(v, AvailableLanguage.CHINA));

        currentLanguageImage.setBackgroundResource(currentLanguage.getDrawableResource());
        currentLanguageTextView.setText(AvailableLanguage.getCurrentSetence(currentLanguage));
    }
}