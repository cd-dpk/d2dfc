package org.dpk.d2dfc.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeHandler {

    public static long millisecondsEqualDay=86400000, secondsEqualDay=86400;
    public static long factorMillisecond= 1000;
    public static long unixTimeNow(){
        Calendar localCalendar = Calendar.getInstance();
        Date date = localCalendar.getTime();
        return date.getTime()/factorMillisecond;
    }
    public static long addDaysToUnixTime(long unixTime, int days){
        return unixTime+days*secondsEqualDay;
    }
    public static long subtractDaysToUnixTime(long unixTime, int days){
        return unixTime-days*secondsEqualDay;
    }
    public static long unixTimeFrom(String timeString){
        long time = 0;
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(timeString);
            return unixTimeFromDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }
    public static Date dateFromUnixTime(long unixTime){
        Date date = new Date(unixTime*factorMillisecond);
        return date;
    }

    public static long unixTimeFromDate(Date date){
        return date.getTime()/factorMillisecond;
    }

    public static String now(){
        Calendar localCalendar = Calendar.getInstance();
        Date date = localCalendar.getTime();
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp.toString();
    }

    public static int year(){
        Calendar localCalendar = Calendar.getInstance();
        return localCalendar.get(Calendar.YEAR);
    }
    public static int month(){
        Calendar localCalendar = Calendar.getInstance();
        return localCalendar.get(Calendar.MONTH);
    }
    public static int day(){
        Calendar localCalendar = Calendar.getInstance();
        return localCalendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int hour(){
        Calendar localCalendar = Calendar.getInstance();
        return localCalendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int minute(){
        Calendar localCalendar = Calendar.getInstance();
        return localCalendar.get(Calendar.MINUTE);
    }

    public static String toDate(){
        return day()+"/"+(month()+1)+"/"+year();
    }

    public static boolean isSameDate(long time1, long time2) {
        Calendar localCalendar1 = Calendar.getInstance();
        localCalendar1.setTimeInMillis(time1 * factorMillisecond);

        Calendar localCalendar2 = Calendar.getInstance();
        localCalendar1.setTimeInMillis(time2 * factorMillisecond);

        if (localCalendar1.get(Calendar.YEAR) == localCalendar2.get(Calendar.YEAR) &&
                localCalendar1.get(Calendar.MONTH) == localCalendar2.get(Calendar.MONTH) &&
                localCalendar1.get(Calendar.DAY_OF_MONTH) == localCalendar2.get(Calendar.DAY_OF_MONTH)
        ) {
            return true;
        } else
            return false;

    }
}
