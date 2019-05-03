package com.game.kotvitz.war;

import android.content.Context;
import android.media.MediaPlayer;

public class GameMedia {

    public void playClickSound(Context context) {
        final MediaPlayer mp = MediaPlayer.create(context, R.raw.click);
        mp.start();
    }

    public void playSwitchSound(Context context) {
        final MediaPlayer mp = MediaPlayer.create(context, R.raw.switchie);
        mp.start();
    }
}
