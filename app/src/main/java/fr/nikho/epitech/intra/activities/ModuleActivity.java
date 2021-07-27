package fr.nikho.epitech.intra.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import fr.nikho.epitech.intra.R;
import fr.nikho.epitech.intra.EpitechClient;
import fr.nikho.epitech.intra.data.Module;
import fr.nikho.epitech.intra.services.ClientService;
import fr.nikho.epitech.intra.utils.DateManager;
import io.github.florent37.shapeofview.shapes.RoundRectView;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ModuleActivity extends AppCompatActivity {

    private LottieAnimationView loadingAnim;
    private RoundRectView userGradeCard, descriptionRRV;
    private TextView titleText, userGradeText, userCreditText;
    private TextView beginDateText, endRegisterDateText, endDateText;
    private ConstraintLayout descriptionCL, mainCL;

    private TextView activitiesTitleText, pastActivitiesTitleText;
    private LinearLayout activitiesLinearLayout, pastActivitiesLinearLayout;

    private EpitechClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_IntraEpitechAndroid_DIP);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);
        Intent intent = getIntent();
        client = ClientService.createClient(this);

        if (intent == null) {
            invalidIntentData();
            return;
        }
        if (client == null) {
            Toast.makeText(getApplicationContext(), "Can't got Client", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
            return;
        }
        String[] data = intent.getStringArrayExtra("data");
        if (data == null || data.length < 3) {
            invalidIntentData();
            return;
        }
        init(data);
    }

    private void invalidIntentData() {
        Toast.makeText(getApplicationContext(), "Invalid module, module year or code instance", Toast.LENGTH_LONG).show();
        finish();
    }

    private void bindViews() {
        titleText = findViewById(R.id.module_title);

        userGradeText = findViewById(R.id.module_user_grade);
        userGradeCard = findViewById(R.id.module_grade_card);

        beginDateText = findViewById(R.id.module_begin_date);
        endRegisterDateText = findViewById(R.id.module_end_register_date);
        endDateText = findViewById(R.id.module_end_date);

        descriptionCL = findViewById(R.id.module_description_cl);
        userCreditText = findViewById(R.id.module_user_credits);

        activitiesLinearLayout = findViewById(R.id.module_activities_ll);
        pastActivitiesLinearLayout = findViewById(R.id.module_past_activities_ll);

        activitiesTitleText = findViewById(R.id.module_project_title1);
        pastActivitiesTitleText = findViewById(R.id.module_project_title2);

        mainCL = findViewById(R.id.activity_cl);
        loadingAnim = findViewById(R.id.activity_loading_anim);

        descriptionRRV = findViewById(R.id.module_description_rrv);
    }

    private void init(String[] data) {
        bindViews();
        client.getModule(data[0], data[1], data[2])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Module>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mainCL.setVisibility(View.INVISIBLE);
                        loadingAnim.setVisibility(View.VISIBLE);
                        loadingAnim.setAnimation(R.raw.loading_animation);
                        loadingAnim.setRepeatCount(LottieDrawable.INFINITE);
                        loadingAnim.playAnimation();
                    }

                    @Override
                    public void onNext(@NonNull Module module) {
                        Calendar now = new DateManager().getNow();
                        titleText.setText(module.getTitle());
                        displayDescription(module);
                        displayUserGradeCard(module);
                        displayTimeline(now, module);
                        displayActivities(now, module);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(getApplicationContext(), "An error occured, please try again later", Toast.LENGTH_LONG).show();
                        Log.e("Module error", e.getMessage());
                        finish();
                    }

                    @Override
                    public void onComplete() {
                        loadingAnim.pauseAnimation();
                        loadingAnim.setVisibility(View.INVISIBLE);
                        mainCL.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void displayDescription(Module module) {
        if (module.getDescription() == null && module.getCompetence() == null) {
            descriptionRRV.removeAllViews();
            descriptionRRV.setVisibility(View.INVISIBLE);
        } else {
            descriptionCL.setOnClickListener(v -> onClickDescriptionCard(module));
        }
    }

    private void displayUserGradeCard(Module module) {
        if (module.getStudentRegistered() == 1) {
            userGradeCard.setVisibility(View.VISIBLE);
            userGradeText.setText(getString(R.string.module_user_grade_text).replaceAll(":gr", module.getStudentGrade()));
            if (Integer.parseInt(module.getUserCredits()) <= 0) {
                userCreditText.setVisibility(View.INVISIBLE);
            } else {
                userCreditText.setText(getString(R.string.you_got_nb_credit_s).replace(":nb", module.getUserCredits()));
                userGradeCard.setOnClickListener(v -> onClickUserGradeCard(module));
            }
        } else {
            userGradeCard.setVisibility(View.INVISIBLE);
            ViewGroup.LayoutParams params = userGradeCard.getLayoutParams();
            params.height = 1;
            userGradeCard.setLayoutParams(params);
        }
    }

    private void displayTimeline(Calendar now, Module module) {
        Calendar begin = new DateManager().getDateFromModuleData(module.getBegin());
        Calendar endRegistration = new DateManager().getDateFromModuleData(module.getEndRegister());
        Calendar end = new DateManager().getDateFromModuleData(module.getEnd());

        if (now.compareTo(begin) > 0)
            beginDateText.setBackgroundColor(getColor(R.color.flatDarkGray));
        else
            beginDateText.setBackgroundColor(getColor(R.color.flatGreen));
        if (now.compareTo(endRegistration) > 0)
            endRegisterDateText.setBackgroundColor(getColor(R.color.flatDarkGray));
        else
            endRegisterDateText.setBackgroundColor(getColor(R.color.flatGreen));
        if (now.compareTo(end) > 0)
            endDateText.setBackgroundColor(getColor(R.color.flatDarkGray));
        else
            endDateText.setBackgroundColor(getColor(R.color.flatGreen));
    }

    private void displayActivities(Calendar now, Module module) {
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        int pastActivityCount = 0;
        int currentActivityCount = 0;
        for (Module.Activity activity : module.getActivites()) {
            Calendar endCalendar = new DateManager().getDateFromModuleActivityData(activity.getEnd());

            if (now.compareTo(endCalendar) > 0) {
                pastActivityCount++;
                pastActivitiesLinearLayout.addView(getActivityItemView(inflater, activitiesLinearLayout, module, activity));
            } else {
                currentActivityCount++;
                activitiesLinearLayout.addView(getActivityItemView(inflater, activitiesLinearLayout, module, activity));
            }
        }

        if (pastActivityCount == 0) {
            pastActivitiesTitleText.setVisibility(View.INVISIBLE);
            pastActivitiesTitleText.setPadding(0, 0, 0, 0);
        }
        if (currentActivityCount == 0) {
            activitiesTitleText.setVisibility(View.INVISIBLE);
            activitiesTitleText.setPadding(0, 0, 0, 0);
        }
    }

    private void onClickUserGradeCard(Module module) {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(R.layout.dialog_module_user_grade);

        dialog.show();
    }

    private void onClickDescriptionCard(Module module) {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(R.layout.dialog_module_description);

        TextView desc = dialog.findViewById(R.id.dialog_module_description_text);
        TextView skills = dialog.findViewById(R.id.dialog_module_description_competence_text);

        if (desc != null)
            desc.setText(module.getDescription());
        if (skills != null)
            skills.setText(module.getCompetence());

        dialog.show();
    }

    private ConstraintLayout getActivityItemView(LayoutInflater inflater, ViewGroup parent, Module module, Module.Activity activity) {
        String[][] iconAndType = {
                {"proj", R.drawable.ic_project + "", "" + R.color.projectCard},
                {"rdv", R.drawable.ic_appointment + "", "" + R.color.appointmentCard},
                {"tp", R.drawable.ic_workshop + "", "" + R.color.workshopCard},
                {"class", R.drawable.ic_bootstrap + "", "" + R.color.bootstrapCard}};

        ConstraintLayout cl = (ConstraintLayout) inflater.inflate(R.layout.item_module_activity, parent, false);
        ImageView icon = cl.findViewById(R.id.item_module_type_icon);
        TextView title = cl.findViewById(R.id.item_module_title);
        ConstraintLayout card = cl.findViewById(R.id.item_module_cl);
        LinearLayout layoutCard = cl.findViewById(R.id.item_module_type_ll);
        title.setText(activity.getTitle());

        for (String[] strings : iconAndType) {
            if (strings[0].equalsIgnoreCase(activity.getTypeCode())) {
                icon.setBackground(AppCompatResources.getDrawable(this, Integer.parseInt(strings[1])));
                layoutCard.setBackgroundColor(getColor(Integer.parseInt(strings[2])));
            }
        }
        card.setOnClickListener(v -> onClickOnActivity(module, activity));
        return cl;
    }

    private void onClickOnActivity(Module module, Module.Activity activity) {
        Log.e("CLICKED", "CL");
        Intent intent = new Intent(this, ModuleActivityActivity.class);
        String year = module.getScolaryear();
        String codeModule = module.getCodemodule();
        String codeInstance = module.getCodeinstance();
        String codeActivity = activity.getCodeActi();
        intent.putExtra("data", new String[]{year, codeModule, codeInstance, codeActivity});
        startActivity(intent);
    }
}