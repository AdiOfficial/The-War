package com.game.kotvitz.war;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;


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
                LocaleManager.setLocale("en-us", mainMenu, getResources());
                startActivity(mainMenu);
            }
        });
        final ImageView langPol = findViewById(R.id.langPol);
        langPol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocaleManager.setLocale("pl", mainMenu, getResources());
                startActivity(mainMenu);
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
