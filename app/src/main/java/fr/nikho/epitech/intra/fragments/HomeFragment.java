package fr.nikho.epitech.intra.fragments;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import fr.nikho.epitech.intra.R;
import fr.nikho.epitech.intra.activities.HubActivity;
import fr.nikho.epitech.intra.activities.ModuleActivity;
import fr.nikho.epitech.intra.activities.NotificationActivity;
import fr.nikho.epitech.intra.activities.WelcomeActivity;
import fr.nikho.epitech.intra.controllers.EpitechClient;
import fr.nikho.epitech.intra.data.Dashboard;
import fr.nikho.epitech.intra.data.User;
import fr.nikho.epitech.intra.dialogs.GpaDialog;
import fr.nikho.epitech.intra.services.ClientService;
import fr.nikho.epitech.intra.services.UserService;
import info.androidhive.fontawesome.FontTextView;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeFragment extends Fragment {

    private EpitechClient client;
    private User user;
    private TextView userGpa, minimumCredits, userCredits;
    private LinearLayout projectLayout, activitiesLayout, modulesLayout, loadingLayout, homeLayout;
    private CardView userGpaCard, userCreditsCard;
    private GpaDialog gpaDialog;
    private FontTextView moreIcon, notifIcon;

    private LottieAnimationView loadingAnimationView;

    ViewGroup _root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        loadingAnimationView = view.findViewById(R.id.home_fragment_loadingview);
        loadingAnimationView.setAnimation(R.raw.loading_animation);
        loadingAnimationView.setRepeatCount(LottieDrawable.INFINITE);
        loadingAnimationView.playAnimation();

        loadingLayout = view.findViewById(R.id.home_fragment_loading_layout);
        loadingLayout.setVisibility(View.VISIBLE);

        homeLayout = view.findViewById(R.id.home_layout);
        homeLayout.setVisibility(View.INVISIBLE);

        this.user = UserService.getUser(getContext());
        client = ClientService.createClient(getContext());
        loadDashboardInformation();

        return view;
    }


    private void initHomeScreen() {
        _root = (ViewGroup) getView().findViewById(R.id.home_root);

        notifIcon = getView().findViewById(R.id.home_notification_icon);
        notifIcon.setOnClickListener(this::showNotif);

        moreIcon = getView().findViewById(R.id.home_more_icon);
        moreIcon.setOnClickListener(this::showMoreMenu);

        modulesLayout = getView().findViewById(R.id.home_modules_ll);

        activitiesLayout = getView().findViewById(R.id.home_activities_ll);

        minimumCredits = getView().findViewById(R.id.home_minimum_credits);
        userGpa = getView().findViewById(R.id.home_user_gpa);
        userCredits = getView().findViewById(R.id.home_user_credits);

        userGpaCard = getView().findViewById(R.id.home_user_gpa_card);
        userGpaCard.setOnClickListener(this::onClickUserGpaCard);

        userCreditsCard = getView().findViewById(R.id.home_user_credits_card);
        userCreditsCard.setOnClickListener(this::onClickUserCreditsCard);

        projectLayout = getView().findViewById(R.id.home_project_ll);

        gpaDialog = new GpaDialog();

    }

    private void onClickUserCreditsCard(View view) {
        System.out.println("user credits card");
    }

    private void onClickUserGpaCard(View view) {
        //gpaDialog.show(getSupportFragmentManager(), "example dialog");
        Intent intent = new Intent(getContext(), ModuleActivity.class);
        String[] d = {"2020", "B-PSU-402", "NAN-4-1"};
        intent.putExtra("data", d);
        startActivity(intent);
    }

    private void showNotif(View v) {
        startActivity(new Intent(getContext(), NotificationActivity.class));
    }

    private void showMoreMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(getContext(), v);
        popupMenu.setOnMenuItemClickListener(this::onMoreMenuItemClick);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.home_more_menu, popupMenu.getMenu());
        popupMenu.show();
    }



    private boolean onMoreMenuItemClick(MenuItem menu) {
        if (menu.getItemId() == R.id.menu_home_more_hub) {
            startActivity(new Intent(getContext(), HubActivity.class));
        }
        return false;
    }

    private void loadDashboardInformation() {
        client.getDashboard()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Dashboard>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onNext(@NonNull Dashboard dashboard) {
                        initHomeScreen();
                        Arrays.stream(dashboard.getBoard().getProjects()).forEach(project -> projectLayout.addView(getProjectItem(project)));
                        Arrays.asList(dashboard.getBoard().getActivities()).forEach(activity -> activitiesLayout.addView(getActivityItem(activity)));
                        Arrays.asList(dashboard.getBoard().getModules()).forEach(module -> modulesLayout.addView(getModuleLayout(module)));
                        if (dashboard.getBoard().getProjects().length == 0)
                            projectLayout.addView(getBreakTimeAnimation(projectLayout, R.raw.men_relaxing_on_working_chair, "No projects"));
                        if (dashboard.getBoard().getModules().length == 0)
                            modulesLayout.addView(getBreakTimeAnimation(modulesLayout, R.raw.coworkers_make_break, "No modules"));
                        if (dashboard.getBoard().getActivities().length == 0)
                            activitiesLayout.addView(getBreakTimeAnimation(activitiesLayout, R.raw.levitate_meditate_peace_and_love, "Not activities"));

                        int uc = Arrays.stream(dashboard.getNotes())
                                .filter(v -> !v.getGrade().equalsIgnoreCase("Echec"))
                                .filter(v -> !v.getGrade().equalsIgnoreCase("-"))
                                .filter(v -> v.getSemesterCode().equalsIgnoreCase("B4"))
                                .mapToInt(note -> Integer.parseInt(note.getCredits())).sum();
                        userCredits.setText(String.valueOf(uc));
                        int cmin = Integer.parseInt(dashboard.getNotes()[0].getCreditsMin());
                        userCredits.setTextColor(getResources().getColor(R.color.intra_primary));
                        userGpa.setText(user.getGpa()[0].getGpa());
                        minimumCredits.setText(String.valueOf(cmin));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getContext(), WelcomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        startActivity(intent);
                    }

                    @Override
                    public void onComplete() {
                        loadingAnimationView.pauseAnimation();
                        loadingLayout.setVisibility(View.INVISIBLE);

                        homeLayout.setVisibility(View.VISIBLE);
                    }
                });
    }

    public LinearLayout getProjectItem(Dashboard.Board.Project project) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        LinearLayout row = (LinearLayout) inflater.inflate(R.layout.home_project_item, projectLayout, false);
        TextView title = row.findViewById(R.id.project_title);

        TextView start = row.findViewById(R.id.project_timeline_start);
        TextView end = row.findViewById(R.id.project_timeline_end);

        start.setText(project.getTimelineStart());
        end.setText(project.getTimelineEnd());

        ProgressBar progress = row.findViewById(R.id.project_progress_bar);
        progress.setProgress(100);
        title.setText(project.getTitle());
        return row;
    }

    public LinearLayout getActivityItem(Dashboard.Board.Activity activity) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        LinearLayout row = (LinearLayout) inflater.inflate(R.layout.home_activity_item, activitiesLayout, false);
        TextView title = row.findViewById(R.id.activity_name);
        TextView start = row.findViewById(R.id.activity_start_date);
        TextView end = row.findViewById(R.id.activity_end_date);
        ProgressBar progress = row.findViewById(R.id.activity_progress_bar);

        title.setText(activity.getTitle());
        start.setText(activity.getTimelineStart());
        end.setText(activity.getTimelineEnd());
        progress.setProgress(100);

        return row;
    }

    public TextView getTextViewChild(String text) {
        TextView textView = new TextView(getContext());
        textView.setText(text);
        return textView;
    }

    public LinearLayout getModuleLayout(Dashboard.Board.Module module) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        LinearLayout row = (LinearLayout) inflater.inflate(R.layout.home_activity_item, activitiesLayout, false);
        TextView title = row.findViewById(R.id.module_name);
        TextView start = row.findViewById(R.id.module_start_date);
        TextView end = row.findViewById(R.id.module_end_date);
        ProgressBar progress = row.findViewById(R.id.module_progress_bar);

        title.setText(module.getTitle());
        start.setText(module.getTimelineStart());
        end.setText(module.getTimelineEnd());
        progress.setProgress(100);

        return row;
    }

    public ConstraintLayout getBreakTimeAnimation(ViewGroup parent, int resourceId, String text) {

        LayoutInflater inflater = LayoutInflater.from(getContext());

        ConstraintLayout layout = (ConstraintLayout) inflater.inflate(R.layout.dashboard_item, parent, false);

        LottieAnimationView view = layout.findViewById(R.id.dashboard_item_lottieanimationview);
        TextView title = layout.findViewById(R.id.dashboard_item_title);

        view.setAnimation(resourceId);
        view.setRepeatCount(LottieDrawable.INFINITE);
        view.playAnimation();
        view.setForegroundGravity(Gravity.END);

        title.setText(text);

        return layout;
    }
}
