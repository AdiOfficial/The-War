package com.game.kotvitz.war.activity

import android.media.AudioManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.game.kotvitz.war.GameMedia
import com.game.kotvitz.war.R
import com.game.kotvitz.war.ScreenDesigner

class GameRulesActivity : AppCompatActivity() {

    private val gameMedia = GameMedia()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_rules)
        ScreenDesigner.callFullScreenMode(window)
        volumeControlStream = AudioManager.STREAM_MUSIC
        val rulesText = findViewById<TextView>(R.id.rulesText)
        rulesText.movementMethod = ScrollingMovementMethod()
        val backButton = findViewById<Button>(R.id.backButtonRules)
        backButton.setOnClickListener {
            gameMedia.playClickSound(baseContext)
            onBackPressed()
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }
}
