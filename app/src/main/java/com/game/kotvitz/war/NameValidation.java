package com.game.kotvitz.war;

public class NameValidation {

    private static final String nameRegex = "^(?=.*[A-Za-z]).{1,10}$";

    public static boolean validateName(String name) {
        return name.matches(nameRegex);
    }
}
