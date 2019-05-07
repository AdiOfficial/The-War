package com.game.kotvitz.war.model;

import java.util.ArrayList;
import java.util.Collections;

public class Card {

    private Rank rank;
    private Suit suit;
    private ArrayList<Card> wholeDeck;
    private ArrayList<Card> player1Deck;
    private ArrayList<Card> player2Deck;
    private int rankId;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Card() {
        wholeDeck = new ArrayList<>();
        player1Deck = new ArrayList<>();
        player2Deck = new ArrayList<>();
    }

    public void createDeck() {
        int numberOfRanks = Rank.values().length;
        for (int i = 0; i <= numberOfRanks - 1; i++) {
            rankId = i + 2;
            Card clubCards = new Card(Rank.getRank(rankId), Suit.CLUBS);
            Card diamondCard = new Card(Rank.getRank(rankId), Suit.DIAMONDS);
            Card heartCard = new Card(Rank.getRank(rankId), Suit.HEARTS);
            Card spadeCard = new Card(Rank.getRank(rankId), Suit.SPADES);

            wholeDeck.add(clubCards);
            wholeDeck.add(diamondCard);
            wholeDeck.add(heartCard);
            wholeDeck.add(spadeCard);
        }
        Collections.shuffle(wholeDeck);
    }

    public void divideDeck() {
        createDeck();
        player1Deck = new ArrayList<>(wholeDeck.subList(0, 26));
        player2Deck = new ArrayList<>(wholeDeck.subList(26, 52));
    }
}
