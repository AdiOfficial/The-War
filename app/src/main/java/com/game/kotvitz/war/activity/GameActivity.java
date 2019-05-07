package com.game.kotvitz.war.activity;

import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.game.kotvitz.war.creator.DialogCreator;
import com.game.kotvitz.war.creator.GameCreator;
import com.game.kotvitz.war.GameMedia;
import com.game.kotvitz.war.R;
import com.game.kotvitz.war.ScreenDesigner;

import java.util.Objects;

public class GameActivity extends AppCompatActivity {

    private DialogCreator dialogCreator = new DialogCreator();
    private GameMedia gameMedia = new GameMedia();
    private TextView player1Name;
    private TextView player2Name;
    private ImageView card2;
    private ImageView card3;
    private GameCreator gameCreator = new GameCreator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ScreenDesigner.callFullScreenMode(getWindow());
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        Toolbar gameToolbar = findViewById(R.id.gameToolbar);
        setSupportActionBar(gameToolbar);
        player1Name = findViewById(R.id.player1Name);
        player2Name = findViewById(R.id.player2Name);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialogCreator.displayPlayWithPopup(GameActivity.this);
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
                dialogCreator.restartGamePopup(GameActivity.this);
                return true;
            case R.id.end_game:
                gameMedia.playClickSound(getBaseContext());
                dialogCreator.finishGamePopup(GameActivity.this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
