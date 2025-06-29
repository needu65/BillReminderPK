package com.example.billreminderpk.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    public static String formatDate(Date date, String pattern) {
    SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
    return sdf.format(date);
    }

    public static int daysBetweenTodayAnd(Date dueDate) {
        long diff = dueDate.getTime() - new Date().getTime();
        return (int) (diff / (1000 * 60 * 60 * 24));
    }
}