package com.game.kotvitz.war.model

class Card {

    private var rank: Rank? = null
    private var suit: Suit? = null
    private var wholeDeck: ArrayList<Card> = ArrayList()
    var player1Deck: ArrayList<Card>? = null
    var player2Deck: ArrayList<Card>? = null
    var player1Rank: Rank? = null
    var player2Rank: Rank? = null
    var player1Suit: Suit? = null
    var player2Suit: Suit? = null
    var player1WarRank1: Rank? = null
    var player1WarRank2: Rank? = null
    var player2WarRank1: Rank? = null
    var player2WarRank2: Rank? = null
    var player1WarSuit1: Suit? = null
    var player2WarSuit1: Suit? = null
    private var player1WarSuit2: Suit? = null
    private var player2WarSuit2: Suit? = null
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

    val player1Count: Int
        get() = player1Deck!!.size

    val player2Count: Int
        get() = player2Deck!!.size

    private fun createDeck() {
        val numberOfRanks = Rank.values().size
        for (i in 0 until numberOfRanks) {
            rankId = i + 2
            val clubCards = Card(Rank.getRank(rankId), Suit.C)
            val diamondCard = Card(Rank.getRank(rankId), Suit.D)
            val heartCard = Card(Rank.getRank(rankId), Suit.H)
            val spadeCard = Card(Rank.getRank(rankId), Suit.S)

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

    fun drawCardForFirstPlayer() {
        player1Rank = player1Deck!![0].rank
        player1Suit = player1Deck!![0].suit
    }

    fun drawCardForSecondPlayer() {
        player2Rank = player2Deck!![0].rank
        player2Suit = player2Deck!![0].suit
    }

    fun compareCards() {
        when {
            player1Rank!!.id > player2Rank!!.id -> {
                player1Deck!!.add(player1Deck!![0])
                player1Deck!!.removeAt(0)
                player1Deck!!.add(player2Deck!![0])
                player2Deck!!.removeAt(0)
            }
            player1Rank!!.id < player2Rank!!.id -> {
                player2Deck!!.add(player2Deck!![0])
                player2Deck!!.removeAt(0)
                player2Deck!!.add(player1Deck!![0])
                player1Deck!!.removeAt(0)
            }
            else -> {
                warIsStarting = true
                startWarForFirstTime()
            }
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
        }
    }
}