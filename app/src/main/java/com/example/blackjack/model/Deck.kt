package com.example.blackjack.model

/**
 * Representa un mazo de cartas de BlackJack
 */
class Deck {
    private val cards = mutableListOf<Card>()

    init {
        reset()
    }

    /**
     * Reinicia el mazo con todas las cartas y lo baraja
     */
    fun reset() {
        cards.clear()
        // Crear todas las combinaciones de palos y rangos
        for (suit in Suit.values()) {
            for (rank in Rank.values()) {
                cards.add(Card(suit, rank))
            }
        }
        shuffle()
    }

    /**
     * Baraja las cartas del mazo
     */
    fun shuffle() {
        cards.shuffle()
    }

    /**
     * Reparte una carta del mazo
     * @param faceUp Indica si la carta debe estar boca arriba
     * @return La carta repartida o null si el mazo está vacío
     */
    fun deal(faceUp: Boolean = true): Card? {
        if (cards.isEmpty()) {
            reset() // Reiniciar el mazo si está vacío
        }
        return if (cards.isNotEmpty()) {
            cards.removeAt(0).copy(isFaceUp = faceUp)
        } else {
            null
        }
    }

    /**
     * Obtiene el número de cartas restantes en el mazo
     */
    fun remainingCards(): Int = cards.size
}
