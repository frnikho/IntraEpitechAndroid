package fr.nikho.epitech.intra.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;
import fr.nikho.epitech.intra.R;
import fr.nikho.epitech.intra.EpitechClient;
import fr.nikho.epitech.intra.data.Notification;
import fr.nikho.epitech.intra.data.User;
import fr.nikho.epitech.intra.services.ClientService;
import fr.nikho.epitech.intra.services.NotificationService;
import fr.nikho.epitech.intra.services.UserService;
import fr.nikho.epitech.intra.utils.DateManager;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NotificationActivity extends AppCompatActivity {

    private LinearLayout notificationLayout;
    private static List<Integer> seenNotifications;
    private NotificationService notificationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_IntraEpitechAndroid_DIP);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        init();
        load();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        notificationService.saveSeenNotification(this, seenNotifications);
    }

    private void init() {
        seenNotifications = new ArrayList<>();
        notificationService = new NotificationService();
        notificationLayout = findViewById(R.id.notification_ll);

        List<Integer> ids = notificationService.loadSeenNotification(this);
        if (ids != null && !ids.isEmpty())
            seenNotifications.addAll(ids);
    }

    private boolean alreadySeen(int notificationId) {
        return seenNotifications.stream().anyMatch(notifications -> notificationId == notifications);
    }

    private void load() {
        EpitechClient client = ClientService.createClient(this);
        User user = UserService.getUser(this);
        if (client == null || user == null) {
            Log.e("notification", "client or user is null");
            return;
        }
        client.getNotifications(user.getInternalEmail())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Notification[]>() {
                    Notification[] notifs;
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(Notification @NonNull[] notifications) {
                        notifs = notifications;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getAnimationView();
                    }

                    @Override
                    public void onComplete() {
                        Arrays.stream(notifs).forEach(notification -> {
                            notificationLayout.addView(getNotificationItem(notification));
                        });

                        if (notifs.length == 0) {
                            Log.e("not", "hello");
                            getAnimationView();
                        }
                    }
                });

    }

    private void showBottomSheetDialog(Notification notification, TextView itemTitle) {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(R.layout.dialog_notification_bottom_sheet_layout);

        CircleImageView circleImageView = dialog.findViewById(R.id.notification_bottom_sheet_dialog_picture);
        TextView title = dialog.findViewById(R.id.notification_bottom_sheet_dialog_title);
        TextView content = dialog.findViewById(R.id.notification_bottom_sheet_dialog_content);

        String imageLink = ClientService.getLoginLink(this);
        imageLink = imageLink.substring(0, imageLink.length() - 1) + notification.getUser().getPicture();
        Glide.with(this).load(imageLink).centerCrop().placeholder(R.drawable.ic_launcher_foreground).into(circleImageView);

        title.setText(removeHTMLContent(notification.getTitle()));
        content.setText(removeHTMLContent(notification.getContent()));

        dialog.setOnDismissListener(v -> itemTitle.setTypeface(Typeface.DEFAULT));

        dialog.show();
    }

    private View getAnimationView() {
        LinearLayout layout = findViewById(R.id.notification_animation_layout);
        layout.setVisibility(View.VISIBLE);

        LottieAnimationView lottieAnimationView = findViewById(R.id.notification_animation);
        lottieAnimationView.setAnimation(R.raw.newsletter);
        lottieAnimationView.setRepeatCount(LottieDrawable.INFINITE);
        lottieAnimationView.playAnimation();
        return lottieAnimationView;
    }

    private View getNotificationItem(Notification notification) {
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        ConstraintLayout row = (ConstraintLayout) inflater.inflate(R.layout.item_notification, notificationLayout, false);

        TextView title = row.findViewById(R.id.notification_item_title);
        TextView date = row.findViewById(R.id.notification_item_date);
        CircleImageView picture = row.findViewById(R.id.notification_item_picture);

        row.setOnClickListener(v -> {
            seenNotifications.add(Integer.parseInt(notification.getId()));
            showBottomSheetDialog(notification, title);
        });

        title.setText(removeHTMLContent(notification.getTitle()));

        if (!alreadySeen(Integer.parseInt(notification.getId()))) {
            title.setTypeface(Typeface.DEFAULT_BOLD);
        }

        date.setText(notification.getDate());

        Calendar notifDate = new DateManager().getDateFromNotificationData(notification.getDate());
        Calendar now = new DateManager().getNow();
        long diff = notifDate.getTime().getTime() - now.getTime().getTime();
        Calendar diffCalendar = Calendar.getInstance();
        diffCalendar.setTime(new Date(diff));

        long seconds = TimeUnit.MILLISECONDS.toSeconds(diff);

        if (seconds <= -0)
            seconds *= -1;

        if (seconds > 60) {
            // MINUTES
            if ((seconds / 60) > 60) {
                // HOURS
                if ((seconds / 60 / 60) > 24) {
                    //DAYS
                    if (seconds / 60 / 60 / 24 > 31) {

                    } else {
                        date.setText((seconds / 60 / 60 / 24) + " d");
                    }
                } else {
                    date.setText((seconds / 60 / 60) + " h");
                }

            } else {
                date.setText((seconds / 60) + " m");
            }
        } else {
            date.setText(seconds + " s");
        }

        String imageLink = ClientService.getLoginLink(this);
        imageLink = imageLink.substring(0, imageLink.length() - 1) + notification.getUser().getPicture();
        Glide.with(this).load(imageLink).into(picture);
        return row;
    }

    private String removeHTMLContent(String htmlContent) {
        return htmlContent.replaceAll("<[^>]*>", "");
    }

}