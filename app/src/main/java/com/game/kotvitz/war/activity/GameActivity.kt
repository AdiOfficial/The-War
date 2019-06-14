package com.game.kotvitz.war.activity

import android.media.AudioManager
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.game.kotvitz.war.GameMedia
import com.game.kotvitz.war.R
import com.game.kotvitz.war.ScreenDesigner
import com.game.kotvitz.war.creator.DialogCreator
import com.game.kotvitz.war.creator.GameCreator
import java.util.*

class GameActivity : AppCompatActivity() {

    private val dialogCreator = DialogCreator()
    private val gameMedia = GameMedia()
    private var player1Name: TextView? = null
    private var player2Name: TextView? = null
    private val card2: ImageView? = null
    private val card3: ImageView? = null
    private val gameCreator = GameCreator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        ScreenDesigner.callFullScreenMode(window)
        volumeControlStream = AudioManager.STREAM_MUSIC
        val gameToolbar = findViewById<Toolbar>(R.id.gameToolbar)
        setSupportActionBar(gameToolbar)
        player1Name = findViewById(R.id.player1Name)
        player2Name = findViewById(R.id.player2Name)
        Objects.requireNonNull<ActionBar>(supportActionBar).setDisplayShowTitleEnabled(false)
        Handler().postDelayed({ dialogCreator.displayPlayWithPopup(this@GameActivity) }, 100L)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.game_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.restart_game -> {
                gameMedia.playClickSound(baseContext)
                dialogCreator.restartGamePopup(this@GameActivity)
                true
            }
            R.id.end_game -> {
                gameMedia.playClickSound(baseContext)
                dialogCreator.finishGamePopup(this@GameActivity)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
