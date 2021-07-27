package fr.nikho.epitech.intra.fragments;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import fr.nikho.epitech.intra.EpitechClient;
import fr.nikho.epitech.intra.R;
import fr.nikho.epitech.intra.data.CalendarActivity;
import fr.nikho.epitech.intra.services.ClientService;
import fr.nikho.epitech.intra.utils.DateManager;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CalendarFragment extends Fragment {

    private static Calendar currentDate = null;

    private HashMap<Integer, ConstraintLayout> calendars;

    private DateFormat dateFormat;

    private LinearLayout calendarLL;

    private ImageView backIcon, forewardIcon;

    private TextView dayTitle;

    private DateManager dateManager;
    private EpitechClient client;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        calendars = new HashMap<>();

        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);

        if (currentDate == null)
            currentDate = Calendar.getInstance(Locale.FRANCE);

        calendarLL = view.findViewById(R.id.calendar_ll);

        backIcon = view.findViewById(R.id.calendar_back_icon);
        forewardIcon = view.findViewById(R.id.calendar_foreward_icon);

        dayTitle = view.findViewById(R.id.calendar_day);
        dayTitle.setText(dateFormat.format(currentDate.getTime()));
        dayTitle.setOnClickListener(this::showDatePickerDialog);

        client = ClientService.createClient(requireContext());
        dateManager = new DateManager();

        fetchWeek();

        return view;
    }

    private void showDatePickerDialog(View v) {
        DatePickerDialog dialog = new DatePickerDialog(getContext(), null, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH));

        dialog.setOnDateSetListener((view, year, month, dayOfMonth) -> {
            currentDate.set(year, month, dayOfMonth);
            dayTitle.setText(dateFormat.format(currentDate.getTime()));
            fetchWeek();
        });

        dialog.show();
    }

    /**
     * Fetch calendar activities
     * format: "2021-07-26"
     */
    private void fetchWeek() {
        Date d1 = new Date();
        Date d2 = new Date();
        if (currentDate != null) {
            d1 = dateManager.getFirstDayOfWeek(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH));
            d2 = dateManager.getLastDayOfWeek(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH));
        }
        client.getCalendarActivity(dateFormat.format(d1), dateFormat.format(d2))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CalendarActivity[]>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        LayoutInflater inflater = LayoutInflater.from(getContext());
                        for (int i = 0; i < 16; i++) {
                            ConstraintLayout cl = getCalendarHourView(inflater, 8 + i);
                            calendarLL.addView(cl);
                            calendars.put(i, cl);
                        }

                    }

                    @Override
                    public void onNext(CalendarActivity @NonNull [] calendarActivities) {
                        for (CalendarActivity activity : calendarActivities) {
                            Calendar start = dateManager.getDateFromModuleActivityData(activity.getStart());
                            Calendar end = dateManager.getDateFromModuleActivityData(activity.getEnd());

                            if (currentDate.get(Calendar.DAY_OF_WEEK) == start.get(Calendar.DAY_OF_WEEK)) {
                                Log.e("activity today", activity.getActiTitle());
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(getContext(), "An error occurred, try again later", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private ConstraintLayout getCalendarHourView(LayoutInflater inflater, int hours) {
        ConstraintLayout cl = (ConstraintLayout) inflater.inflate(R.layout.item_calendar_hour, calendarLL, false);
        TextView hour = cl.findViewById(R.id.item_calendar_hour_text);
        String hourStr = "";
        if (hours < 10)
            hourStr += "0";
        hourStr += String.valueOf(hours);
        hourStr += ":00";
        hour.setText(hourStr);
        return cl;
    }

}