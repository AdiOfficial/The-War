package com.game.kotvitz.war;

import android.content.Intent;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {

    private GameMedia gameMedia = new GameMedia();
    private DialogCreator dialogCreator = new DialogCreator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        ScreenDesigner.callFullScreenMode(getWindow());
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        Button startGameButton = findViewById(R.id.startGameButton);
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMedia.playClickSound(getBaseContext());
                startActivity(new Intent(MainMenuActivity.this, GameActivity.class));
            }
        });
        Button gameRulesButton = findViewById(R.id.gameRulesButton);
        gameRulesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMedia.playClickSound(getBaseContext());
                startActivity(new Intent(MainMenuActivity.this, GameRulesActivity.class));
            }
        });
        Button optionsButton = findViewById(R.id.optionsButton);
        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMedia.playClickSound(getBaseContext());
                startActivity(new Intent(MainMenuActivity.this, OptionsAcitvity.class));
            }
        });
        Button quitButton = findViewById(R.id.quitButton);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMedia.playClickSound(getBaseContext());
                dialogCreator.displayQuitPopup(MainMenuActivity.this);
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
