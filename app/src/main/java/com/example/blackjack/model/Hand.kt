package com.example.blackjack.model

/**
 * Representa una mano de cartas en el juego de BlackJack
 */
data class Hand(
    val cards: List<Card> = emptyList()
) {
    /**
     * Calcula el valor total de la mano
     * Maneja automáticamente el valor del As (1 u 11)
     */
    fun calculateValue(): Int {
        var total = 0
        var aces = 0

        // Primero contar todos los valores y el número de ases
        for (card in cards.filter { it.isFaceUp }) {
            if (card.rank == Rank.ACE) {
                aces++
                total += 11 // Inicialmente contar el As como 11
            } else {
                total += card.rank.value
            }
        }

        // Ajustar el valor de los ases si es necesario
        while (total > 21 && aces > 0) {
            total -= 10 // Cambiar un As de 11 a 1
            aces--
        }

        return total
    }

    /**
     * Verifica si la mano es BlackJack (As + carta de valor 10 con solo 2 cartas)
     */
    fun isBlackJack(): Boolean {
        if (cards.size != 2) return false
        val value = calculateValue()
        return value == 21
    }

    /**
     * Verifica si la mano se ha pasado de 21
     */
    fun isBusted(): Boolean {
        return calculateValue() > 21
    }

    /**
     * Agrega una carta a la mano
     */
    fun addCard(card: Card): Hand {
        return copy(cards = cards + card)
    }

    /**
     * Voltea la primera carta boca abajo si existe
     */
    fun hideFirstCard(): Hand {
        if (cards.isEmpty()) return this
        val newCards = cards.toMutableList()
        newCards[0] = newCards[0].copy(isFaceUp = false)
        return copy(cards = newCards)
    }

    /**
     * Voltea todas las cartas boca arriba
     */
    fun revealAllCards(): Hand {
        return copy(cards = cards.map { it.copy(isFaceUp = true) })
    }
}
