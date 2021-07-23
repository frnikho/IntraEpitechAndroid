package fr.nikho.epitech.intra.services;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.concurrent.TimeUnit;

import fr.nikho.epitech.intra.EpitechClient;
import fr.nikho.epitech.intra.utils.AddCookiesInterceptor;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientService {

    private static EpitechClient client = null;
    private static String authUrl = null;
    private static String email = null;

    public static EpitechClient getClient() {
        return client;
    }

    public static String getAuthUrl() {
        return authUrl;
    }

    public static String getEmail() {
        return email;
    }

    public static String getLoginLink(Context context) {
        if (authUrl != null)
            return authUrl;
        SharedPreferences prefs = context.getSharedPreferences("global", Context.MODE_PRIVATE);
        String tmp = prefs.getString("AUTOLOGIN_LINK", "null");
        String tmpEmail = prefs.getString("EMAIL", "null");
        if (tmp.equalsIgnoreCase("null") || tmpEmail.equalsIgnoreCase("null"))
            return null;
        authUrl = tmp;
        email = tmpEmail;
        return authUrl;
    }

    public static boolean setEmail(Context context, String email) {
        try {
            SharedPreferences.Editor editor = context.getSharedPreferences("global", Context.MODE_PRIVATE).edit();
            editor.putString("EMAIL", email);
            editor.apply();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public static boolean setLoginLink(Context context, String loginLink) {
        try {
            SharedPreferences.Editor editor = context.getSharedPreferences("global", Context.MODE_PRIVATE).edit();
            editor.putString("AUTOLOGIN_LINK", loginLink);
            editor.apply();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    private Retrofit createApi(String baseUrl, Context context) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new AddCookiesInterceptor(context))
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create());
        return builder.build();
    }

    public static EpitechClient createClient(String link, Context context) {
        return new ClientService().createApi(link, context).create(EpitechClient.class);
    }

    public static EpitechClient createClient(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("global", Context.MODE_PRIVATE);
        String link = prefs.getString("AUTOLOGIN_LINK", "null");
        String tmpEmail = prefs.getString("EMAIL", "null");
        if (link.equals("null"))
            return null;
        authUrl = link;
        email = tmpEmail;
        return new ClientService().createApi(link, context).create(EpitechClient.class);
    }

    public static void reset(Context context) {
        client = null;
        SharedPreferences.Editor editor = context.getSharedPreferences("global", Context.MODE_PRIVATE).edit();
        editor.putString("AUTOLOGIN_LINK", "null");
        editor.putString("EMAIL", "null");
        editor.apply();
    }

}
