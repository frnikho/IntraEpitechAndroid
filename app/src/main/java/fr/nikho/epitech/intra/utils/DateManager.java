package fr.nikho.epitech.intra.utils;

import java.util.Calendar;
import java.util.Date;

public class DateManager {

    /**
     * Get the Date from a String
     * @param data a String which contains: "01/01/2021, 00h00",
     * @return a Date
     */
    public Date getDateFromData(String data) {
        Calendar calendar = Calendar.getInstance();
        if (data.isEmpty())
            return null;
        try {
            String[] time_date = data.split(", ");
            String[] dateValues = time_date[0].split("/");
            String[] timeValues = time_date[1].split("h");
            int day = Integer.parseInt(dateValues[0]);
            int months = Integer.parseInt(dateValues[1])-1;
            int year = Integer.parseInt(dateValues[2]);
            int hours = Integer.parseInt(timeValues[0]);
            int minutes = Integer.parseInt(timeValues[1]);
            calendar.set(year, months, day, hours, minutes);
        } catch (Exception ex) {
            return null;
        }
        return calendar.getTime();
    }

}
