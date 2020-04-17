package org.dpk.d2dfc.utils;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class TimeHandler {


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




}
