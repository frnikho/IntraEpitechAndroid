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

    /**
     * Get a Calendar class from a Notification String
     * @param notifData a String which contains: "2021/07/23, 17:11:02",
     * @return Calendar instance
     */

    public Calendar getDateFromNotificationData(String notifData) {
        Calendar calendar = Calendar.getInstance();
        if (notifData.isEmpty())
            return null;
        try {
            String[] time_date = notifData.split(" ");
            String[] dateValues = time_date[0].split("-");
            String[] timeValues = time_date[1].split(":");
            int year = Integer.parseInt(dateValues[0]);
            int months = Integer.parseInt(dateValues[1])-1;
            int day = Integer.parseInt(dateValues[2]);
            int hours = Integer.parseInt(timeValues[0]);
            int minutes = Integer.parseInt(timeValues[1]);
            int seconds = Integer.parseInt(timeValues[2]);
            calendar.set(year, months, day, hours, minutes, seconds);

        } catch (Exception ex) {
            ex.getStackTrace();
            return null;
        }
        return calendar;
    }

    /**
     * Get a Calendar class
     * @param data a String which contains: "2021/07/23",
     * @return Calendar
     */

    public Calendar getDateFromModuleData(String data) {
        Calendar calendar = Calendar.getInstance();
        if (data.isEmpty())
            return null;
        try {
            String[] date = data.split("-");
            int year = Integer.parseInt(date[0]);
            int months = Integer.parseInt(date[1])-1;
            int day = Integer.parseInt(date[2]);
            calendar.set(year, months, day);
        } catch (Exception ex) {
            ex.getStackTrace();
            return null;
        }
        return calendar;
    }

    /**
     *
     * @param data a String which contains: "2021-04-18 01:00:00",
     * @return
     */
    public Calendar getDateFromModuleActivityData(String data) {
        Calendar calendar = Calendar.getInstance();
        if (data.isEmpty())
            return null;
        try {
            String[] date = data.split(" ");
            String[] dateFormat = date[0].split("-");
            String[] hoursFormat = date[1].split(":");
            int year = Integer.parseInt(dateFormat[0]);
            int months = Integer.parseInt(dateFormat[1])-1;
            int day = Integer.parseInt(dateFormat[2]);

            int hours = Integer.parseInt(hoursFormat[0]);
            int minutes = Integer.parseInt(hoursFormat[1]);
            int seconds = Integer.parseInt(hoursFormat[2]);

            calendar.set(year, months, day, hours, minutes, seconds);
        } catch (Exception ex) {
            ex.getStackTrace();
            return null;
        }
        return calendar;
    }

    /**
     * Get Calendar from now
     * @return Calendar
     */
    public Calendar getNow() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        return calendar;
    }

    public Date getFirstDayOfWeek() {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return date.getTime();
    }

    public Date getFirstDayOfWeek(int year, int month, int day) {
        Calendar date = Calendar.getInstance();
        date.set(year, month, day);
        date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return date.getTime();
    }

    public Date getLastDayOfWeek() {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        date.add(Calendar.DATE, 6);
        return date.getTime();
    }

    public Date getLastDayOfWeek(int year, int month, int day) {
        Calendar date = Calendar.getInstance();
        date.set(year, month, day);
        date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        date.add(Calendar.DATE, 6);
        return date.getTime();
    }


}
