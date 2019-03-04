package com.game.kotvitz.war;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;

public abstract class LocaleManager {

    public static void setLocale(String localeName, Intent intent, Resources res) {
        Locale myLocale = new Locale(localeName);
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        intent.putExtra("lang", localeName);
    }
}
