package fr.nikho.epitech.intra.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import fr.nikho.epitech.intra.R;
import fr.nikho.epitech.intra.controllers.EpitechClient;
import fr.nikho.epitech.intra.data.Module;
import fr.nikho.epitech.intra.services.ClientService;
import fr.nikho.epitech.intra.utils.HubUtils;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HubActivity extends AppCompatActivity {

    private EpitechClient client;

    private TextView hubTalkTextView, hubWorkshopTextView;
    private LottieAnimationView hubTalkLoadingAnim;

    private int talkXp, talkMalusXp, talkMax = 0;

    private int workshopXp, workshopMalusXp, workshopMax = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);
        client = ClientService.createClient(this);
        initialize();
        sendRequest();
    }

    private void initialize() {
        hubTalkTextView = findViewById(R.id.hub_talk_textview);
        hubWorkshopTextView = findViewById(R.id.hub_workshop_textview);
        hubTalkLoadingAnim = findViewById(R.id.hub_talk_loading);
    }

    private void sendRequest() {
        client.getHubFRModule()
                .mergeWith(client.getHubModule())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Module>() {

                    private int next = 0;

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        hubTalkLoadingAnim.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onNext(@NonNull Module module) {
                        HubUtils hub = new HubUtils(module);
                        talkXp += hub.getUserPresentTalkAndMeetupXp();
                        talkMalusXp += hub.getUserAbsentTalkAndMeetupXp();

                        workshopXp += hub.getUserPresentWorkshopsXp();
                        workshopMalusXp += hub.getUserAbsentWorkshopXp();
                        next++;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (e.getMessage().equalsIgnoreCase("timeout")) {
                            //TODO DIP
                        }
                        System.out.println("ERROR" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        talkMax = HubUtils.TALK_PARTICIPATION_LIMIT;
                        workshopMax = HubUtils.WK_PARTICIPATION_LIMIT;
                        hubTalkLoadingAnim.pauseAnimation();
                        hubTalkLoadingAnim.setVisibility(View.GONE);
                        hubTalkTextView.setText("Talk xp: " + (talkXp - talkMalusXp) + " - (max: " + (talkMax) + " )\n" + "Workshop xp: " + (workshopXp - workshopMalusXp) + " - (max: " + workshopMax + " )");
                        hubTalkTextView.setVisibility(View.VISIBLE);
/*                        hubWorkshopTextView.setText();
                        hubWorkshopTextView.setVisibility(View.VISIBLE);*/
                    }
                });

    }
}