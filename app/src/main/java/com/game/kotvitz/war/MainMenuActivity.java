package com.game.kotvitz.war;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        ScreenDesigner.callFullScreenMode(getWindow());
        //mp.start();
        Button startGameButton = findViewById(R.id.startGameButton);
        final Intent gameActivity = new Intent(this, GameActivity.class);
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(gameActivity);
            }
        });
        Button gameRulesButton = findViewById(R.id.gameRulesButton);
        final Intent gameRulesActivity = new Intent(this, GameRulesActivity.class);
        gameRulesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(gameRulesActivity);
            }
        });
        Button optionsButton = findViewById(R.id.optionsButton);
        final Intent optionsActivity = new Intent(this, OptionsAcitvity.class);
        optionsButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(optionsActivity);
            }
        });
        Button quitButton = findViewById(R.id.quitButton);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayQuitPopup();
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    private void displayQuitPopup() {
        View layout = getLayoutInflater().inflate(R.layout.quit_popup,null);
        final PopupWindow popup = new PopupWindow(this);
        popup.setContentView(layout);
        popup.setFocusable(false);
        popup.setWidth(864);
        popup.setHeight(538);
        popup.showAtLocation(layout, Gravity.CENTER, 0, 0);
        popup.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        Button yesButton = layout.findViewById(R.id.yesButton);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                System.exit(0);
            }
        });
        Button noButton = layout.findViewById(R.id.noButton);
        noButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View layout) {
                popup.dismiss();
            }
        });
    }
}
