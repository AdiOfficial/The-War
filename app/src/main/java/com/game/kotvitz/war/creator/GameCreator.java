package com.game.kotvitz.war.creator;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.constraint.ConstraintLayout;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.game.kotvitz.war.R;
import com.game.kotvitz.war.model.Card;

import java.io.BufferedInputStream;
import java.io.IOException;

public class GameCreator {

    private ImageView player1Card, player2Card;
    private LinearLayout player1TakenCard, player2TakenCard;
    private TextView player1CardsNumber, player2CardsNumber;
    private Bitmap backBitmap;
    private BufferedInputStream back;
    private ConstraintLayout.LayoutParams layoutParams;
    private Card card;

    private final class CardTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                        view);
                view.startDrag(data, shadowBuilder, view, 0);
                return true;
            } else {
                return false;
            }
        }
    }

    class CardDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            if (event.getAction() == DragEvent.ACTION_DROP) {
                View view = (View) event.getLocalState();
                ViewGroup owner = (ViewGroup) view.getParent();
                owner.removeView(view);
                LinearLayout container = (LinearLayout) v;
                container.addView(view);
                view.setVisibility(View.VISIBLE);
            }
            return true;
        }
    }

    public void prepareBoard(Context context) {
        final AssetManager assetManager = context.getAssets();
        try {
            back = new BufferedInputStream(assetManager.open("back.png"));
            backBitmap = BitmapFactory.decodeStream(back);

            card = new Card();
            card.divideDeck();

            player1Card = ((Activity) context).findViewById(R.id.player1Card);
            player1Card.setImageBitmap(backBitmap);

            player2Card = ((Activity) context).findViewById(R.id.player2Card);
            player2Card.setImageBitmap(backBitmap);

            player1CardsNumber = ((Activity) context).findViewById(R.id.player1CardsNumber);
            player1CardsNumber.setText(String.valueOf(card.getPlayer1Deck().size()));
            player2CardsNumber = ((Activity) context).findViewById(R.id.player2CardsNumber);
            player2CardsNumber.setText(String.valueOf(card.getPlayer2Deck().size()));

            player1TakenCard = ((Activity) context).findViewById(R.id.player1TakenCard);
            player2TakenCard = ((Activity) context).findViewById(R.id.player2TakenCard);

            player1Card.setOnTouchListener(new CardTouchListener());
            player1TakenCard.setOnDragListener(new CardDragListener());

            player2Card.setOnTouchListener(new CardTouchListener());
            player2TakenCard.setOnDragListener(new CardDragListener());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
