package com.game.kotvitz.war.activity

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.Switch
import com.game.kotvitz.war.GameMedia
import com.game.kotvitz.war.LocaleManager
import com.game.kotvitz.war.R

class OptionsAcitvity : AppCompatActivity() {

    private var backButton: Button? = null
    private var radioPolish: RadioButton? = null
    private var radioEnglish: RadioButton? = null
    private var soundsSwitch: Switch? = null
    private var audio: AudioManager? = null
    private val gameMedia = GameMedia()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)
        volumeControlStream = AudioManager.STREAM_MUSIC
        audio = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        soundsSwitch = findViewById(R.id.soundsSwitch)
        soundsSwitch!!.setOnClickListener { gameMedia.playSwitchSound(baseContext) }
        checkSoundState()
        checkLanguage()
        backButton = findViewById(R.id.backButtonOptions)
        backButton!!.setOnClickListener {
            gameMedia.playClickSound(baseContext)
            onBackPressed()
        }
        soundsSwitch!!.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                audio!!.setStreamVolume(AudioManager.STREAM_MUSIC, audio!!.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0)
            } else {
                audio!!.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0)
            }
        }
    }

    fun onRadioButtonClicked(view: View) {
        gameMedia.playClickSound(baseContext)
        val checked = (view as RadioButton).isChecked
        when (view.getId()) {
            R.id.pl_lang -> if (checked) {
                val intent = Intent(this, MainMenuActivity::class.java)
                LocaleManager.setLocale("pl", intent, resources)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
            }
            R.id.eng_lang -> if (checked) {
                val intent = Intent(this, MainMenuActivity::class.java)
                LocaleManager.setLocale("en-us", intent, resources)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
            }
        }
    }

    private fun checkLanguage() {
        val currentLocale = resources.configuration.locale
        radioEnglish = findViewById(R.id.eng_lang)
        radioPolish = findViewById(R.id.pl_lang)
        if (currentLocale.language == "en-us")
            radioEnglish!!.isChecked = true
        else if (currentLocale.language == "pl")
            radioPolish!!.isChecked = true
    }

    private fun checkSoundState() {
        val volume = audio!!.getStreamVolume(AudioManager.STREAM_MUSIC)
        soundsSwitch!!.isChecked = volume != 0
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
