package com.game.kotvitz.war;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

public class GameActivity extends AppCompatActivity {

    private boolean anotherPlayerIsChosen;

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

    private void displayPlayWithPopup() {
        final AlertDialog playWithPopup = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.play_with_popup,null);
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
                TextView player2Name = findViewById(R.id.player2Name);
                player2Name.setText("ANDROID");
                displayChooseFirstPlayerPopup();
            }
        });
        playWithPopup.setView(dialogView);
        playWithPopup.setCanceledOnTouchOutside(false);
        playWithPopup.show();
    }

    private void displayChooseFirstPlayerPopup() {
        final AlertDialog chooseFrstPlrPopup = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.choose_first_player_popup, null);
        final EditText firstPlayerName = dialogView.findViewById(R.id.first_player_name);
        Button sumbitButton = dialogView.findViewById(R.id.buttonSubmit);
        sumbitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView player1Name = findViewById(R.id.player1Name);
                player1Name.setText(firstPlayerName.getText());
                chooseFrstPlrPopup.dismiss();
                if (anotherPlayerIsChosen)
                    displayChooseSecondPlayerPopup();
            }
        });
        chooseFrstPlrPopup.setView(dialogView);
        chooseFrstPlrPopup.setCanceledOnTouchOutside(false);
        chooseFrstPlrPopup.show();
    }

    private void displayChooseSecondPlayerPopup() {
        final AlertDialog chooseScndPlrPopup = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.choose_second_player_popup, null);
        final EditText secondPlayerName = dialogView.findViewById(R.id.second_player_name);
        Button sumbitButton = dialogView.findViewById(R.id.buttonSubmit2);
        sumbitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView player2Name = findViewById(R.id.player2Name);
                player2Name.setText(secondPlayerName.getText());
                chooseScndPlrPopup.dismiss();
            }
        });
        chooseScndPlrPopup.setView(dialogView);
        chooseScndPlrPopup.setCanceledOnTouchOutside(false);
        chooseScndPlrPopup.show();
    }
}
