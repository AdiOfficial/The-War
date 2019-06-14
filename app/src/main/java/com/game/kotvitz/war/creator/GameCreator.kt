package com.game.kotvitz.war.creator

import android.app.Activity
import android.content.ClipData
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.constraint.ConstraintLayout
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.game.kotvitz.war.R
import com.game.kotvitz.war.model.Card
import java.io.BufferedInputStream
import java.io.IOException

class GameCreator {

    private var player1Card: ImageView? = null
    private var player2Card: ImageView? = null
    private var player1TakenCard: LinearLayout? = null
    private var player2TakenCard: LinearLayout? = null
    private var player1CardsNumber: TextView? = null
    private var player2CardsNumber: TextView? = null
    private var backBitmap: Bitmap? = null
    private var back: BufferedInputStream? = null
    private val layoutParams: ConstraintLayout.LayoutParams? = null
    private var card: Card? = null

    private inner class CardTouchListener : View.OnTouchListener {
        override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
            return if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                val data = ClipData.newPlainText("", "")
                val shadowBuilder = View.DragShadowBuilder(
                        view)
                view.startDrag(data, shadowBuilder, view, 0)
                true
            } else {
                false
            }
        }
    }

    internal inner class CardDragListener : View.OnDragListener {

        override fun onDrag(v: View, event: DragEvent): Boolean {
            if (event.action == DragEvent.ACTION_DROP) {
                val view = event.localState as View
                val owner = view.parent as ViewGroup
                owner.removeView(view)
                val container = v as LinearLayout
                container.addView(view)
                view.visibility = View.VISIBLE
            }
            return true
        }
    }

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

            player1TakenCard = context.findViewById(R.id.player1TakenCard)
            player2TakenCard = context.findViewById(R.id.player2TakenCard)

            player1Card!!.setOnTouchListener(CardTouchListener())
            player1TakenCard!!.setOnDragListener(CardDragListener())

            player2Card!!.setOnTouchListener(CardTouchListener())
            player2TakenCard!!.setOnDragListener(CardDragListener())
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
