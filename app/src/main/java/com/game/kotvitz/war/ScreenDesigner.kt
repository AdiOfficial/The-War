package com.game.kotvitz.war

import android.view.View
import android.view.Window

object ScreenDesigner {

    fun callFullScreenMode(window: Window) {
        val decorView = window.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        decorView.systemUiVisibility = uiOptions
    }
}
