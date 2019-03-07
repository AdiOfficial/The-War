package com.game.kotvitz.war;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

public class GameActivity extends AppCompatActivity {

    private boolean anotherPlayerIsChosen;
    private EditText firstPlayerName;
    private EditText secondPlayerName;
    private TextView player1Name;
    private TextView player2Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ScreenDesigner.callFullScreenMode(getWindow());
        Toolbar gameToolbar = findViewById(R.id.gameToolbar);
        setSupportActionBar(gameToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                displayPlayWithPopup();
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
                return true;
            case R.id.end_game:
                finishGamePopup();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void displayPlayWithPopup() {
        final AlertDialog playWithPopup = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.play_with_popup, null);
        Button playWithPlayerButton = dialogView.findViewById(R.id.playWithPlayerButton);
        playWithPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playWithPopup.dismiss();
                anotherPlayerIsChosen = true;
                displayChooseFirstPlayerPopup();
            }
        });
        Button playWithAndroidButton = dialogView.findViewById(R.id.playWithAndroidButton);
        playWithAndroidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playWithPopup.dismiss();
                anotherPlayerIsChosen = false;
                player2Name = findViewById(R.id.player2Name);
                player2Name.setText("ANDROID");
                displayChooseFirstPlayerPopup();
            }
        });
        playWithPopup.setView(dialogView);
        playWithPopup.setCancelable(false);
        playWithPopup.setCanceledOnTouchOutside(false);
        playWithPopup.show();
    }

    private void displayChooseFirstPlayerPopup() {
        final AlertDialog chooseFrstPlrPopup = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.choose_first_player_popup, null);
        firstPlayerName = dialogView.findViewById(R.id.first_player_name);
        Button sumbitButton = dialogView.findViewById(R.id.buttonSubmit);
        sumbitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean nameIsValid = NameValidation.validateName(firstPlayerName.getText().toString());
                if (nameIsValid) {
                    player1Name = findViewById(R.id.player1Name);
                    player1Name.setText(firstPlayerName.getText());
                    chooseFrstPlrPopup.dismiss();
                    if (anotherPlayerIsChosen)
                        displayChooseSecondPlayerPopup();
                } else {
                    firstPlayerName.setError(getString(R.string.player_name_error));
                }
            }
        });
        chooseFrstPlrPopup.setView(dialogView);
        chooseFrstPlrPopup.setCancelable(false);
        chooseFrstPlrPopup.setCanceledOnTouchOutside(false);
        chooseFrstPlrPopup.show();
    }

    private void displayChooseSecondPlayerPopup() {
        final AlertDialog chooseScndPlrPopup = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.choose_second_player_popup, null);
        secondPlayerName = dialogView.findViewById(R.id.second_player_name);
        Button sumbitButton = dialogView.findViewById(R.id.buttonSubmit2);
        sumbitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean nameIsValid = NameValidation.validateName(secondPlayerName.getText().toString());
                if (nameIsValid) {
                    player2Name = findViewById(R.id.player2Name);
                    player2Name.setText(secondPlayerName.getText());
                    chooseScndPlrPopup.dismiss();
                } else {
                    secondPlayerName.setError(getString(R.string.player_name_error));
                }
            }
        });
        chooseScndPlrPopup.setView(dialogView);
        chooseScndPlrPopup.setCancelable(false);
        chooseScndPlrPopup.setCanceledOnTouchOutside(false);
        chooseScndPlrPopup.show();
    }

    private void finishGamePopup() {
        final AlertDialog playWithPopup = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.finish_game_popup, null);
        Button yesButton = dialogView.findViewById(R.id.yesButton2);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(GameActivity.this, MainMenuActivity.class));
            }
        });
        Button noButton = dialogView.findViewById(R.id.noButton2);
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playWithPopup.dismiss();
            }
        });
        playWithPopup.setView(dialogView);
        playWithPopup.setCancelable(false);
        playWithPopup.setCanceledOnTouchOutside(false);
        playWithPopup.show();
    }
}
