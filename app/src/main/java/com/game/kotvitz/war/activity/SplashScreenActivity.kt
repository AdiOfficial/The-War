package com.game.kotvitz.war.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity

import com.game.kotvitz.war.R
import com.game.kotvitz.war.ScreenDesigner

class SplashScreenActivity : AppCompatActivity() {
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        ScreenDesigner.callFullScreenMode(window)
        val languageChoice = Intent(this, LanguageChoice::class.java)
        handler.postDelayed({
            finish()
            startActivity(languageChoice)
        }, 5000)
    }
}
