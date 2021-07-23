package fr.nikho.epitech.intra.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.nikho.epitech.intra.R;
import fr.nikho.epitech.intra.activities.MarksActivity;
import fr.nikho.epitech.intra.EpitechClient;
import fr.nikho.epitech.intra.data.Netlog;
import fr.nikho.epitech.intra.data.User;
import fr.nikho.epitech.intra.services.ClientService;
import fr.nikho.epitech.intra.services.UserService;
import fr.nikho.epitech.intra.utils.NetlogUtils;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;

public class UserFragment extends Fragment {

    private TextView namesText, studentYearText;
    private TextView epitechLocationText, emailText;
    private TextView ghostText, medalText, encouragmentText, rescueText;

    private User user;
    private EpitechClient client;

    private LinearLayout userLinearLayout;

    private Button marksButton;

    private AnyChartView netlogChart;
    private PieChart netlogPieChart;

    private HashMap<User.UserInfoFields, Integer> infos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        user = UserService.getUser(requireContext());
        client = ClientService.createClient(requireContext());

        if (user == null || client == null)
            return;
        infos = new HashMap<>();
        infos.put(user.getUserInfo().getBirthplace(), R.drawable.ic_outline_cake_24_primary);
        infos.put(user.getUserInfo().getPhone(), R.drawable.ic_outline_local_phone_24);
        infos.put(user.getUserInfo().getPoste(), R.drawable.ic_outline_work_outline_24);
        infos.put(user.getUserInfo().getWebsite(), R.drawable.ic_outline_web_24);
        infos.put(user.getUserInfo().getCity(), R.drawable.ic_outline_location_city_24);
        infos.put(user.getUserInfo().getEmail(), R.drawable.ic_outline_email_24);
        infos.put(user.getUserInfo().getAddress(), R.drawable.ic_outline_location_on_24);
        infos.put(user.getUserInfo().getCountry(), R.drawable.ic_baseline_location_searching_24);

        userLinearLayout = view.findViewById(R.id.user_fragment_info_cardview_ll);
        marksButton = view.findViewById(R.id.user_fragment_marks_button);

        namesText = view.findViewById(R.id.user_fragment_user_names);
        studentYearText = view.findViewById(R.id.user_fragment_studentyear);

        netlogChart = view.findViewById(R.id.user_fragment_netlog_anychart);
        netlogPieChart = view.findViewById(R.id.user_fragment_netlog_chart);

        ghostText = view.findViewById(R.id.user_fragment_ghost_text);
        rescueText = view.findViewById(R.id.user_fragment_rescue_text);
        encouragmentText = view.findViewById(R.id.user_fragment_encouragement_text);
        medalText = view.findViewById(R.id.user_fragment_medal_text);

        String names = user.getFirstname() + " " + user.getLastname();
        namesText.setText(names);

        String studentYearTextContent = getString(R.string.scolar_year).replaceAll(":nb", String.valueOf(user.getStudentYear()));
        studentYearText.setText(studentYearTextContent);

        marksButton.setOnClickListener(this::onClickMarksButton);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        infos.forEach((info, icon) -> {
            if (info != null) {
                userLinearLayout.addView(getUserInfoView(inflater, info, icon));
            }
        });
        netlogPieChart.setDrawHoleEnabled(true);
        netlogPieChart.setHoleColor(Color.BLUE);
        netlogPieChart.setHoleRadius(0f);
        loadNetsoulLastWeek();
        loadUserFlags();
    }

    private void loadUserFlags() {
        client.getUserFlags(user.getInternalEmail())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<User.Flags>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<User.Flags> flagsResponse) {
                        User.Flags flags = new Gson().fromJson(flagsResponse.errorBody().charStream(), User.Flags.class);

                        int ghost = flags.getFlags().getGhost().getNb();
                        int difficulty = flags.getFlags().getDifficulty().getNb();
                        int remarkable = flags.getFlags().getRemarkable().getNb();
                        int medal = flags.getFlags().getMedal().getNb();

                        String ghostTextContent = getString(R.string.user_ghost_suffix).replace(":nb", String.valueOf(ghost));
                        String rescueTextContent = getString(R.string.user_rescue_suffix).replace(":nb", String.valueOf(ghost));
                        String encouragementTextContent = getString(R.string.user_encouragment_suffix).replace(":nb", String.valueOf(remarkable));
                        String medalTextContent = getString(R.string.user_medal_suffix).replace(":nb", String.valueOf(medal));

                        ghostText.setText(ghostTextContent);
                        rescueText.setText(rescueTextContent);
                        encouragmentText.setText(encouragementTextContent);
                        medalText.setText(medalTextContent);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void loadNetsoulLastWeek() {

        Pie pie = AnyChart.pie();

        client.getNetlog(user.getInternalEmail())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Double[][]>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(Double[] @NonNull [] doubles) {
                        List<Netlog> netlog = Netlog.parse(doubles);
                        int connected_time = NetlogUtils.getLastWeekNetlogInSeconds(netlog);
                        int average_time = NetlogUtils.getLastWeekNetlogAverageInSeconds(netlog);
                        List<DataEntry> data = new ArrayList<>();
                        ArrayList<PieEntry> entries = new ArrayList<>();
                        entries.add(new PieEntry(connected_time, "Logged"));
                        entries.add(new PieEntry(average_time/60f/60f*1000, "Average"));
                        data.add(new ValueDataEntry("Logged", connected_time/60));
                        data.add(new ValueDataEntry("Average", average_time/60f));

                        pie.credits().enabled(false);
                        pie.data(data);
                        netlogChart.setChart(pie);

                        PieDataSet dataSet = new PieDataSet(entries, "Netlog");
                        ArrayList<Integer> colors = new ArrayList<>();

                        for (int c : ColorTemplate.VORDIPLOM_COLORS)
                            colors.add(c);

                        for (int c : ColorTemplate.JOYFUL_COLORS)
                            colors.add(c);

                        for (int c : ColorTemplate.COLORFUL_COLORS)
                            colors.add(c);

                        for (int c : ColorTemplate.LIBERTY_COLORS)
                            colors.add(c);

                        for (int c : ColorTemplate.PASTEL_COLORS)
                            colors.add(c);

                        colors.add(ColorTemplate.getHoloBlue());

                        dataSet.setColors(colors);
                        PieData pieData = new PieData(dataSet);
                        netlogPieChart.setData(pieData);
                        netlogPieChart.invalidate();


                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void onClickMarksButton(View v) {
        startActivity(new Intent(getContext(), MarksActivity.class));
    }

    private ConstraintLayout getUserInfoView(LayoutInflater inflater, User.UserInfoFields info, int icon) {
        ConstraintLayout layout = (ConstraintLayout) inflater.inflate(R.layout.item_user_info, userLinearLayout, false);

        TextView infoText = layout.findViewById(R.id.user_info_item_text);
        ImageView infoIcon = layout.findViewById(R.id.user_info_item_image);

        infoText.setText(info.getValue());
        infoIcon.setBackgroundResource(icon);

        return layout;
    }

}