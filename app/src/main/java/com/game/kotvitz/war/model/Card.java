package com.game.kotvitz.war.model;

import java.util.ArrayList;
import java.util.Collections;

public class Card {

    private Rank rank;
    private Suit suit;
    private ArrayList<Card> wholeDeck;
    private ArrayList<Card> player1Deck;
    private ArrayList<Card> player2Deck;
    private Rank player1Rank, player2Rank;
    private Suit player1Suit, player2Suit;
    private Rank player1WarRank1, player1WarRank2, player1WarRank3;
    private Rank player2WarRank1, player2WarRank2, player2WarRank3;
    private Suit player1WarSuit1, player2WarSuit1, player1WarSuit2, player2WarSuit2, player1WarSuit3, player2WarSuit3;
    private boolean warIsStarting;
    private int rankId;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Card() {
        wholeDeck = new ArrayList<>();
        player1Deck = new ArrayList<>();
        player2Deck = new ArrayList<>();
        warIsStarting = false;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Rank getPlayer1Rank() {
        return player1Rank;
    }

    public void setPlayer1Rank(Rank player1Rank) {
        this.player1Rank = player1Rank;
    }

    public Rank getPlayer2Rank() {
        return player2Rank;
    }

    public void setPlayer2Rank(Rank player2Rank) {
        this.player2Rank = player2Rank;
    }

    public Suit getPlayer1Suit() {
        return player1Suit;
    }

    public void setPlayer1Suit(Suit player1Suit) {
        this.player1Suit = player1Suit;
    }

    public Suit getPlayer2Suit() {
        return player2Suit;
    }

    public void setPlayer2Suit(Suit player2Suit) {
        this.player2Suit = player2Suit;
    }

    public Rank getPlayer1WarRank1() {
        return player1WarRank1;
    }

    public void setPlayer1WarRank1(Rank player1WarRank1) {
        this.player1WarRank1 = player1WarRank1;
    }

    public Rank getPlayer1WarRank2() {
        return player1WarRank2;
    }

    public void setPlayer1WarRank2(Rank player1WarRank2) {
        this.player1WarRank2 = player1WarRank2;
    }

    public Rank getPlayer1WarRank3() {
        return player1WarRank3;
    }

    public void setPlayer1WarRank3(Rank player1WarRank3) {
        this.player1WarRank3 = player1WarRank3;
    }

    public Rank getPlayer2WarRank1() {
        return player2WarRank1;
    }

    public void setPlayer2WarRank1(Rank player2WarRank1) {
        this.player2WarRank1 = player2WarRank1;
    }

    public Rank getPlayer2WarRank2() {
        return player2WarRank2;
    }

    public void setPlayer2WarRank2(Rank player2WarRank2) {
        this.player2WarRank2 = player2WarRank2;
    }

    public Rank getPlayer2WarRank3() {
        return player2WarRank3;
    }

    public void setPlayer2WarRank3(Rank player2WarRank3) {
        this.player2WarRank3 = player2WarRank3;
    }

    public Suit getPlayer1WarSuit1() {
        return player1WarSuit1;
    }

    public void setPlayer1WarSuit1(Suit player1WarSuit1) {
        this.player1WarSuit1 = player1WarSuit1;
    }

    public Suit getPlayer2WarSuit1() {
        return player2WarSuit1;
    }

    public void setPlayer2WarSuit1(Suit player2WarSuit1) {
        this.player2WarSuit1 = player2WarSuit1;
    }

    public Suit getPlayer1WarSuit2() {
        return player1WarSuit2;
    }

    public void setPlayer1WarSuit2(Suit player1WarSuit2) {
        this.player1WarSuit2 = player1WarSuit2;
    }

    public Suit getPlayer2WarSuit2() {
        return player2WarSuit2;
    }

    public void setPlayer2WarSuit2(Suit player2WarSuit2) {
        this.player2WarSuit2 = player2WarSuit2;
    }

    public Suit getPlayer1WarSuit3() {
        return player1WarSuit3;
    }

    public void setPlayer1WarSuit3(Suit player1WarSuit3) {
        this.player1WarSuit3 = player1WarSuit3;
    }

    public Suit getPlayer2WarSuit3() {
        return player2WarSuit3;
    }

    public void setPlayer2WarSuit3(Suit player2WarSuit3) {
        this.player2WarSuit3 = player2WarSuit3;
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

    public void compareCards() {
        player1Rank = player1Deck.get(0).getRank();
        player1Suit = player1Deck.get(0).getSuit();
        player2Rank = player2Deck.get(0).getRank();
        player2Suit = player2Deck.get(0).getSuit();

        if (player1Rank.getId() > player2Rank.getId()) {
            player1Deck.add(player1Deck.get(0));
            player1Deck.remove(0);
            player1Deck.add(player2Deck.get(0));
            player2Deck.remove(0);
        } else if(player1Rank.getId() < player2Rank.getId()) {
            player2Deck.add(player2Deck.get(0));
            player2Deck.remove(0);
            player2Deck.add(player1Deck.get(0));
            player1Deck.remove(0);
        } else {
            warIsStarting = true;
            startWarForFirstTime();
        }

    }

    public void startWarForFirstTime() {
        player1WarRank1 = player1Deck.get(2).getRank();
        player1WarSuit1 = player1Deck.get(2).getSuit();
        player2WarRank1 = player2Deck.get(2).getRank();
        player2WarSuit1 = player2Deck.get(2).getSuit();

        if (player1WarRank1.getId() > player2WarRank1.getId()) {
            player1Deck.add(player2Deck.get(0));
            player2Deck.remove(0);
            player1Deck.add(player2Deck.get(0));
            player2Deck.remove(0);
            player1Deck.add(player2Deck.get(0));
            player2Deck.remove(0);
            player1Deck.add(player1Deck.get(0));
            player1Deck.remove(0);
            player1Deck.add(player1Deck.get(0));
            player1Deck.remove(0);
            player1Deck.add(player1Deck.get(0));
            player1Deck.remove(0);
        } else if (player1WarRank1.getId() < player2WarRank1.getId()) {
            player2Deck.add(player1Deck.get(0));
            player1Deck.remove(0);
            player2Deck.add(player1Deck.get(0));
            player1Deck.remove(0);
            player2Deck.add(player1Deck.get(0));
            player1Deck.remove(0);
            player2Deck.add(player2Deck.get(0));
            player2Deck.remove(0);
            player2Deck.add(player2Deck.get(0));
            player2Deck.remove(0);
            player2Deck.add(player2Deck.get(0));
            player2Deck.remove(0);
        } else {
            warIsStarting = true;
            startWarForSecondTime();
        }
    }

    public void startWarForSecondTime() {
        player1WarRank2 = player1Deck.get(4).getRank();
        player1WarSuit2 = player1Deck.get(4).getSuit();
        player2WarRank2 = player2Deck.get(4).getRank();
        player2WarSuit2 = player2Deck.get(4).getSuit();

        if (player1WarRank2.getId() > player2WarRank2.getId()) {
            player1Deck.add(player2Deck.get(0));
            player2Deck.remove(0);
            player1Deck.add(player2Deck.get(0));
            player2Deck.remove(0);
            player1Deck.add(player2Deck.get(0));
            player2Deck.remove(0);
            player1Deck.add(player1Deck.get(0));
            player1Deck.remove(0);
            player1Deck.add(player1Deck.get(0));
            player1Deck.remove(0);
            player1Deck.add(player1Deck.get(0));
            player1Deck.remove(0);
        } else if (player1WarRank2.getId() < player2WarRank2.getId()) {
            player2Deck.add(player1Deck.get(0));
            player1Deck.remove(0);
            player2Deck.add(player1Deck.get(0));
            player1Deck.remove(0);
            player2Deck.add(player1Deck.get(0));
            player1Deck.remove(0);
            player2Deck.add(player2Deck.get(0));
            player2Deck.remove(0);
            player2Deck.add(player2Deck.get(0));
            player2Deck.remove(0);
            player2Deck.add(player2Deck.get(0));
            player2Deck.remove(0);
        } else {
            warIsStarting = true;
            startWarForThirdTime();
        }
    }

    public void startWarForThirdTime() {
        player1WarRank3 = player1Deck.get(6).getRank();
        player1WarSuit3 = player1Deck.get(6).getSuit();
        player2WarRank3 = player2Deck.get(6).getRank();
        player2WarSuit3 = player2Deck.get(6).getSuit();

        if (player1WarRank3.getId() > player2WarRank3.getId()) {
            player1Deck.add(player2Deck.get(0));
            player2Deck.remove(0);
            player1Deck.add(player2Deck.get(0));
            player2Deck.remove(0);
            player1Deck.add(player2Deck.get(0));
            player2Deck.remove(0);
            player1Deck.add(player1Deck.get(0));
            player1Deck.remove(0);
            player1Deck.add(player1Deck.get(0));
            player1Deck.remove(0);
            player1Deck.add(player1Deck.get(0));
            player1Deck.remove(0);
        } else if (player1WarRank2.getId() < player2WarRank2.getId()) {
            player2Deck.add(player1Deck.get(0));
            player1Deck.remove(0);
            player2Deck.add(player1Deck.get(0));
            player1Deck.remove(0);
            player2Deck.add(player1Deck.get(0));
            player1Deck.remove(0);
            player2Deck.add(player2Deck.get(0));
            player2Deck.remove(0);
            player2Deck.add(player2Deck.get(0));
            player2Deck.remove(0);
            player2Deck.add(player2Deck.get(0));
            player2Deck.remove(0);
        }
    }
}
