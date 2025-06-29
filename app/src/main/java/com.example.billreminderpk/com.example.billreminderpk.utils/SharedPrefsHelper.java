package com.example.billreminderpk.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPrefsHelper {

    private static final String PREFS_NAME = "BillReminderPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_BILLS = "bills";
    private static final String KEY_REMINDER_TIME = "reminderTime";
    private static final String KEY_LANGUAGE = "appLanguage";

    // User Authentication
    public static boolean isLoggedIn(Context context) {
        return getPrefs(context).getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public static void setLoggedIn(Context context, boolean isLoggedIn, String email) {
        getPrefs(context).edit()
                .putBoolean(KEY_IS_LOGGED_IN, isLoggedIn)
                .putString(KEY_USER_ID, email)
                .apply();
    }

    public static String getCurrentUserId(Context context) {
        return getPrefs(context).getString(KEY_USER_ID, null);
    }

    // Language Management
    public static String getLanguage(Context context) {
        return getPrefs(context).getString(KEY_LANGUAGE, AppConstants.LANG_ENGLISH);
    }

    public static void setLanguage(Context context, String language) {
        getPrefs(context).edit()
                .putString(KEY_LANGUAGE, language)
                .apply();
    }

    // Bill Operations
    public static void saveBill(Context context, Bill bill) {
        List<Bill> bills = getBills(context);
        bills.add(bill);
        saveBills(context, bills);
    }

    public static List<Bill> getBills(Context context) {
        String json = getPrefs(context).getString(KEY_BILLS, null);
        if (json == null) return new ArrayList<>();

        Type type = new TypeToken<List<Bill>>(){}.getType();
        return new Gson().fromJson(json, type);
    }

    public static List<Bill> getBillsForUser(Context context, String userId) {
        List<Bill> allBills = getBills(context);
        List<Bill> userBills = new ArrayList<>();
        
        for (Bill bill : allBills) {
            if (bill.getUserId().equals(userId)) {
                userBills.add(bill);
            }
        }
        return userBills;
    }

    private static void saveBills(Context context, List<Bill> bills) {
        getPrefs(context).edit()
                .putString(KEY_BILLS, new Gson().toJson(bills))
                .apply();
    }

    // Reminder Settings
    public static String getReminderTime(Context context) {
        return getPrefs(context).getString(KEY_REMINDER_TIME, AppConstants.DEFAULT_REMINDER_TIME);
    }

    public static void setReminderTime(Context context, String time) {
        getPrefs(context).edit()
                .putString(KEY_REMINDER_TIME, time)
                .apply();
    }

    // User Registration
    public static boolean verifyUser(Context context, String email, String password) {
        SharedPreferences prefs = getPrefs(context);
        String savedPassword = prefs.getString("USER_PWD_" + email, null);
        return password.equals(savedPassword);
    }

    public static void registerUser(Context context, String email, String password) {
        getPrefs(context).edit()
                .putString("USER_PWD_" + email, password)
                .apply();
    }

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
}