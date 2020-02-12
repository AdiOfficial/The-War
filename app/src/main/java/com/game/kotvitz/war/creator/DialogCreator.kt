package com.game.kotvitz.war.creator

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.game.kotvitz.war.GameMedia
import com.game.kotvitz.war.NameValidation
import com.game.kotvitz.war.R
import com.game.kotvitz.war.activity.MainMenuActivity
import kotlin.system.exitProcess

class DialogCreator(private val context: Context) {

    private var firstPlayerName: EditText? = null
    private var secondPlayerName: EditText? = null
    private var player1Name: TextView? = null
    private var player2Name: TextView? = null
    private val gameMedia = GameMedia(context)
    private val gameCreator = GameCreator(context)

    fun displayQuitPopup() {
        val popup = AlertDialog.Builder(context).create()
        val dialogView = View.inflate(context, R.layout.quit_popup, null)
        val yesButton = dialogView.findViewById<Button>(R.id.yesButton)
        yesButton.setOnClickListener {
            gameMedia.playClickSound()
            (context as Activity).finishAffinity()
            exitProcess(0)
        }
        val noButton = dialogView.findViewById<Button>(R.id.noButton)
        noButton.setOnClickListener {
            gameMedia.playClickSound()
            popup.dismiss()
        }
        popup.setView(dialogView)
        popup.setCancelable(false)
        popup.setCanceledOnTouchOutside(false)
        popup.show()
    }

    fun displayChooseFirstPlayerPopup() {
        val chooseFrstPlrPopup = AlertDialog.Builder(context).create()
        val dialogView = View.inflate(context, R.layout.choose_first_player_popup,null)
        firstPlayerName = dialogView.findViewById(R.id.first_player_name)
        val submitButton = dialogView.findViewById<Button>(R.id.buttonSubmit)
        submitButton.setOnClickListener {
            gameMedia.playClickSound()
            val nameIsValid = NameValidation.validateName(firstPlayerName!!.text.toString())
            if (nameIsValid) {
                player1Name = (context as Activity).findViewById(R.id.player1Name)
                player1Name!!.text = firstPlayerName!!.text
                chooseFrstPlrPopup.dismiss()
                displayChooseSecondPlayerPopup()
            } else
                firstPlayerName!!.error = context.getString(R.string.player_name_error)
        }
        chooseFrstPlrPopup.setView(dialogView)
        chooseFrstPlrPopup.setCancelable(false)
        chooseFrstPlrPopup.setCanceledOnTouchOutside(false)
        chooseFrstPlrPopup.show()
    }

    private fun displayChooseSecondPlayerPopup() {
        val chooseScndPlrPopup = AlertDialog.Builder(context).create()
        val dialogView = View.inflate(context, R.layout.choose_second_player_popup, null)
        secondPlayerName = dialogView.findViewById(R.id.second_player_name)
        chooseScndPlrPopup.setOnDismissListener { gameCreator.prepareBoard() }
        val sumbitButton = dialogView.findViewById<Button>(R.id.buttonSubmit2)
        sumbitButton.setOnClickListener {
            gameMedia.playClickSound()
            val nameIsValid = NameValidation.validateName(secondPlayerName!!.text.toString())
            if (nameIsValid) {
                player2Name = (context as Activity).findViewById(R.id.player2Name)
                player2Name!!.text = secondPlayerName!!.text
                chooseScndPlrPopup.dismiss()
                gameCreator.prepareBoard()
            } else {
                secondPlayerName!!.error = context.getString(R.string.player_name_error)
            }
        }
        chooseScndPlrPopup.setView(dialogView)
        chooseScndPlrPopup.setCancelable(false)
        chooseScndPlrPopup.setCanceledOnTouchOutside(false)
        chooseScndPlrPopup.show()
    }

    fun finishGamePopup() {
        val finishGamePopup = AlertDialog.Builder(context).create()
        val dialogView = View.inflate(context, R.layout.finish_game_popup, null)
        val yesButton = dialogView.findViewById<Button>(R.id.yesButton2)
        yesButton.setOnClickListener {
            gameMedia.playClickSound()
            (context as Activity).finish()
            context.startActivity(Intent(context, MainMenuActivity::class.java))
        }
        val noButton = dialogView.findViewById<Button>(R.id.noButton2)
        noButton.setOnClickListener {
            gameMedia.playClickSound()
            finishGamePopup.dismiss()
        }
        finishGamePopup.setView(dialogView)
        finishGamePopup.setCancelable(false)
        finishGamePopup.setCanceledOnTouchOutside(false)
        finishGamePopup.show()
    }

    fun restartGamePopup() {
        val restartGamePopup = AlertDialog.Builder(context).create()
        val dialogView = View.inflate(context, R.layout.restart_game_popup, null)
        val yesButton = dialogView.findViewById<Button>(R.id.yesButton3)
        yesButton.setOnClickListener {
            gameMedia.playClickSound()
            (context as Activity).recreate()
        }
        val noButton = dialogView.findViewById<Button>(R.id.noButton3)
        noButton.setOnClickListener {
            gameMedia.playClickSound()
            restartGamePopup.dismiss()
        }
        restartGamePopup.setView(dialogView)
        restartGamePopup.setCancelable(false)
        restartGamePopup.setCanceledOnTouchOutside(false)
        restartGamePopup.show()
    }
}
