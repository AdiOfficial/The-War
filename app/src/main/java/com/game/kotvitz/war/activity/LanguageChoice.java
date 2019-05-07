package com.game.kotvitz.war.activity;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.game.kotvitz.war.GameMedia;
import com.game.kotvitz.war.LocaleManager;
import com.game.kotvitz.war.R;
import com.game.kotvitz.war.ScreenDesigner;


public class LanguageChoice extends AppCompatActivity {

    private GameMedia gameMedia = new GameMedia();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language_choice);
        ScreenDesigner.callFullScreenMode(getWindow());
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        final ImageView langEn = findViewById(R.id.langEn);
        final Intent mainMenu = new Intent(this, MainMenuActivity.class);
        langEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMedia.playClickSound(getBaseContext());
                LocaleManager.setLocale("en-us", mainMenu, getResources());
                startActivity(mainMenu);
            }
        });
        final ImageView langPol = findViewById(R.id.langPol);
        langPol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMedia.playClickSound(getBaseContext());
                LocaleManager.setLocale("pl", mainMenu, getResources());
                startActivity(mainMenu);
            }
        });
    }

    @Override
    public void onBackPressed() {
        gameMedia.playClickSound(getBaseContext());
        moveTaskToBack(true);
    }
}
