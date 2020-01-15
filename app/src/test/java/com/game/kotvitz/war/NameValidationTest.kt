package com.game.kotvitz.war

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

internal class NameValidationTest {

    @Test
    fun nameValidation_correctNameSimple_ReturnsTrue() {
        assertTrue(NameValidation.validateName("Michal"))
    }

    @Test
    fun nameValidation_oneLetterName_ReturnsTrue() {
        assertTrue(NameValidation.validateName("A"))
    }

    @Test
    fun nameValidation_nameWithNumbers_ReturnsTrue() {
        assertTrue(NameValidation.validateName("Jacek170"))
    }

    @Test
    fun nameValidation_nameWithSpecialSigns_ReturnsTrue() {
        assertTrue(NameValidation.validateName("Adam%!"))
    }

    @Test
    fun nameValidation_onlyNumbersName_ReturnsFalse() {
        assertFalse(NameValidation.validateName("12345"))
    }

    @Test
    fun nameValidation_verLongName_ReturnsFalse() {
        assertFalse(NameValidation.validateName("Aaaaandrzeeeeeeej"))
    }

    @Test
    fun nameValidation_EmptyString_ReturnsFalse() {
        assertFalse(NameValidation.validateName(""))
    }

    @Test
    fun nameValidation_NullName_ReturnsFalse() {
        assertFalse(NameValidation.validateName(null))
    }
}