package com.game.kotvitz.war.activity

import android.content.Intent
import android.media.AudioManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.game.kotvitz.war.GameMedia
import com.game.kotvitz.war.R
import com.game.kotvitz.war.creator.DialogCreator

class MainMenuActivity : AppCompatActivity() {

    private val gameMedia = GameMedia()
    private val dialogCreator = DialogCreator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)
        volumeControlStream = AudioManager.STREAM_MUSIC
        val startGameButton = findViewById<Button>(R.id.startGameButton)
        startGameButton.setOnClickListener {
            gameMedia.playClickSound(baseContext)
            startActivity(Intent(this@MainMenuActivity, GameActivity::class.java))
        }
        val gameRulesButton = findViewById<Button>(R.id.gameRulesButton)
        gameRulesButton.setOnClickListener {
            gameMedia.playClickSound(baseContext)
            startActivity(Intent(this@MainMenuActivity, GameRulesActivity::class.java))
        }
        val optionsButton = findViewById<Button>(R.id.optionsButton)
        optionsButton.setOnClickListener {
            gameMedia.playClickSound(baseContext)
            startActivity(Intent(this@MainMenuActivity, OptionsAcitvity::class.java))
        }
        val quitButton = findViewById<Button>(R.id.quitButton)
        quitButton.setOnClickListener {
            gameMedia.playClickSound(baseContext)
            dialogCreator.displayQuitPopup(this@MainMenuActivity)
        }
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
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
