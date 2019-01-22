package com.game.kotvitz.war;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
