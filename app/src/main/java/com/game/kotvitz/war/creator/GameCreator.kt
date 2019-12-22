package com.game.kotvitz.war.creator

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.game.kotvitz.war.R
import com.game.kotvitz.war.model.Card
import java.io.BufferedInputStream
import java.io.IOException

class GameCreator {

    private var player1Card: ImageView? = null
    private var player2Card: ImageView? = null
    private var player1CardsNumber: TextView? = null
    private var player2CardsNumber: TextView? = null
    private var player1Name: TextView? = null
    private var backBitmap: Bitmap? = null
    private var back: BufferedInputStream? = null
    private lateinit var player1MoveButton: Button
    private var card: Card? = null

    fun prepareBoard(context: Context) {
        val assetManager = context.assets
        try {
            back = BufferedInputStream(assetManager.open("back.png"))
            backBitmap = BitmapFactory.decodeStream(back)

            card = Card()
            card!!.divideDeck()

            player1Card = (context as Activity).findViewById(R.id.player1Card)
            player1Card!!.setImageBitmap(backBitmap)

            player2Card = context.findViewById(R.id.player2Card)
            player2Card!!.setImageBitmap(backBitmap)

            player1CardsNumber = context.findViewById(R.id.player1CardsNumber)
            player1CardsNumber!!.text = card!!.player1Deck!!.size.toString()
            player2CardsNumber = context.findViewById(R.id.player2CardsNumber)
            player2CardsNumber!!.text = card!!.player2Deck!!.size.toString()

            player1MoveButton = context.findViewById(R.id.player1MoveButton)
            player1Name = context.findViewById(R.id.player1Name)
            player1MoveButton.visibility = View.VISIBLE
            player1MoveButton.text = player1Name!!.text
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
