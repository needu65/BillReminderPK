package com.example.billreminderpk;

import android.app.Application;

public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        setAppLanguage();
    }

    public static Context getAppContext() {
        return context;
    }

    private void setAppLanguage() {
        String lang = SharedPrefsHelper.getLanguage(this);
        updateResources(this, lang);
    }

    private static void updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale);
        } else {
            config.locale = locale;
        }

        DisplayMetrics dm = res.getDisplayMetrics();
        res.updateConfiguration(config, dm);
    }

    public static void setLanguage(Context context, String languageCode) {
        SharedPrefsHelper.setLanguage(context, languageCode);
        updateResources(context, languageCode);
    }
}