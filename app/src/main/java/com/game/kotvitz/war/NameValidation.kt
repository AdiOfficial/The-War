package com.game.kotvitz.war

object NameValidation {

    private const val nameRegex = "^(?=.*[A-Za-z]).{1,10}$"

    fun validateName(name: String): Boolean {
        return name.matches(nameRegex.toRegex())
    }
}
