package com.game.kotvitz.war

import android.content.Context
import android.media.MediaPlayer

class GameMedia {

    fun playClickSound(context: Context) {
        val mp = MediaPlayer.create(context, R.raw.click)
        mp.start()
    }

    fun playSwitchSound(context: Context) {
        val mp = MediaPlayer.create(context, R.raw.switchie)
        mp.start()
    }

    fun playCardSlideSound(context: Context) {
        val mp = MediaPlayer.create(context, R.raw.card_slide)
        mp.start()
    }

    fun playCardPlaceSound(context: Context) {
        val mp = MediaPlayer.create(context, R.raw.card_place)
        mp.start()
    }
}
