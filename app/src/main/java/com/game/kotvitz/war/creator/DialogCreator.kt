package com.game.kotvitz.war.creator

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.game.kotvitz.war.GameMedia
import com.game.kotvitz.war.NameValidation
import com.game.kotvitz.war.R
import com.game.kotvitz.war.activity.MainMenuActivity
import kotlin.system.exitProcess

class DialogCreator {

    private var anotherPlayerIsChosen: Boolean = false
    private var firstPlayerName: EditText? = null
    private var secondPlayerName: EditText? = null
    private var player1Name: TextView? = null
    private var player2Name: TextView? = null
    private val gameMedia = GameMedia()
    private val gameCreator = GameCreator()

    fun displayQuitPopup(context: Context) {
        val popup = AlertDialog.Builder(context).create()
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView = inflater.inflate(R.layout.quit_popup, null)
        val yesButton = dialogView.findViewById<Button>(R.id.yesButton)
        yesButton.setOnClickListener {
            gameMedia.playClickSound(context)
            (context as Activity).finishAffinity()
            exitProcess(0)
        }
        val noButton = dialogView.findViewById<Button>(R.id.noButton)
        noButton.setOnClickListener {
            gameMedia.playClickSound(context)
            popup.dismiss()
        }
        popup.setView(dialogView)
        popup.setCancelable(false)
        popup.setCanceledOnTouchOutside(false)
        popup.show()
    }

    fun displayPlayWithPopup(context: Context) {
        val playWithPopup = AlertDialog.Builder(context).create()
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView = inflater.inflate(R.layout.play_with_popup, null)
        val playWithPlayerButton = dialogView.findViewById<Button>(R.id.playWithPlayerButton)
        playWithPlayerButton.setOnClickListener {
            gameMedia.playClickSound(context)
            playWithPopup.dismiss()
            anotherPlayerIsChosen = true
            displayChooseFirstPlayerPopup(context)
        }
        val playWithAndroidButton = dialogView.findViewById<Button>(R.id.playWithAndroidButton)
        playWithAndroidButton.setOnClickListener {
            gameMedia.playClickSound(context)
            playWithPopup.dismiss()
            anotherPlayerIsChosen = false
            player2Name = (context as Activity).findViewById(R.id.player2Name)
            player2Name!!.text = "ANDROID"
            displayChooseFirstPlayerPopup(context)
        }
        playWithPopup.setView(dialogView)
        playWithPopup.setCancelable(false)
        playWithPopup.setCanceledOnTouchOutside(false)
        playWithPopup.show()
    }

    private fun displayChooseFirstPlayerPopup(context: Context) {
        val chooseFrstPlrPopup = AlertDialog.Builder(context).create()
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView = inflater.inflate(R.layout.choose_first_player_popup, null)
        firstPlayerName = dialogView.findViewById(R.id.first_player_name)
        val submitButton = dialogView.findViewById<Button>(R.id.buttonSubmit)
        submitButton.setOnClickListener {
            gameMedia.playClickSound(context)
            val nameIsValid = NameValidation.validateName(firstPlayerName!!.text.toString())
            if (nameIsValid) {
                player1Name = (context as Activity).findViewById(R.id.player1Name)
                player1Name!!.text = firstPlayerName!!.text
                chooseFrstPlrPopup.dismiss()
                if (anotherPlayerIsChosen)
                    displayChooseSecondPlayerPopup(context)
                else
                    gameCreator.prepareBoard(context)
            } else
                firstPlayerName!!.error = context.getString(R.string.player_name_error)
        }
        chooseFrstPlrPopup.setView(dialogView)
        chooseFrstPlrPopup.setCancelable(false)
        chooseFrstPlrPopup.setCanceledOnTouchOutside(false)
        chooseFrstPlrPopup.show()
    }

    private fun displayChooseSecondPlayerPopup(context: Context) {
        val chooseScndPlrPopup = AlertDialog.Builder(context).create()
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView = inflater.inflate(R.layout.choose_second_player_popup, null)
        secondPlayerName = dialogView.findViewById(R.id.second_player_name)
        chooseScndPlrPopup.setOnDismissListener { gameCreator.prepareBoard(context) }
        val sumbitButton = dialogView.findViewById<Button>(R.id.buttonSubmit2)
        sumbitButton.setOnClickListener {
            gameMedia.playClickSound(context)
            val nameIsValid = NameValidation.validateName(secondPlayerName!!.text.toString())
            if (nameIsValid) {
                player2Name = (context as Activity).findViewById(R.id.player2Name)
                player2Name!!.text = secondPlayerName!!.text
                chooseScndPlrPopup.dismiss()
            } else {
                secondPlayerName!!.error = context.getString(R.string.player_name_error)
            }
        }
        chooseScndPlrPopup.setView(dialogView)
        chooseScndPlrPopup.setCancelable(false)
        chooseScndPlrPopup.setCanceledOnTouchOutside(false)
        chooseScndPlrPopup.show()
    }

    fun finishGamePopup(context: Context) {
        val finishGamePopup = AlertDialog.Builder(context).create()
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView = inflater.inflate(R.layout.finish_game_popup, null)
        val yesButton = dialogView.findViewById<Button>(R.id.yesButton2)
        yesButton.setOnClickListener {
            gameMedia.playClickSound(context)
            (context as Activity).finish()
            context.startActivity(Intent(context, MainMenuActivity::class.java))
        }
        val noButton = dialogView.findViewById<Button>(R.id.noButton2)
        noButton.setOnClickListener {
            gameMedia.playClickSound(context)
            finishGamePopup.dismiss()
        }
        finishGamePopup.setView(dialogView)
        finishGamePopup.setCancelable(false)
        finishGamePopup.setCanceledOnTouchOutside(false)
        finishGamePopup.show()
    }

    fun restartGamePopup(context: Context) {
        val restartGamePopup = AlertDialog.Builder(context).create()
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView = inflater.inflate(R.layout.restart_game_popup, null)
        val yesButton = dialogView.findViewById<Button>(R.id.yesButton3)
        yesButton.setOnClickListener {
            gameMedia.playClickSound(context)
            (context as Activity).recreate()
        }
        val noButton = dialogView.findViewById<Button>(R.id.noButton3)
        noButton.setOnClickListener {
            gameMedia.playClickSound(context)
            restartGamePopup.dismiss()
        }
        restartGamePopup.setView(dialogView)
        restartGamePopup.setCancelable(false)
        restartGamePopup.setCanceledOnTouchOutside(false)
        restartGamePopup.show()
    }
}
