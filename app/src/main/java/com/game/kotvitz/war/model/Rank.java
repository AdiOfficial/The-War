package com.game.kotvitz.war.model;

import java.util.HashMap;
import java.util.Map;

public enum Rank {
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

    public int id;

    Rank(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private static Map<Integer, Rank> map = new HashMap<Integer, Rank>();

    static {
        for (Rank rank : Rank.values()) {
            map.put(rank.id, rank);
        }
    }

    public static Rank getRank(int id) {
        return map.get(id);
    }
}
