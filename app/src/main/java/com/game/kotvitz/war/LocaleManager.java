package com.game.kotvitz.war;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;

public class LocaleManager {

    public String getLocale(Intent intent) {
        String lang = intent.getStringExtra("lang");
        return lang;
    }

    public void setLocale(String localeName, Intent intent, Resources res) {
        Locale myLocale = new Locale(localeName);
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        intent.putExtra("lang", localeName);
    }

    public void setLocaleOnConfigurationChanged(Intent intent, Context context) {
        String lang = getLocale(intent);
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }
}
