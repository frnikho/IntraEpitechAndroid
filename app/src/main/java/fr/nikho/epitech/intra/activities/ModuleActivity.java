package fr.nikho.epitech.intra.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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

import com.bumptech.glide.Glide;
import com.github.vipulasri.timelineview.TimelineView;

import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import fr.nikho.epitech.intra.R;
import fr.nikho.epitech.intra.EpitechClient;
import fr.nikho.epitech.intra.data.Module;
import fr.nikho.epitech.intra.data.User;
import fr.nikho.epitech.intra.services.ClientService;
import fr.nikho.epitech.intra.services.UserService;
import fr.nikho.epitech.intra.utils.DateManager;
import io.github.florent37.shapeofview.shapes.RoundRectView;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ModuleActivity extends AppCompatActivity {

    private RoundRectView userGradeCard;
    private TextView titleText, userGradeText, userCreditText, descriptionText, competenceText;
    private TextView beginDateText, endRegisterDateText, endDateText;
    private LinearLayout responsableLayout, templateResponsableLayout;

    private User user;
    private EpitechClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_IntraEpitechAndroid_DIP);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);
        Intent intent = getIntent();

        user = UserService.getUser(this);
        client = ClientService.createClient(this);

        if (intent == null || user == null || client == null)
            return;
        String[] data = intent.getStringArrayExtra("data");
        if (data == null)
            return;
        init(data);
    }

    private void bindViews() {
        titleText = findViewById(R.id.module_title);

        userGradeText = findViewById(R.id.module_user_grade);
        userGradeCard = findViewById(R.id.module_grade_card);

        beginDateText = findViewById(R.id.module_begin_date);
        endRegisterDateText = findViewById(R.id.module_end_register_date);
        endDateText = findViewById(R.id.module_end_date);

/*        descriptionText = findViewById(R.id.module_description);
        competenceText = findViewById(R.id.module_competence);
        responsableLayout = findViewById(R.id.module_responsable_layout);
        templateResponsableLayout = findViewById(R.id.module_template_responsable_layout);*/
        userCreditText = findViewById(R.id.module_user_credits);
    }

    private void init(String[] data) {
        bindViews();
        String year = data[0];
        String code = data[1];
        String instance = data[2];

        Log.e("Module", "abc");

        client.getModule(year, code, instance)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Module>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Module module) {
                        titleText.setText(module.getTitle());

                      /*  if (descriptionText != null)
                            descriptionText.setText(module.getDescription());
                        if (module.getCompetence() != null)
                            competenceText.setText(module.getCompetence());*/

                        if (module.getStudentRegistered() == 1) {
                            userGradeCard.setVisibility(View.VISIBLE);
                            userGradeText.setText("Grade: " + module.getStudentGrade());
                            if (Integer.parseInt(module.getUserCredits()) <= 0) {
                                userCreditText.setVisibility(View.INVISIBLE);
                            } else {
                                userCreditText.setText(getString(R.string.you_got_nb_credit_s).replace(":nb", module.getUserCredits()));
                            }
                        } else {
                            userGradeCard.setVisibility(View.INVISIBLE);
                        }

                        Calendar begin = new DateManager().getDateFromModuleData(module.getBegin());
                        Calendar endRegisteration = new DateManager().getDateFromModuleData(module.getEndRegister());
                        Calendar end = new DateManager().getDateFromModuleData(module.getEnd());

                        Calendar now = new DateManager().getNow();

                        if (now.compareTo(begin) > 0) {

                        } else {

                        }
                        /*LayoutInflater inflater = LayoutInflater.from(getApplicationContext());

                        String authUrl = ClientService.getLoginLink(getApplicationContext());
                        authUrl = authUrl.substring(0, authUrl.length() - 1);

                        for (Module.Manager manager : module.getResp()) {
                            responsableLayout.addView(getUserItemView(inflater, responsableLayout, manager, authUrl));
                        }

                        for (Module.Manager manager : module.getTemplateResp()) {
                            templateResponsableLayout.addView(getUserItemView(inflater, templateResponsableLayout, manager, authUrl));
                        }*/

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("module", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private ConstraintLayout getUserItemView(LayoutInflater inflater, ViewGroup parent, Module.Manager managers, String authUrl) {
        ConstraintLayout cl = (ConstraintLayout) inflater.inflate(R.layout.module_user_item, parent, false);

        TextView userName = cl.findViewById(R.id.module_user_item_names);
        CircleImageView imageView = cl.findViewById(R.id.module_user_item_picture);

        userName.setText(managers.getTitle());

        String url = authUrl + managers.getPicture();

        Glide.with(getApplicationContext())
                .load(url)
                .into(imageView);

        return cl;
    }
}