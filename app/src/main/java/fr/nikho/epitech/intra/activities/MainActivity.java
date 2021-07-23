package fr.nikho.epitech.intra.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import fr.nikho.epitech.intra.R;
import fr.nikho.epitech.intra.EpitechClient;
import fr.nikho.epitech.intra.data.User;
import fr.nikho.epitech.intra.services.ClientService;
import fr.nikho.epitech.intra.services.UserService;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); //TODO remove this for the dark mode

        init();
        prefs = getSharedPreferences("global", Context.MODE_PRIVATE);
        String baseUrl = prefs.getString("AUTOLOGIN_LINK", "null");
        if (baseUrl.equals("null")) {
            startActivity(new Intent(this, WelcomeActivity.class));
        } else {

            //startService(new Intent(this, MessageService.class));

            checkUserInfo(baseUrl);

           /* WorkRequest notificationRequest = new PeriodicWorkRequest.Builder(
                    NotificationWorker.class,
                    5,
                    TimeUnit.MINUTES
            ).setInputData(new Data.Builder().putString("auth_url", ClientService.getLoginLink(this))
                    .build()).build();
            WorkManager.getInstance(this).enqueue(notificationRequest);*/


        }
    }

    private void init() {



        UserService.load(this);
    }

    public void checkUserInfo(String baseUrl) {
        EpitechClient client = ClientService.createClient(baseUrl, this);
        client.getAuthUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<User>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull User user) {
                UserService.setUser(user);
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println(e.getMessage());
                Toast.makeText(MainActivity.this, "Can't get user info !", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, DIPActivity.class);
                startActivity(intent);
            }

            @Override
            public void onComplete() {
            }
        });
    }
}