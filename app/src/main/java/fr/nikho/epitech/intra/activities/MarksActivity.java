package fr.nikho.epitech.intra.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import fr.nikho.epitech.intra.R;
import fr.nikho.epitech.intra.EpitechClient;
import fr.nikho.epitech.intra.data.Marks;
import fr.nikho.epitech.intra.data.User;
import fr.nikho.epitech.intra.services.ClientService;
import fr.nikho.epitech.intra.services.UserService;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MarksActivity extends AppCompatActivity {

    private Button backHome;

    private HashMap<Marks.Modules, String> userModulesMarks;
    private ArrayList<Marks.Mark> userMarks;
    private List<Integer> lottieAnimationsNoMark;
    private LinearLayout listView, moduleLayout;
    private EpitechClient client;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks);

        userModulesMarks = new HashMap<>();
        userMarks = new ArrayList<>();
        lottieAnimationsNoMark = new ArrayList<>();

        client = ClientService.createClient(this);
        user = UserService.getUser(this);

        listView = findViewById(R.id.marks_top_linearlayout);
        moduleLayout = findViewById(R.id.mark_module_linearlayout);
        backHome = findViewById(R.id.marks_back_home_button);

        lottieAnimationsNoMark.add(R.raw.dancing_celebrating_cat);
        lottieAnimationsNoMark.add(R.raw.dancing_dog);
        lottieAnimationsNoMark.add(R.raw.vikgraphic_no_bg);

        backHome.setOnClickListener(this::backHome);

        loadMarks();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userModulesMarks.clear();
    }

    private void loadMarks() {
        client.getMarks(user.getInternalEmail())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Marks>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Marks marks) {
                        userMarks.addAll(Arrays.stream(marks.getMarks()).collect(Collectors.toList()));
                        Arrays.stream(marks.getModules()).forEach(module -> userModulesMarks.put(module, getModuleSemester(module)));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(getApplicationContext(), "An error occurred: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {
                        //LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                        //inflater.inflate(R.layout.user_info_item, listView, false);
                        int semester = 0;
                        while (true) {
                            List<Marks.Modules> tmpModules = getModulesBySemester(String.valueOf(semester));
                            if (tmpModules.size() == 0)
                                break;
                            LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                            listView.addView(getSemesterTitleView(inflater, semester));

                            tmpModules.forEach(mod -> moduleLayout.addView(getModuleView(inflater, mod)));

                            semester++;
                        }

                    }
                });
    }

    private ConstraintLayout getSemesterTitleView(LayoutInflater inflater, int semester) {
        ConstraintLayout layout = (ConstraintLayout) inflater.inflate(R.layout.mark_semester_layout, listView, false);
        TextView textView = layout.findViewById(R.id.mark_semester_titleview);
        textView.setOnClickListener(v -> showOnlySemester(textView, semester));
        String title = getString(R.string.mark_semester) + semester;
        textView.setText(title);
        return layout;
    }

    private LinearLayout getModuleView(LayoutInflater inflater, Marks.Modules modules) {
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.mark_module_layout, moduleLayout, false);

        TextView name = linearLayout.findViewById(R.id.mark_module_name);
        TextView credit = linearLayout.findViewById(R.id.mark_module_credits);
        TextView grade = linearLayout.findViewById(R.id.mark_module_grade);

        if (modules.getTitle() != null)
            name.setText(modules.getTitle());
        credit.setText(String.valueOf(modules.getCredits()));
        if (modules.getGrade() != null)
            grade.setText(modules.getGrade());

        linearLayout.setOnClickListener(v -> {
            //Toast.makeText(getApplicationContext(), "" + modules.getCodeInstance(), Toast.LENGTH_LONG).show();
            showBottomSheetDialog(modules);
        });

        return linearLayout;
    }

    private String getModuleSemester(Marks.Modules modules) {
        String[] code = modules.getCodeInstance().split("-");
        /*int semester = Integer.parseInt(code[1]);*/
        return code[1];
    }

    private void showOnlySemester(TextView textView, int semester) {
        moduleLayout.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        getModulesBySemester(String.valueOf(semester)).forEach(mod -> moduleLayout.addView(getModuleView(inflater, mod)));
    }

    private List<Marks.Modules> getModulesBySemester(String semester) {
        List<Marks.Modules> modules = new ArrayList<>();
        userModulesMarks.forEach((m, s) -> {
            if (s.equalsIgnoreCase(semester))
                modules.add(m);
        });

        return modules;
    }

    private View getMarkView(Marks.Mark mark, ViewGroup layout) {
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());

        ConstraintLayout cl = (ConstraintLayout) inflater.inflate(R.layout.item_dialog_mark, layout, false);
        TextView markText = cl.findViewById(R.id.mark_dialog_item_note);

        if (mark != null)
            markText.setText(String.valueOf(mark.getFinalNote()));
        return cl;
    }

    private void backHome(View view) {
        finish();
    }

    private List<Marks.Mark> getMarkByModule(Marks.Modules module) {
        return userMarks.stream().filter(mark -> mark.getCodeModule().equalsIgnoreCase(module.getCodeModule())).collect(Collectors.toList());
    }

    private void showBottomSheetDialog(Marks.Modules modules) {
        List<Marks.Mark> marks = getMarkByModule(modules);
        BottomSheetDialog dialog = new BottomSheetDialog(this);

        if (marks.size() == 0) {
            dialog.setContentView(R.layout.dialog_no_mark);
            LottieAnimationView anim = dialog.findViewById(R.id.no_marks_animation);
            anim.setAnimation(lottieAnimationsNoMark.get(new Random().nextInt(lottieAnimationsNoMark.size())));
            anim.setRepeatCount(LottieDrawable.INFINITE);
            anim.playAnimation();
            dialog.show();
            return;
        }

        dialog.setContentView(R.layout.mark_bottom_sheet_dialog_layout);
        LinearLayout layout = dialog.findViewById(R.id.mark_dialog_linearlayout);
        marks.forEach(mark -> layout.addView(getMarkView(mark, layout)));
        dialog.show();
    }
}