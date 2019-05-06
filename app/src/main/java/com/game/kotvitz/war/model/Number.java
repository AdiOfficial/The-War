package com.game.kotvitz.war.model;

public enum Number {
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

    public final int id;

    Number(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
