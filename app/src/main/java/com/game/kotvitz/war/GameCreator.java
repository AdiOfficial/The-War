package com.game.kotvitz.war;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.TextView;

import com.game.kotvitz.war.model.Card;

import java.io.BufferedInputStream;
import java.io.IOException;

public class GameCreator {

    private ImageView player1Card, player2Card;
    private TextView player1Cards, player2Cards;
    private Bitmap backBitmap;
    private BufferedInputStream back;
    private Card card;

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

            player1Cards = ((Activity) context).findViewById(R.id.player1Cards);
            player2Cards = ((Activity) context).findViewById(R.id.player2Cards);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
