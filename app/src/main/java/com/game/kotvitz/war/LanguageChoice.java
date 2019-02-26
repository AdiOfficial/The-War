package com.game.kotvitz.war;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import java.util.Locale;

public class LanguageChoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language_choice);
        ScreenDesigner.callFullScreenMode(getWindow());
        final ImageView langEn = findViewById(R.id.langEn);
        final Intent mainMenu = new Intent(this, MainMenuActivity.class);
        langEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("en-us", mainMenu, getResources());
                startActivity(mainMenu);
            }
        });
        final ImageView langPol = findViewById(R.id.langPol);
        langPol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("pl", mainMenu, getResources());
                startActivity(mainMenu);
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void setLocale(String localeName, Intent intent, Resources res) {
        Locale myLocale = new Locale(localeName);
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        intent.putExtra("lang", localeName);
    }
}
