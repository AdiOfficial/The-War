package com.game.kotvitz.war.model

class Card {

    private var rank: Rank? = null
    private var suit: Suit? = null
    private var wholeDeck: ArrayList<Card> = ArrayList()
    var player1Deck: ArrayList<Card>? = null
    var player2Deck: ArrayList<Card>? = null
    private var player1Rank: Rank? = null
    private var player2Rank: Rank? = null
    private var player1Suit: Suit? = null
    private var player2Suit: Suit? = null
    private var player1WarRank1: Rank? = null
    private var player1WarRank2: Rank? = null
    private var player1WarRank3: Rank? = null
    private var player2WarRank1: Rank? = null
    private var player2WarRank2: Rank? = null
    private var player2WarRank3: Rank? = null
    private var player1WarSuit1: Suit? = null
    private var player2WarSuit1: Suit? = null
    private var player1WarSuit2: Suit? = null
    private var player2WarSuit2: Suit? = null
    private var player1WarSuit3: Suit? = null
    private var player2WarSuit3: Suit? = null
    private var warIsStarting: Boolean = false
    private var rankId: Int = 0

    constructor(rank: Rank?, suit: Suit) {
        this.rank = rank
        this.suit = suit
    }

    constructor() {
        player1Deck = ArrayList()
        player2Deck = ArrayList()
        warIsStarting = false
    }

    private fun createDeck() {
        val numberOfRanks = Rank.values().size
        for (i in 0 until numberOfRanks) {
            rankId = i + 2
            val clubCards = Card(Rank.getRank(rankId), Suit.CLUBS)
            val diamondCard = Card(Rank.getRank(rankId), Suit.DIAMONDS)
            val heartCard = Card(Rank.getRank(rankId), Suit.HEARTS)
            val spadeCard = Card(Rank.getRank(rankId), Suit.SPADES)

            wholeDeck.add(clubCards)
            wholeDeck.add(diamondCard)
            wholeDeck.add(heartCard)
            wholeDeck.add(spadeCard)
        }
        wholeDeck.shuffle()
    }

    fun divideDeck() {
        createDeck()
        player1Deck = ArrayList(wholeDeck.subList(0, 26))
        player2Deck = ArrayList(wholeDeck.subList(26, 52))
    }

    fun compareCards() {
        player1Rank = player1Deck!![0].rank
        player1Suit = player1Deck!![0].suit
        player2Rank = player2Deck!![0].rank
        player2Suit = player2Deck!![0].suit

        if (player1Rank!!.id > player2Rank!!.id) {
            player1Deck!!.add(player1Deck!![0])
            player1Deck!!.removeAt(0)
            player1Deck!!.add(player2Deck!![0])
            player2Deck!!.removeAt(0)
        } else if (player1Rank!!.id < player2Rank!!.id) {
            player2Deck!!.add(player2Deck!![0])
            player2Deck!!.removeAt(0)
            player2Deck!!.add(player1Deck!![0])
            player1Deck!!.removeAt(0)
        } else {
            warIsStarting = true
            startWarForFirstTime()
        }

    }

    private fun startWarForFirstTime() {
        player1WarRank1 = player1Deck!![2].rank
        player1WarSuit1 = player1Deck!![2].suit
        player2WarRank1 = player2Deck!![2].rank
        player2WarSuit1 = player2Deck!![2].suit

        when {
            player1WarRank1!!.id > player2WarRank1!!.id -> {
                player1Deck!!.add(player2Deck!![0])
                player2Deck!!.removeAt(0)
                player1Deck!!.add(player2Deck!![0])
                player2Deck!!.removeAt(0)
                player1Deck!!.add(player2Deck!![0])
                player2Deck!!.removeAt(0)
                player1Deck!!.add(player1Deck!![0])
                player1Deck!!.removeAt(0)
                player1Deck!!.add(player1Deck!![0])
                player1Deck!!.removeAt(0)
                player1Deck!!.add(player1Deck!![0])
                player1Deck!!.removeAt(0)
            }
            player1WarRank1!!.id < player2WarRank1!!.id -> {
                player2Deck!!.add(player1Deck!![0])
                player1Deck!!.removeAt(0)
                player2Deck!!.add(player1Deck!![0])
                player1Deck!!.removeAt(0)
                player2Deck!!.add(player1Deck!![0])
                player1Deck!!.removeAt(0)
                player2Deck!!.add(player2Deck!![0])
                player2Deck!!.removeAt(0)
                player2Deck!!.add(player2Deck!![0])
                player2Deck!!.removeAt(0)
                player2Deck!!.add(player2Deck!![0])
                player2Deck!!.removeAt(0)
            }
            else -> {
                warIsStarting = true
                startWarForSecondTime()
            }
        }
    }

    private fun startWarForSecondTime() {
        player1WarRank2 = player1Deck!![4].rank
        player1WarSuit2 = player1Deck!![4].suit
        player2WarRank2 = player2Deck!![4].rank
        player2WarSuit2 = player2Deck!![4].suit

        when {
            player1WarRank2!!.id > player2WarRank2!!.id -> {
                player1Deck!!.add(player2Deck!![0])
                player2Deck!!.removeAt(0)
                player1Deck!!.add(player2Deck!![0])
                player2Deck!!.removeAt(0)
                player1Deck!!.add(player2Deck!![0])
                player2Deck!!.removeAt(0)
                player1Deck!!.add(player1Deck!![0])
                player1Deck!!.removeAt(0)
                player1Deck!!.add(player1Deck!![0])
                player1Deck!!.removeAt(0)
                player1Deck!!.add(player1Deck!![0])
                player1Deck!!.removeAt(0)
            }
            player1WarRank2!!.id < player2WarRank2!!.id -> {
                player2Deck!!.add(player1Deck!![0])
                player1Deck!!.removeAt(0)
                player2Deck!!.add(player1Deck!![0])
                player1Deck!!.removeAt(0)
                player2Deck!!.add(player1Deck!![0])
                player1Deck!!.removeAt(0)
                player2Deck!!.add(player2Deck!![0])
                player2Deck!!.removeAt(0)
                player2Deck!!.add(player2Deck!![0])
                player2Deck!!.removeAt(0)
                player2Deck!!.add(player2Deck!![0])
                player2Deck!!.removeAt(0)
            }
            else -> {
                warIsStarting = true
                startWarForThirdTime()
            }
        }
    }

    private fun startWarForThirdTime() {
        player1WarRank3 = player1Deck!![6].rank
        player1WarSuit3 = player1Deck!![6].suit
        player2WarRank3 = player2Deck!![6].rank
        player2WarSuit3 = player2Deck!![6].suit

        when {
            player1WarRank3!!.id > player2WarRank3!!.id -> {
                player1Deck!!.add(player2Deck!![0])
                player2Deck!!.removeAt(0)
                player1Deck!!.add(player2Deck!![0])
                player2Deck!!.removeAt(0)
                player1Deck!!.add(player2Deck!![0])
                player2Deck!!.removeAt(0)
                player1Deck!!.add(player1Deck!![0])
                player1Deck!!.removeAt(0)
                player1Deck!!.add(player1Deck!![0])
                player1Deck!!.removeAt(0)
                player1Deck!!.add(player1Deck!![0])
                player1Deck!!.removeAt(0)
            }
            player1WarRank2!!.id < player2WarRank2!!.id -> {
                player2Deck!!.add(player1Deck!![0])
                player1Deck!!.removeAt(0)
                player2Deck!!.add(player1Deck!![0])
                player1Deck!!.removeAt(0)
                player2Deck!!.add(player1Deck!![0])
                player1Deck!!.removeAt(0)
                player2Deck!!.add(player2Deck!![0])
                player2Deck!!.removeAt(0)
                player2Deck!!.add(player2Deck!![0])
                player2Deck!!.removeAt(0)
                player2Deck!!.add(player2Deck!![0])
                player2Deck!!.removeAt(0)
            }
        }
    }
}
