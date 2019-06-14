package com.game.kotvitz.war.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.game.kotvitz.war.R;
import com.game.kotvitz.war.ScreenDesigner;

public class SplashScreenActivity extends AppCompatActivity {
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        ScreenDesigner.INSTANCE.callFullScreenMode(getWindow());
        final Intent languageChoice = new Intent(this, LanguageChoice.class);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                startActivity(languageChoice);
            }
        }, 5000);
    }
}
