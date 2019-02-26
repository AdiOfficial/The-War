package com.game.kotvitz.war;

import android.view.View;
import android.view.Window;

public abstract class ScreenDesigner {

    public static void callFullScreenMode(Window window) {
        View decorView = window.getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }
}
