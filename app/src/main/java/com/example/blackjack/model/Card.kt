package com.example.blackjack.model

/**
 * Representa los palos de las cartas
 */
enum class Suit {
    HEARTS,    // Corazones
    DIAMONDS,  // Diamantes
    CLUBS,     // Tréboles
    SPADES     // Picas
}

/**
 * Representa los rangos de las cartas
 */
enum class Rank(val displayName: String, val value: Int) {
    ACE("A", 11),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10)
}

/**
 * Representa una carta en el juego de BlackJack
 * @property suit El palo de la carta
 * @property rank El rango de la carta
 * @property isFaceUp Indica si la carta está boca arriba
 */
data class Card(
    val suit: Suit,
    val rank: Rank,
    val isFaceUp: Boolean = true
) {
    /**
     * Obtiene el valor de la carta para el cálculo de puntuación
     * @param currentTotal El total actual de la mano para determinar el valor del As
     */
    fun getValue(currentTotal: Int = 0): Int {
        return if (rank == Rank.ACE && currentTotal + rank.value > 21) {
            1 // El As vale 1 si usar 11 causaría que se pase de 21
        } else {
            rank.value
        }
    }

    /**
     * Obtiene el símbolo del palo para mostrar en la UI
     */
    fun getSuitSymbol(): String {
        return when (suit) {
            Suit.HEARTS -> "♥"
            Suit.DIAMONDS -> "♦"
            Suit.CLUBS -> "♣"
            Suit.SPADES -> "♠"
        }
    }

    /**
     * Obtiene el color del palo
     */
    fun isRed(): Boolean {
        return suit == Suit.HEARTS || suit == Suit.DIAMONDS
    }
}
