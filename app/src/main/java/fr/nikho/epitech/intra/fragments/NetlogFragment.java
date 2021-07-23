package fr.nikho.epitech.intra.fragments;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import fr.nikho.epitech.intra.R;
import fr.nikho.epitech.intra.EpitechClient;
import fr.nikho.epitech.intra.data.Netlog;
import fr.nikho.epitech.intra.data.User;
import fr.nikho.epitech.intra.services.ClientService;
import fr.nikho.epitech.intra.services.UserService;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NetlogFragment extends Fragment {

    private EpitechClient client;
    private User user;
    private LineChart lineChart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_netlog, container, false);
        lineChart = view.findViewById(R.id.netlog_linechart);

        lineChart.setViewPortOffsets(0, 0, 0, 0);

        lineChart.setBackgroundColor(getResources().getColor(R.color.intra_primary, getActivity().getTheme()));

        // no description text
        lineChart.getDescription().setEnabled(false);

        // enable touch gestures
        lineChart.setTouchEnabled(false);

        // enable scaling and dragging
        lineChart.setDragEnabled(false);
        lineChart.setScaleEnabled(false);
        lineChart.setPinchZoom(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setMaxHighlightDistance(300);

        XAxis x = lineChart.getXAxis();
        x.setEnabled(false);

        YAxis y = lineChart.getAxisLeft();
        y.setEnabled(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            y.setTypeface(getResources().getFont(R.font.lato_light));
        }
        y.setLabelCount(6, false);
        y.setTextColor(Color.WHITE);
        y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        y.setDrawGridLines(false);
        y.setAxisLineColor(Color.WHITE);

        lineChart.getAxisRight().setEnabled(false);
        lineChart.getLegend().setEnabled(false);

        // don't forget to refresh the drawing
        lineChart.invalidate();

        client = ClientService.createClient(getContext());
        user = UserService.getUser(getContext());

        if (client == null || user == null)
            return view;

        loadChartData();
        return view;
    }

    private void loadChartData() {
        List<Entry> currentEntries = new ArrayList<>();
        List<Entry> averageEntries = new ArrayList<>();

        client.getNetlog(user.getInternalEmail())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Double[][]>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(Double[] @NonNull [] doubles) {
                        List<Netlog> netlogs = Netlog.parse(doubles);

                        int monthly = 32;
                        int weekly = 8;

                        for (int i = 1; i < weekly; i++) {
                            double average = netlogs.get(netlogs.size() - weekly + i).getAverageInTS();
                            double current = netlogs.get(netlogs.size() - weekly + i).getCurrentInTS();

                            currentEntries.add(new Entry(i, Float.parseFloat(String.valueOf(current / 60 / 60))));
                            averageEntries.add(new Entry(i, Float.parseFloat(String.valueOf(average / 60 / 60))));
                        }

                        LineDataSet currentSet = new LineDataSet(currentEntries, "current"); // add entries to dataset
                        LineDataSet averageSet = new LineDataSet(averageEntries, "average");
                        LineData lineData = new LineData(averageSet, currentSet);

                        currentSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
                        currentSet.setCubicIntensity(0.1f);
                        currentSet.setDrawFilled(true);
                        currentSet.setDrawCircles(false);
                        currentSet.setLineWidth(1.8f);
                        currentSet.setCircleRadius(4f);
                        currentSet.setCircleColor(Color.WHITE);
                        currentSet.setHighLightColor(Color.rgb(244, 117, 117));
                        currentSet.setColor(Color.GRAY);
                        currentSet.setFillColor(Color.WHITE);
                        currentSet.setFillAlpha(100);
                        currentSet.setDrawHorizontalHighlightIndicator(false);

                        averageSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
                        averageSet.setCubicIntensity(0.2f);
                        averageSet.setDrawFilled(true);
                        averageSet.setDrawCircles(false);
                        averageSet.setLineWidth(1.8f);
                        averageSet.setCircleRadius(4f);
                        averageSet.setCircleColor(Color.WHITE);
                        averageSet.setHighLightColor(Color.rgb(244, 117, 117));
                        averageSet.setColor(Color.BLUE);
                        averageSet.setFillColor(Color.WHITE);
                        averageSet.setFillAlpha(100);
                        averageSet.setDrawHorizontalHighlightIndicator(false);
                        lineChart.setData(lineData);
                        lineChart.invalidate(); // refresh
                        Log.e("netlog", netlogs.size() + "");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("netlog", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}