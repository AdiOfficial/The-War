package com.game.kotvitz.war

import android.content.Context
import android.media.MediaPlayer

class GameMedia(private val context: Context) {

    fun playClickSound() {
        val mp = MediaPlayer.create(context, R.raw.click)
        mp.start()
    }

    fun playSwitchSound() {
        val mp = MediaPlayer.create(context, R.raw.switchie)
        mp.start()
    }

    fun playCardSlideSound() {
        val mp = MediaPlayer.create(context, R.raw.card_slide)
        mp.start()
    }

    fun playCardPlaceSound() {
        val mp = MediaPlayer.create(context, R.raw.card_place)
        mp.start()
    }
}
