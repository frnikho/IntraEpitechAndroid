package fr.nikho.epitech.intra.services;

import android.content.Context;
import android.content.SharedPreferences;

import fr.nikho.epitech.intra.data.AvailableLanguage;

public class LanguageService {

    public static AvailableLanguage getCurrentLanguage(Context context) {
        return AvailableLanguage.getByName(getCurrentLanguageName(context));
    }

    public static String getCurrentLanguageName(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("language", Context.MODE_PRIVATE);
        return prefs.getString("current", "english");
    }

    public static boolean saveCurrentLanguage(Context context, AvailableLanguage language) {
        try {
            SharedPreferences.Editor editor = context.getSharedPreferences("language", Context.MODE_PRIVATE).edit();
            editor.putString("current", language.getName());
            editor.apply();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean saveCurrentLanguage(Context context, String languageName) {
        try {
            AvailableLanguage language = AvailableLanguage.getByName(languageName);
            SharedPreferences.Editor editor = context.getSharedPreferences("language", Context.MODE_PRIVATE).edit();
            editor.putString("current", language.getName());
            editor.apply();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
