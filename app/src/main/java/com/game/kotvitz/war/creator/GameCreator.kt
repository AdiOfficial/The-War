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
    private var player1WarCard1: ImageView? = null
    private var player2WarCard1: ImageView? = null
    private var player1WarCard2: ImageView? = null
    private var player2WarCard2: ImageView? = null
    private var player1CardsNumber: TextView? = null
    private var player2CardsNumber: TextView? = null
    private var player1Name: TextView? = null
    private var player2Name: TextView? = null
    private var backBitmap: Bitmap? = null
    private var back: BufferedInputStream? = null
    private lateinit var player1drawButton: Button
    private lateinit var player2drawButton: Button
    private lateinit var player1drawWar1Button: Button
    private lateinit var player2drawWar1Button: Button
    private lateinit var player1drawWar2Button: Button
    private lateinit var player2drawWar2Button: Button
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

            player1WarCard1 = context.findViewById(R.id.player1WarCard1)
            player2WarCard1 = context.findViewById(R.id.player2WarCard1)
            player1WarCard2 = context.findViewById(R.id.player1WarCard2)
            player2WarCard2 = context.findViewById(R.id.player2WarCard2)

            player1CardsNumber = context.findViewById(R.id.player1CardsNumber)
            player1CardsNumber!!.text = card!!.player1Deck!!.size.toString()
            player2CardsNumber = context.findViewById(R.id.player2CardsNumber)
            player2CardsNumber!!.text = card!!.player2Deck!!.size.toString()

            player1Name = context.findViewById(R.id.player1Name)
            player2Name = context.findViewById(R.id.player2Name)

            player1drawButton = context.findViewById(R.id.player1drawButton)
            player2drawButton = context.findViewById(R.id.player2drawButton)
            player1drawButton.visibility = View.VISIBLE
            player1drawButton.text = player1Name!!.text
            player2drawButton.text = player2Name!!.text

            player1drawWar1Button = context.findViewById(R.id.player1drawWar1Button)
            player2drawWar1Button = context.findViewById(R.id.player2drawWar1Button)
            player1drawWar1Button.visibility = View.INVISIBLE
            player1drawWar1Button.text = player1Name!!.text
            player2drawWar1Button.text = player2Name!!.text

            player1drawWar2Button = context.findViewById(R.id.player1drawWar2Button)
            player2drawWar2Button = context.findViewById(R.id.player2drawWar2Button)
            player1drawWar2Button.visibility = View.INVISIBLE
            player1drawWar2Button.text = player1Name!!.text
            player2drawWar2Button.text = player2Name!!.text

            player1drawButton.setOnClickListener {
                card!!.drawCardForFirstPlayer(0)
                val p1 = card!!.player1Rank!!.id.toString() + card!!.player1Suit!!.name
                setFirstPlayerCardTexture(context, p1, null, null)
            }
            player2drawButton.setOnClickListener {
                card!!.drawCardForSecondPlayer(0)
                val p2 = card!!.player2Rank!!.id.toString() + card!!.player2Suit!!.name
                setSecondPlayerCardTexture(context, p2, null, null)
                card!!.compareCards()
                updateGameState(card!!, context)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun updateGameState(card: Card, context: Context) {
        player1CardsNumber!!.text = card.player1Count.toString()
        player2CardsNumber!!.text = card.player2Count.toString()
        val p1 = card.player1Rank!!.id.toString() + card.player1Suit!!.name
        val p2 = card.player2Rank!!.id.toString() + card.player2Suit!!.name
        val p1War: String?
        val p1War2: String?
        val p2War: String?
        val p2War2: String?
        if(card.player1Rank == card.player2Rank) {
            if(card.player1WarRank1 == card.player1WarRank2) {
                p1War = card.player1WarRank1!!.id.toString() + card.player1WarSuit1!!.name
                p1War2 = card.player1WarRank2!!.id.toString() + card.player1WarRank2!!.name
                p2War = card.player2WarRank1!!.id.toString() + card.player2WarSuit1!!.name
                p2War2 = card.player2WarRank2!!.id.toString() + card.player2WarRank2!!.name
                setFirstPlayerCardTexture(context, p1, p1War, p1War2)
                setSecondPlayerCardTexture(context, p2, p2War, p2War2)
            } else {
                p1War = card.player1WarRank1!!.id.toString() + card.player1WarSuit1!!.name
                p2War = card.player2WarRank1!!.id.toString() + card.player2WarSuit1!!.name
                setFirstPlayerCardTexture(context, p1, p1War, null)
                setSecondPlayerCardTexture(context, p2, p2War, null)
            }
        } else {
            setFirstPlayerCardTexture(context, p1, null, null)
            setSecondPlayerCardTexture(context, p2, null, null)
        }
    }

    private fun setFirstPlayerCardTexture( context: Context, p1: String?, p1War: String?, p1War2: String?) {
        val assets = context.assets
        val inputStream1: BufferedInputStream
        val inputStream2: BufferedInputStream
        val inputStream3: BufferedInputStream
        var bitmap: Bitmap?
        try {
            inputStream1 = BufferedInputStream(assets.open("$p1.png"))
            if (p1War == null && p1War2 == null) {
                bitmap = BitmapFactory.decodeStream(inputStream1)
                player1WarCard1!!.visibility = View.INVISIBLE
                player2WarCard1!!.visibility = View.INVISIBLE
                player1Card!!.setImageBitmap(bitmap)
                player1drawButton.visibility = View.INVISIBLE
                player2drawButton.visibility = View.VISIBLE
            } else if (p1War != null && p1War2 == null) {
                player1WarCard1!!.visibility = View.VISIBLE
                player1WarCard2!!.visibility = View.INVISIBLE
                bitmap = BitmapFactory.decodeStream(inputStream1)
                player1Card!!.setImageBitmap(bitmap)
                inputStream2 = BufferedInputStream(assets.open("$p1War.png"))
                bitmap = BitmapFactory.decodeStream(inputStream2)
                player1WarCard1!!.setImageBitmap(bitmap)
                player1drawButton.visibility = View.INVISIBLE
                player2drawButton.visibility = View.VISIBLE
            } else if (p1War != null && p1War2 != null) {
                player1WarCard1!!.visibility = View.VISIBLE
                player1WarCard2!!.visibility = View.VISIBLE
                bitmap = BitmapFactory.decodeStream(inputStream1)
                player1Card!!.setImageBitmap(bitmap)
                inputStream2 = BufferedInputStream(assets.open("$p1War.png"))
                bitmap = BitmapFactory.decodeStream(inputStream2)
                player1WarCard1!!.setImageBitmap(bitmap)
                inputStream3 = BufferedInputStream(assets.open("$p1War2.png"))
                bitmap = BitmapFactory.decodeStream(inputStream3)
                player1WarCard2!!.setImageBitmap(bitmap)
                player1drawButton.visibility = View.INVISIBLE
                player2drawButton.visibility = View.VISIBLE
            }
        } catch (ex: IOException) {
            print(ex.stackTrace)
        }
    }

    private fun setSecondPlayerCardTexture(context: Context, p2: String?, p2War: String?, p2War2: String?) {
        val assets = context.assets
        val inputStream1: BufferedInputStream
        val inputStream2: BufferedInputStream
        val inputStream3: BufferedInputStream
        var bitmap: Bitmap?
        try {
            inputStream1 = BufferedInputStream(assets.open("$p2.png"))
            if (p2War == null && p2War2 == null) {
                bitmap = BitmapFactory.decodeStream(inputStream1)
                player1WarCard1!!.visibility = View.INVISIBLE
                player2WarCard1!!.visibility = View.INVISIBLE
                player2Card!!.setImageBitmap(bitmap)
                player1drawButton.visibility = View.VISIBLE
                player2drawButton.visibility = View.INVISIBLE
            } else if (p2War != null && p2War2 == null) {
                player2WarCard1!!.visibility = View.VISIBLE
                player2WarCard2!!.visibility = View.INVISIBLE
                bitmap = BitmapFactory.decodeStream(inputStream1)
                player2Card!!.setImageBitmap(bitmap)
                inputStream2 = BufferedInputStream(assets.open("$p2War.png"))
                bitmap = BitmapFactory.decodeStream(inputStream2)
                player2WarCard1!!.setImageBitmap(bitmap)
                player1drawButton.visibility = View.VISIBLE
                player2drawButton.visibility = View.INVISIBLE
            } else if (p2War != null && p2War2 != null) {
                player2WarCard1!!.visibility = View.VISIBLE
                player2WarCard2!!.visibility = View.VISIBLE
                bitmap = BitmapFactory.decodeStream(inputStream1)
                player2Card!!.setImageBitmap(bitmap)
                inputStream2 = BufferedInputStream(assets.open("$p2War.png"))
                bitmap = BitmapFactory.decodeStream(inputStream2)
                player2WarCard1!!.setImageBitmap(bitmap)
                inputStream3 = BufferedInputStream(assets.open("$p2War2.png"))
                bitmap = BitmapFactory.decodeStream(inputStream3)
                player2WarCard2!!.setImageBitmap(bitmap)
                player1drawButton.visibility = View.VISIBLE
                player2drawButton.visibility = View.INVISIBLE
            }
        } catch (ex: IOException) {
            print(ex.stackTrace)
        }
    }
}
