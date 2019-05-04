package com.game.kotvitz.war;

import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.Objects;

public class GameActivity extends AppCompatActivity {

    private PopupCreator popupCreator = new PopupCreator();
    private GameMedia gameMedia = new GameMedia();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ScreenDesigner.callFullScreenMode(getWindow());
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        Toolbar gameToolbar = findViewById(R.id.gameToolbar);
        setSupportActionBar(gameToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                popupCreator.displayPlayWithPopup(GameActivity.this);
            }
        }, 100L);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.restart_game:
                gameMedia.playClickSound(getBaseContext());
                popupCreator.restartGamePopup(GameActivity.this);
                return true;
            case R.id.end_game:
                gameMedia.playClickSound(getBaseContext());
                popupCreator.finishGamePopup(GameActivity.this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
