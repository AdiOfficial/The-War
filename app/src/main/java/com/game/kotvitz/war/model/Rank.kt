package com.game.kotvitz.war.model

import android.util.SparseArray

enum class Rank(var id: Int) {
    ACE(14),
    KING(13),
    QUEEN(12),
    JACK(11),
    TEN(10),
    NINE(9),
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2);


    companion object {

        private val array = SparseArray<Rank>()

        init {
            for (rank in values()) {
                array.put(rank.id, rank)
            }
        }

        fun getRank(id: Int): Rank? {
            return array[id]
        }
    }
}

