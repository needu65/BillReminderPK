package com.example.billreminderpk.utils;

public class AppConstants {

    // SharedPreferences keys
    public static final String PREFS_NAME = "BillReminderPrefs";
    public static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    public static final String KEY_USER_ID = "userId";
    public static final String KEY_BILLS = "bills";
    public static final String KEY_REMINDER_TIME = "reminderTime";
    public static final String KEY_LANGUAGE = "appLanguage";

    // Notification constants
    public static final String CHANNEL_ID = "bill_reminder_channel";
    public static final String CHANNEL_NAME = "Bill Reminders";
    public static final int NOTIFICATION_ID = 1001;
    public static final int REMINDER_REQUEST_CODE = 2001;

    // Bill types
    public static final String BILL_TYPE_ELECTRICITY = "Electricity";
    public static final String BILL_TYPE_GAS = "Gas";
    public static final String BILL_TYPE_WATER = "Water";
    public static final String BILL_TYPE_LANDLINE = "Landline/Phone";
    public static final String BILL_TYPE_MOBILE = "Postpaid Mobile";

    // Company names for each bill type
    public static final String[] ELECTRICITY_COMPANIES = {"WAPDA", "K-Electric"};
    public static final String[] GAS_COMPANIES = {"SNGPL", "SSGC"};
    public static final String[] WATER_COMPANIES = {
        "BWASA", "FWASA", "GWASA", "HYDERABAD WASA", 
        "LWASA", "MWASA", "QWASA", "RWASA", 
        "WSSC SWAT", "WSSP", "AJK-Barkiyat", "GB-Barkiyat"
    };
    public static final String[] LANDLINE_COMPANIES = {"PTCL"};
    public static final String[] MOBILE_COMPANIES = {"Jazz", "Zong", "Ufone", "Telenor"};

    // Intent extras
    public static final String EXTRA_BILL_ID = "bill_id";
    public static final String EXTRA_EDIT_BILL = "edit_bill";
    public static final String EXTRA_SHOW_UPCOMING = "show_upcoming";

    // Date formats
    public static final String DATE_FORMAT_DISPLAY = "dd MMM yyyy";
    public static final String DATE_FORMAT_STORAGE = "yyyy-MM-dd";
    public static final String TIME_FORMAT_12HR = "hh:mm a";

    // Default reminder time (9:00 AM)
    public static final String DEFAULT_REMINDER_TIME = "09:00";

    // Language codes
    public static final String LANG_ENGLISH = "en";
    public static final String LANG_URDU = "ur";

    // Validation constants
    public static final int MIN_PASSWORD_LENGTH = 6;
    public static final int DAYS_BEFORE_REMINDER = 3;
}