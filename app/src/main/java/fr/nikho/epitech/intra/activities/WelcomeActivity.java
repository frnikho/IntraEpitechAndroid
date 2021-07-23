package fr.nikho.epitech.intra.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Arrays;

import fr.nikho.epitech.intra.R;
import fr.nikho.epitech.intra.data.User;
import fr.nikho.epitech.intra.EpitechClient;
import fr.nikho.epitech.intra.services.ClientService;
import fr.nikho.epitech.intra.services.UserService;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WelcomeActivity extends Activity {

    private TextView Link = null;
    private TextView LinkField = null;
    private Button Button = null;
    private SharedPreferences prefs;

    private String[] allowedUrl = new String[]{
            "http://intra.epitech.eu/auth-",
            "https://intra.epitech.eu/auth-",
            "http://www.intra.epitech.eu/auth-",
            "https://www.intra.epitech.eu/auth-"
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        prefs = getSharedPreferences("global", Context.MODE_PRIVATE);
        Link = findViewById(R.id.intra_link);
        LinkField = findViewById(R.id.generated_link_field);
        Button = findViewById(R.id.button);
        Link.setOnClickListener(v -> {
            Intent web = new Intent(Intent.ACTION_VIEW);
            web.setData(Uri.parse(getString(R.string.intra_autologin_link)));
            startActivity(web);
        });
        Button.setOnClickListener(v -> {
            onSubmit(LinkField.getText().toString());
        });

        LinkField.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_GO) {
                onSubmit(v.getText().toString());
            }
            System.out.println(v.getText().toString());
            return false;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (intent == null)
            return;
        Uri data = intent.getData();
        if (data == null)
            return;
        LinkField.setText(data.toString() + "/");
    }

    private void onSubmit(String baseUrl) {
        if (baseUrl.isEmpty() || Arrays.stream(allowedUrl).noneMatch(baseUrl::startsWith)) {
            Toast.makeText(this, "Bad Auth url !", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!baseUrl.endsWith("/"))
            baseUrl += "/";
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit client = builder.build();
        EpitechClient intra = client.create(EpitechClient.class);
        ClientService.setLoginLink(this, baseUrl);
        intra.getAuthUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull User user) {
                        UserService.setUser(user);
                        ClientService.setEmail(getApplicationContext(), user.getInternalEmail());
                        Toast.makeText(WelcomeActivity.this, "" + user.getTitle(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(WelcomeActivity.this, "" + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Link = null;
        UserService.save(this);
    }
}
