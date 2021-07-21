package fr.nikho.epitech.intra.utils;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fr.nikho.epitech.intra.data.Netlog;

public class NetlogUtils {

    public static Date getDate(Integer[][] netlog) {
        Integer[] array = netlog[0];
        Timestamp timestamp = new Timestamp(array[0]);
        return null;
    }

    public static int getTimestampInSeconds(long timestamp) {
        Calendar cal = Calendar.getInstance();
        Timestamp ts = new Timestamp(timestamp);
        Date date = new Date(ts.getTime());
        cal.setTime(date);
        int seconds = cal.get(Calendar.SECOND);
        int minutes = cal.get(Calendar.MINUTE);
        int hours = cal.get(Calendar.HOUR);
        int days = cal.get(Calendar.DAY_OF_MONTH) - 1;
        if (hours >= 1)
            hours-=1;
        return (seconds + (minutes * 60) + (hours * 60 * 60) + (days * 60 * 60 * 60));
    }

    public static int getLastWeekNetlogInSeconds(List<Netlog> netlogList) {
        double ts = 0;
        for (int i = 1; i < 8; i++) {
            Netlog log = netlogList.get(netlogList.size() - i);
            ts += log.getCurrentInTS();
        }
        return getTimestampInSeconds(Math.round(ts));
    }

    public static int getLastWeekNetlogAverageInSeconds(List<Netlog> netlogs) {
        double ts = 0;
        for (int i = 1; i < 8; i++) {
            Netlog log = netlogs.get(netlogs.size() - i);
            ts += log.getAverageInTS();
        }
        return getTimestampInSeconds(Math.round(ts));
    }

}
