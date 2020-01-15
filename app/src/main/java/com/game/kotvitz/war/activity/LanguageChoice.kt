package com.game.kotvitz.war.activity

import android.content.Intent
import android.media.AudioManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.game.kotvitz.war.GameMedia
import com.game.kotvitz.war.LocaleManager
import com.game.kotvitz.war.R


class LanguageChoice : AppCompatActivity() {

    private val gameMedia = GameMedia()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.language_choice)
        volumeControlStream = AudioManager.STREAM_MUSIC
        val langEn = findViewById<ImageView>(R.id.langEn)
        val mainMenu = Intent(this, MainMenuActivity::class.java)
        langEn.setOnClickListener {
            gameMedia.playClickSound(baseContext)
            LocaleManager.setLocale("en-us", mainMenu, resources)
            startActivity(mainMenu)
        }
        val langPol = findViewById<ImageView>(R.id.langPol)
        langPol.setOnClickListener {
            gameMedia.playClickSound(baseContext)
            LocaleManager.setLocale("pl", mainMenu, resources)
            startActivity(mainMenu)
        }
    }

    override fun onBackPressed() {
        gameMedia.playClickSound(baseContext)
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
