package com.example.snailpasswordmanager.utils

import kotlin.random.Random

private val lowercaseAlphabet = "abcdefghijklmnopqrstuvwxyz"
private val uppercaseAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
private val digitAlphabet = "0123456789"
private val specialAlphabet = "~!@#$%^&*()_-+={}[]\\|:;\"'<>,.?/"

fun passwordGenerate(length: Int, isLowercase: Boolean, isUppercase: Boolean, isDigits: Boolean, isSpecials: Boolean): String {
    if ((!isLowercase && !isUppercase && !isDigits && !isSpecials) || length == 0) {
        return ""
    }

    val composedAlphabet = StringBuilder()
    val password = CharArray(length)
    val filledPositions = mutableListOf<Int>()

    if (isLowercase) {
        setRandomChar(filledPositions, password, lowercaseAlphabet)
        composedAlphabet.append(lowercaseAlphabet)
    }

    if (isUppercase) {
        setRandomChar(filledPositions, password, uppercaseAlphabet)
        composedAlphabet.append(uppercaseAlphabet)
    }

    if (isDigits) {
        setRandomChar(filledPositions, password, digitAlphabet)
        composedAlphabet.append(digitAlphabet)
    }

    if (isSpecials) {
        setRandomChar(filledPositions, password, specialAlphabet)
        composedAlphabet.append(specialAlphabet)
    }

    while (filledPositions.size < length) {
        setRandomChar(filledPositions, password, composedAlphabet.toString())
    }

    return String(password)
}


private fun setRandomChar(filledPositions: MutableList<Int>, password: CharArray, alphabet: String) {
    val passwordLength = password.size
    val alphabetLength = alphabet.length
    val symbol = alphabet[Random.nextInt(alphabetLength)]
    var position = Random.nextInt(passwordLength)

    while (filledPositions.contains(position)) {
        position++
        if (position >= passwordLength) {
            position = 0
        }
    }

    filledPositions.add(position)
    password[position] = symbol
}
