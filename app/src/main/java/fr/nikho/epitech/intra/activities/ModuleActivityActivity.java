package fr.nikho.epitech.intra.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import fr.nikho.epitech.intra.EpitechClient;
import fr.nikho.epitech.intra.R;
import fr.nikho.epitech.intra.data.Activity;
import fr.nikho.epitech.intra.services.ClientService;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ModuleActivityActivity extends AppCompatActivity {

    EpitechClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_activity);

        client = ClientService.createClient(this);
        if (client == null) {
            startActivity(new Intent(this, WelcomeActivity.class));
            return;
        }

        Intent intent = getIntent();
        if (intent == null) {
            invalidIntentData();
            return;
        }
        String[] data = intent.getStringArrayExtra("data");
        if (data.length < 4) {
            invalidIntentData();
            return;
        }
        init(data);
    }

    private void invalidIntentData() {
        Toast.makeText(getApplicationContext(), "Invalid module, module year, code instance or code activity", Toast.LENGTH_LONG).show();
        finish();
    }

    private void init(String[] data) {
        client.getActivity(data[0], data[1], data[2], data[3])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Activity>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Activity activity) {
                        Log.e("activity test", "oui la dot");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        invalidIntentData();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}