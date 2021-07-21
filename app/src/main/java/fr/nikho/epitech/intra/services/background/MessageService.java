package fr.nikho.epitech.intra.services.background;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.List;

import fr.nikho.epitech.intra.controllers.EpitechClient;
import fr.nikho.epitech.intra.data.Notification;
import fr.nikho.epitech.intra.data.User;
import fr.nikho.epitech.intra.services.ClientService;
import fr.nikho.epitech.intra.services.NotificationService;
import fr.nikho.epitech.intra.services.UserService;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MessageService extends Service {

    private NotificationService notificationService;
    private Context context;
    private User user;
    private EpitechClient client;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        context = this;
        user = UserService.getUser(context);
        client = ClientService.createClient(context);
        notificationService = new NotificationService();

        if (client == null || user == null)
            return START_STICKY;

        startTask();
        return START_STICKY;
    }

    private void startTask() {
        Log.e("Message Service", "startTask");
         new CountDownTimer(10000, 10) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                sendNotificationRequest();
                Log.e("Message Service", "onFinish: ");
                startTask();
            }
        }.start();
    }

    private boolean alreadySeen(Notification notification, List<Integer> notifications) {
        return notifications.stream().anyMatch(n -> n == Integer.parseInt(notification.getId()));
    }

    private void sendNotificationRequest() {
        client.getNotifications(user.getInternalEmail())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Notification[]>() {
                    List<Integer> seenNotification;
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        seenNotification = notificationService.loadSeenNotification(context);
                    }

                    @Override
                    public void onNext(Notification @NonNull[] notifications) {
                        for (Notification notification : notifications) {
                            if (!alreadySeen(notification, seenNotification)) {
                                Log.e("Message Service", "new notifications !");
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("Message Service", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("Message Service", "onComplete :)");
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
