package com.game.kotvitz.war.creator;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.game.kotvitz.war.GameMedia;
import com.game.kotvitz.war.NameValidation;
import com.game.kotvitz.war.R;
import com.game.kotvitz.war.activity.MainMenuActivity;

public class DialogCreator {

    private boolean anotherPlayerIsChosen;
    private EditText firstPlayerName;
    private EditText secondPlayerName;
    private TextView player1Name;
    private TextView player2Name;
    private GameMedia gameMedia = new GameMedia();
    private GameCreator gameCreator = new GameCreator();

    public void displayQuitPopup(final Context context) {
        final AlertDialog popup = new AlertDialog.Builder(context).create();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.quit_popup, null);
        Button yesButton = dialogView.findViewById(R.id.yesButton);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMedia.playClickSound(context);
                ((Activity) context).finishAffinity();
                System.exit(0);
            }
        });
        Button noButton = dialogView.findViewById(R.id.noButton);
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMedia.playClickSound(context);
                popup.dismiss();
            }
        });
        popup.setView(dialogView);
        popup.setCancelable(false);
        popup.setCanceledOnTouchOutside(false);
        popup.show();
    }

    public void displayPlayWithPopup(final Context context) {
        final AlertDialog playWithPopup = new AlertDialog.Builder(context).create();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.play_with_popup, null);
        Button playWithPlayerButton = dialogView.findViewById(R.id.playWithPlayerButton);
        playWithPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMedia.playClickSound(context);
                playWithPopup.dismiss();
                anotherPlayerIsChosen = true;
                displayChooseFirstPlayerPopup(context);
            }
        });
        Button playWithAndroidButton = dialogView.findViewById(R.id.playWithAndroidButton);
        playWithAndroidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMedia.playClickSound(context);
                playWithPopup.dismiss();
                anotherPlayerIsChosen = false;
                player2Name = ((Activity) context).findViewById(R.id.player2Name);
                player2Name.setText("ANDROID");
                displayChooseFirstPlayerPopup(context);
            }
        });
        playWithPopup.setView(dialogView);
        playWithPopup.setCancelable(false);
        playWithPopup.setCanceledOnTouchOutside(false);
        playWithPopup.show();
    }

    public void displayChooseFirstPlayerPopup(final Context context) {
        final AlertDialog chooseFrstPlrPopup = new AlertDialog.Builder(context).create();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.choose_first_player_popup, null);
        firstPlayerName = dialogView.findViewById(R.id.first_player_name);
        Button sumbitButton = dialogView.findViewById(R.id.buttonSubmit);
        sumbitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMedia.playClickSound(context);
                boolean nameIsValid = NameValidation.INSTANCE.validateName(firstPlayerName.getText().toString());
                if (nameIsValid) {
                    player1Name = ((Activity) context).findViewById(R.id.player1Name);
                    player1Name.setText(firstPlayerName.getText());
                    chooseFrstPlrPopup.dismiss();
                    if (anotherPlayerIsChosen)
                        displayChooseSecondPlayerPopup(context);
                    else
                        gameCreator.prepareBoard(context);
                } else {
                    firstPlayerName.setError(context.getString(R.string.player_name_error));
                }
            }
        });
        chooseFrstPlrPopup.setView(dialogView);
        chooseFrstPlrPopup.setCancelable(false);
        chooseFrstPlrPopup.setCanceledOnTouchOutside(false);
        chooseFrstPlrPopup.show();
    }

    public void displayChooseSecondPlayerPopup(final Context context) {
        final AlertDialog chooseScndPlrPopup = new AlertDialog.Builder(context).create();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.choose_second_player_popup, null);
        secondPlayerName = dialogView.findViewById(R.id.second_player_name);
        chooseScndPlrPopup.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                gameCreator.prepareBoard(context);
            }
        });
        Button sumbitButton = dialogView.findViewById(R.id.buttonSubmit2);
        sumbitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMedia.playClickSound(context);
                boolean nameIsValid = NameValidation.INSTANCE.validateName(secondPlayerName.getText().toString());
                if (nameIsValid) {
                    player2Name = ((Activity) context).findViewById(R.id.player2Name);
                    player2Name.setText(secondPlayerName.getText());
                    chooseScndPlrPopup.dismiss();
                } else {
                    secondPlayerName.setError(context.getString(R.string.player_name_error));
                }
            }
        });
        chooseScndPlrPopup.setView(dialogView);
        chooseScndPlrPopup.setCancelable(false);
        chooseScndPlrPopup.setCanceledOnTouchOutside(false);
        chooseScndPlrPopup.show();
    }

    public void finishGamePopup(final Context context) {
        final AlertDialog finishGamePopup = new AlertDialog.Builder(context).create();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.finish_game_popup, null);
        Button yesButton = dialogView.findViewById(R.id.yesButton2);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMedia.playClickSound(context);
                ((Activity) context).finish();
                context.startActivity(new Intent(context, MainMenuActivity.class));
            }
        });
        Button noButton = dialogView.findViewById(R.id.noButton2);
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMedia.playClickSound(context);
                finishGamePopup.dismiss();
            }
        });
        finishGamePopup.setView(dialogView);
        finishGamePopup.setCancelable(false);
        finishGamePopup.setCanceledOnTouchOutside(false);
        finishGamePopup.show();
    }

    public void restartGamePopup(final Context context) {
        final AlertDialog restartGamePopup = new AlertDialog.Builder(context).create();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.restart_game_popup, null);
        Button yesButton = dialogView.findViewById(R.id.yesButton3);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMedia.playClickSound(context);
                ((Activity) context).recreate();
            }
        });
        Button noButton = dialogView.findViewById(R.id.noButton3);
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMedia.playClickSound(context);
                restartGamePopup.dismiss();
            }
        });
        restartGamePopup.setView(dialogView);
        restartGamePopup.setCancelable(false);
        restartGamePopup.setCanceledOnTouchOutside(false);
        restartGamePopup.show();
    }
}
