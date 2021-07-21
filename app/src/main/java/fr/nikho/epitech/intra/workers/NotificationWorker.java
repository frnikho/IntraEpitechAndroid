package fr.nikho.epitech.intra.workers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import org.jetbrains.annotations.NotNull;


import fr.nikho.epitech.intra.controllers.EpitechClient;
import fr.nikho.epitech.intra.data.Notification;
import fr.nikho.epitech.intra.services.ClientService;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NotificationWorker extends Worker {

    private Context context;

    public NotificationWorker(@NonNull @NotNull Context context, @NonNull @NotNull WorkerParameters workerParams) {
        super(context, workerParams);
        System.out.println("CONSTRUCTOR CALLED");
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public Result doWork() {
        EpitechClient client = ClientService.createClient(getApplicationContext());
        if (client == null)
            return Result.failure();
        client.getNotifications(ClientService.getEmail())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Notification[]>() {
            @Override
            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                System.out.println("SUBSCRIBE");
            }

            @Override
            public void onNext(Notification @io.reactivex.rxjava3.annotations.NonNull[] notifications) {
                System.out.println("NEXT");
            }

            @Override
            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                e.getStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("COMPLETE");
            }
        });
        return Result.success();
    }
}
